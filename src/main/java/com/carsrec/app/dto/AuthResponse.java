package com.carsrec.app.dto;

public class AuthResponse {
    private String token;
    private String email;

    public AuthResponse() {}

    public AuthResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public String getToken() { return token; }
    public String getEmail() { return email; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String token;
        private String email;

        public Builder token(String token) { this.token = token; return this; }
        public Builder email(String email) { this.email = email; return this; }

        public AuthResponse build() {
            return new AuthResponse(token, email);
        }
    }
}
