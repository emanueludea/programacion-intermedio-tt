package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.UserDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.User;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.UserRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        // Encrypt password before saving
        if (userDTO.getUserPassword() != null && !userDTO.getUserPassword().isEmpty()) {
            user.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        }
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(this::convertToDTO);
    }

    @Override
    public boolean deleteByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    @Override
    public UserDTO updateUser(String username, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // Update fields if provided
            if (userDTO.getUserPassword() != null && !userDTO.getUserPassword().isEmpty()) {
                user.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
            }
            if (userDTO.getIsAdmin() != null) {
                user.setIsAdmin(userDTO.getIsAdmin());
            }

            User updatedUser = userRepository.save(user);
            return convertToDTO(updatedUser);
        }
        return null;
    }

    @Override
    public UserDTO authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        System.out.println("Authenticating user: " + username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getUserPassword())) {
            System.out.println("User authenticated successfully: " + user.get().getUsername());
            return convertToDTO(user.get());
        }
        return null;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUsername(), user.getIsAdmin());
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setUserPassword(userDTO.getUserPassword());
        user.setIsAdmin(userDTO.getIsAdmin() != null ? userDTO.getIsAdmin() : false);
        return user;
    }
}
