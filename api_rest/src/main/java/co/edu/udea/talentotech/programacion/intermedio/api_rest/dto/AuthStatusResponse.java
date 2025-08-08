package co.edu.udea.talentotech.programacion.intermedio.api_rest.dto;

public class AuthStatusResponse {
    private UserDTO userDTO;
    private Boolean isAuth;

    public AuthStatusResponse() {
    }

    public AuthStatusResponse(Boolean isAuth, UserDTO userDTO) {
        this.isAuth = isAuth;
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isAuth) {
        this.isAuth = isAuth;
    }

    @Override
    public String toString() {
        return "AuthStatusResponse{" +
                "userDTO=" + userDTO +
                ", isAuth=" + isAuth +
                '}';
    }
}
