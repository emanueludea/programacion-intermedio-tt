package co.edu.udea.talentotech.programacion.intermedio.api_rest.dto;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.User;

public class UserDTO {
    private String username;
    private String userPassword;
    private Boolean isAdmin;

    public UserDTO() {
    }

    public UserDTO(String username, String userPassword, Boolean isAdmin) {
        this.username = username;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
    }

    // Constructor for response (without password)
    public UserDTO(String username, Boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
    }

    // Constructor from entity
    public UserDTO(User user) {
        this.username = user.getUsername();
        this.isAdmin = user.getIsAdmin();
        // Don't include password in DTO for security
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
