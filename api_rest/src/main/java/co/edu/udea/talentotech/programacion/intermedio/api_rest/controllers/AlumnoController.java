package co.edu.udea.talentotech.programacion.intermedio.api_rest.controllers;

import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.AlumnoDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.MateriaDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.AlumnoService;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl.AlumnoServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = "*")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<AlumnoDTO> getAlumnos(@RequestParam(required = false) String param) {
        // Hacer validaciones de la peticion HTTP
        return alumnoService.findAll();
    }

    @GetMapping("/{cedula}/materias")
    public ResponseEntity<List<MateriaDTO>> getMateriasByAlumno(@PathVariable Integer cedula) {
        return ResponseEntity.ok(alumnoService.findMateriasByAlumno(cedula));
    }

    @PostMapping
    public ResponseEntity<AlumnoDTO> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        AlumnoDTO createdAlumno = alumnoService.save(alumnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlumno);
    }

    @PostMapping("/{cedula}/materias/{codigoMateria}")
    public ResponseEntity<Void> matricularMateria(@PathVariable Integer cedula, @PathVariable Short codigoMateria) {
        alumnoService.enrollMateria(cedula, codigoMateria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<AlumnoDTO> updateAlumno(@PathVariable Integer cedula, @RequestBody AlumnoDTO alumnoDTO) {
        AlumnoDTO updatedAlumno = alumnoService.update(cedula, alumnoDTO);
        return ResponseEntity.ok(updatedAlumno);
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Integer cedula) {
        alumnoService.delete(cedula);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cedula}/materias/{codigoMateria}")
    public ResponseEntity<Void> eliminarMateria(@PathVariable Integer cedula, @PathVariable Short codigoMateria) {
        alumnoService.unenrollMateria(cedula, codigoMateria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
