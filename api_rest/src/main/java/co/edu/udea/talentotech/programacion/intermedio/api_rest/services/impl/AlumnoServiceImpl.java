package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.AlumnoDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Alumno;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.AlumnoRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<AlumnoDTO> findAll() {
        List<Alumno> all = (List<Alumno>) alumnoRepository.findAll();
        return all.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AlumnoDTO save(AlumnoDTO alumnoDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public AlumnoDTO update(Integer id, AlumnoDTO alumnoDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        alumnoRepository.deleteById(id);
    }

    private AlumnoDTO convertToDTO(Alumno alumno) {
        return new AlumnoDTO(alumno);
    }
}
