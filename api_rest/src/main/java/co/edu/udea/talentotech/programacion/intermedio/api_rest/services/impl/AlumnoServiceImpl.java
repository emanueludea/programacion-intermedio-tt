package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.AlumnoDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.MateriaDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Alumno;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Materia;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.AlumnoRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.MateriaRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<AlumnoDTO> findAll() {
        List<Alumno> all = (List<Alumno>) alumnoRepository.findAll();
        return all.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AlumnoDTO save(AlumnoDTO alumnoDTO) {
        Alumno alumno = convertToEntity(alumnoDTO);
        Alumno savedAlumno = alumnoRepository.save(alumno);
        return convertToDTO(savedAlumno);
    }

    @Override
    public AlumnoDTO update(Integer id, AlumnoDTO alumnoDTO) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno not found with id: " + id));
        // Update fields
        if (alumnoDTO.getPrimerNombre() != null) {
            alumno.setPrimerNombre(alumnoDTO.getPrimerNombre());
        }
        if (alumnoDTO.getSegundoNombre() != null) {
            alumno.setSegundoNombre(alumnoDTO.getSegundoNombre());
        }
        if (alumnoDTO.getPrimerApellido() != null) {
            alumno.setPrimerApellido(alumnoDTO.getPrimerApellido());
        }
        if (alumnoDTO.getSegundoApellido() != null) {
            alumno.setSegundoApellido(alumnoDTO.getSegundoApellido());
        }
        if (alumnoDTO.getEdad() != null) {
            alumno.setEdad(alumnoDTO.getEdad());
        }
        if (alumnoDTO.getSemestre() != null) {
            alumno.setSemestre(alumnoDTO.getSemestre());
        }
        Alumno updatedAlumno = alumnoRepository.save(alumno);
        return convertToDTO(updatedAlumno);
    }

    @Override
    public void delete(Integer id) {
        alumnoRepository.deleteById(id);
    }

    @Override
    public void enrollMateria(Integer cedulaAlumno, Short codigoMateria) {
        Alumno alumno = alumnoRepository.findById(cedulaAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno not found with cedula: " + cedulaAlumno));
        Materia materia = materiaRepository.findById(codigoMateria)
                .orElseThrow(() -> new RuntimeException("Materia not found with codigo: " + codigoMateria));
        alumno.getMaterias().add(materia);
        alumnoRepository.save(alumno);
    }

    @Override
    public void unenrollMateria(Integer cedulaAlumno, Short codigoMateria) {
        Alumno alumno = alumnoRepository.findById(cedulaAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno not found with cedula: " + cedulaAlumno));
        Materia materia = materiaRepository.findById(codigoMateria)
                .orElseThrow(() -> new RuntimeException("Materia not found with codigo: " + codigoMateria));
        alumno.getMaterias().remove(materia);
        alumnoRepository.save(alumno);
    }

    @Override
    public List<MateriaDTO> findMateriasByAlumno(Integer cedulaAlumno) {
        Alumno alumno = alumnoRepository.findById(cedulaAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno not found with cedula: " + cedulaAlumno));

        return alumno.getMaterias().stream()
                .map(materia -> new MateriaDTO(materia))
                .collect(Collectors.toList());
    }

    private AlumnoDTO convertToDTO(Alumno alumno) {
        return new AlumnoDTO(alumno);
    }

    private Alumno convertToEntity(AlumnoDTO alumnoDTO) {
        Alumno alumno =  new Alumno();
        alumno.setCedula(alumnoDTO.getCedula());
        alumno.setPrimerNombre(alumnoDTO.getPrimerNombre());
        alumno.setSegundoNombre(alumnoDTO.getSegundoNombre());
        alumno.setPrimerApellido(alumnoDTO.getPrimerApellido());
        alumno.setSegundoApellido(alumnoDTO.getSegundoApellido());
        alumno.setEdad(alumnoDTO.getEdad());
        alumno.setSemestre(alumnoDTO.getSemestre());
        return alumno;
    }
}
