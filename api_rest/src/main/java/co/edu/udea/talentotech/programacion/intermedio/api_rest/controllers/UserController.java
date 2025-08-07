package co.edu.udea.talentotech.programacion.intermedio.api_rest.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.AuthResponse;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.LoginRequest;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.UserDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.security.JwtUtil;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        try {
            Optional<UserDTO> user = userService.findByUsername(username);
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            // Validate required fields
            if (userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (userDTO.getUserPassword() == null || userDTO.getUserPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Check if user already exists
            Optional<UserDTO> existingUser = userService.findByUsername(userDTO.getUsername());
            if (existingUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
            }

            UserDTO savedUser = userService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(username, userDTO);
            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        try {
            boolean deleted = userService.deleteByUsername(username);
            if (deleted) {
                return ResponseEntity.noContent().build(); // 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                return ResponseEntity.badRequest().build();
            }

            UserDTO authenticatedUser = userService.authenticateUser(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            );

            if (authenticatedUser != null) {
                String token = jwtUtil.generateToken(
                    authenticatedUser.getUsername(), 
                    authenticatedUser.getIsAdmin()
                );
                
                AuthResponse authResponse = new AuthResponse(
                    token, 
                    authenticatedUser.getUsername(), 
                    authenticatedUser.getIsAdmin()
                );
                
                return ResponseEntity.ok(authResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Temporary endpoint to generate password hashes - REMOVE IN PRODUCTION
    // @GetMapping("/generate-hashes")
    // public ResponseEntity<String> generatePasswordHashes() {
    //     userService.generatePasswordHashes();
    //     return ResponseEntity.ok("Check console for password hashes");
    // }

}
