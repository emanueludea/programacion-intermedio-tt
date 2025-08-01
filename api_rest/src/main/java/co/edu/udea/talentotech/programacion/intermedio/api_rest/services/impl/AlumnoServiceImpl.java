package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.AlumnoDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.AlumnoRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<AlumnoDTO> findAll() {
        alumnoRepository.getA
    }

    @Override
    public AlumnoDTO save(AlumnoDTO alumnoDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public AlumnoDTO update(int id, AlumnoDTO alumnoDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
