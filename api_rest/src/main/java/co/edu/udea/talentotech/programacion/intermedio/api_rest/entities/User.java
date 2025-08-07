package co.edu.udea.talentotech.programacion.intermedio.api_rest.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @Column(name = "username", length = 50)
    @NotNull
    @Size(max = 50)
    private String username;

    @Column(name = "user_password", length = 255)
    @NotNull
    @Size(max = 255)
    private String userPassword;

    @Column(name = "isadmin")
    @NotNull
    private Boolean isAdmin = false;

    public User() {
    }

    public User(String username, String userPassword, Boolean isAdmin) {
        this.username = username;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
