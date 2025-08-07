package co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
