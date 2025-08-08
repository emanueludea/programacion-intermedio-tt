package co.edu.udea.talentotech.programacion.intermedio.api_rest.dto;

public class AuthResponse {
    private String username;
    private Boolean isAdmin;

    public AuthResponse() {
    }

    public AuthResponse(String username, Boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "username='" + username + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
