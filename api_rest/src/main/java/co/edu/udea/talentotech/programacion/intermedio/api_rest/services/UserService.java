package co.edu.udea.talentotech.programacion.intermedio.api_rest.services;

import java.util.List;
import java.util.Optional;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.UserDTO;

public interface UserService {
    public UserDTO save(UserDTO userDTO);
    public Optional<UserDTO> findByUsername(String username);
    public boolean deleteByUsername(String username);
    public UserDTO updateUser(String username, UserDTO userDTO);
    public UserDTO authenticateUser(String username, String password);
    // public void generatePasswordHashes();
}
