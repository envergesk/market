package ru.kildeev.market.api;

public class JwtResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtResponse(String token) {
        this.token = token;
    }
}
