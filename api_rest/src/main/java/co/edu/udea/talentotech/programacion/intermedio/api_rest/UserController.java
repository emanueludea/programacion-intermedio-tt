package co.edu.udea.talentotech.programacion.intermedio.api_rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private List<User> users = new ArrayList<>();

    @PostMapping("/users")
    public User createUser(@RequestBody User entity) {
        System.out.println("Received entity: " + entity);
        users.add(entity);
        return entity;
    }

    @GetMapping("/users")
    public List<User> getUsers(
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) String sort) {
        System.out.println(limit + " " + offset + " " + order + " " + sort);
        if (limit <= 0 || offset < 0) {
            throw new IllegalArgumentException("Invalid limit or offset");
        }
        if (offset >= users.size()) {
            return new ArrayList<>();
        }
        if (order != null && !order.isEmpty() && sort != null && !sort.isEmpty()) {
            // Agregar lógica de ordenamiento (En la consulta a la DB)
        }
        return users.subList(offset, Math.min(offset + limit, users.size()));
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User entity) {
        System.out.println("Updating user with ID: " + id + " with entity: " + entity);
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        if (users.isEmpty()) {
            throw new IllegalArgumentException("No users available to update");
        }
        users.forEach(user -> {
            if (user.getId() == id) {
                user.setName(entity.getName());
                user.setEmail(entity.getEmail());
                user.setAge(entity.getAge());
            }
        });
        return entity;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id) {
        users.removeIf(user -> user.getId() == Integer.parseInt(id));
        return id;
    }

}
