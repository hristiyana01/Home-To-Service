package app.dtos;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private String message;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
        this.message = null;
    }

    public AuthenticationResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

