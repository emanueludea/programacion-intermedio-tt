package co.edu.udea.talentotech.programacion.intermedio.api_rest.services;

import java.util.List;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.AlumnoDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.MateriaDTO;

public interface AlumnoService {
    List<AlumnoDTO> findAll();
    AlumnoDTO save(AlumnoDTO alumnoDTO);
    AlumnoDTO update(Integer id, AlumnoDTO alumnoDTO);
    void delete(Integer id);
    void enrollMateria(Integer cedulaAlumno, Short codigoMateria);
    void unenrollMateria(Integer cedulaAlumno, Short codigoMateria);
    List<MateriaDTO> findMateriasByAlumno(Integer cedulaAlumno);
}
