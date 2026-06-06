package com.carsrec.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Intercepts every HTTP request and checks for a valid JWT in the Authorization header.
// If valid, authenticates the user so Spring Security allows them through to the endpoint.
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // Injects JwtService for token operations and UserDetailsService to load the user from the DB
    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    // Runs once per request. Reads the Authorization header, validates the JWT,
    // and if valid sets the authenticated user in the security context so protected
    // endpoints know who is making the request.
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // If there is no Authorization header or it doesn't start with "Bearer ", skip JWT processing
        // and pass the request along — Spring Security will block it if the endpoint requires auth
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Strip "Bearer " prefix to get the raw token, then extract the email from it
        final String jwt = authHeader.substring(7);
        final String username = jwtService.extractUsername(jwt);

        // Only authenticate if we got a username from the token and the user isn't already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load the user's full details from the DB to verify they still exist
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Build a Spring Security authentication object with the user's roles
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Attach request metadata (IP, session ID) to the authentication object
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Store the authenticated user in the security context for this request
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Pass the request to the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
