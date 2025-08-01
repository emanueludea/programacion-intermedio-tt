package co.edu.udea.talentotech.programacion.intermedio.api_rest.controllers;

import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.AlumnoDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.AlumnoService;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl.AlumnoServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/alumnos")
    public List<AlumnoDTO> getAlumnos(@RequestParam String param) {
        return alumnoService.findAll();
    }
    
    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    @PutMapping("path/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }

    @DeleteMapping("path/{id}")
    public void deleteMethodName(@PathVariable String id) {
        //TODO: process DELETE request
    }
}
