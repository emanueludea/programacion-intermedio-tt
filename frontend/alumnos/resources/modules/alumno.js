"use strict";

export class Alumno {
    constructor(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, edad, semestre) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.edad = edad;
        this.semestre = semestre;
    }

    // Método para convertir a JSON para la API
    toJSON() {
        return {
            cedula: this.cedula,
            primerNombre: this.primerNombre,
            segundoNombre: this.segundoNombre,
            primerApellido: this.primerApellido,
            segundoApellido: this.segundoApellido,
            edad: this.edad,
            semestre: this.semestre
        };
    }

    // Método para convertir a JSON para actualizaciones (sin cédula)
    toUpdateJSON() {
        return {
            primerNombre: this.primerNombre,
            segundoNombre: this.segundoNombre,
            primerApellido: this.primerApellido,
            segundoApellido: this.segundoApellido,
            edad: this.edad,
            semestre: this.semestre
        };
    }

    // Método estático para crear desde datos de la API
    static fromJSON(data) {
        return new Alumno(
            data.cedula,
            data.primerNombre,
            data.segundoNombre,
            data.primerApellido,
            data.segundoApellido,
            data.edad,
            data.semestre
        );
    }

    // Validar datos del alumno
    isValid() {
        return this.cedula && 
               this.primerNombre && 
               this.primerApellido;
    }

    // Método para buscar por término
    matchesSearch(searchTerm) {
        const term = searchTerm.toLowerCase();
        return this.primerNombre.toLowerCase().includes(term) ||
               (this.segundoNombre && this.segundoNombre.toLowerCase().includes(term)) ||
               this.primerApellido.toLowerCase().includes(term) ||
               (this.segundoApellido && this.segundoApellido.toLowerCase().includes(term)) ||
               this.cedula.toString().includes(term);
    }

    // Método para obtener nombre completo
    getNombreCompleto() {
        let nombre = this.primerNombre;
        if (this.segundoNombre) {
            nombre += ` ${this.segundoNombre}`;
        }
        nombre += ` ${this.primerApellido}`;
        if (this.segundoApellido) {
            nombre += ` ${this.segundoApellido}`;
        }
        return nombre;
    }
}

// Servicio para operaciones con la API
export class AlumnoService {
    static API_BASE_URL = 'http://localhost:8080/api/alumnos';

    static async getAll() {
        try {
            const response = await fetch(this.API_BASE_URL);
            if (!response.ok) {
                throw new Error(`Error HTTP! estado: ${response.status}`);
            }
            const data = await response.json();
            return data.map(alumnoData => Alumno.fromJSON(alumnoData));
        } catch (error) {
            console.error('Error al obtener alumnos:', error);
            throw error;
        }
    }

    static async create(alumno) {
        try {
            const response = await fetch(this.API_BASE_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(alumno.toJSON())
            });
            
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(`Error HTTP! estado: ${response.status}, mensaje: ${errorText}`);
            }
            
            const data = await response.json();
            return Alumno.fromJSON(data);
        } catch (error) {
            console.error('Error al crear alumno:', error);
            throw error;
        }
    }

    static async update(cedula, alumno) {
        try {
            // Usar toUpdateJSON() para excluir la cédula del cuerpo de la petición
            const updateData = alumno.toUpdateJSON();
            
            console.log('Actualizando alumno con cédula:', cedula);
            console.log('Datos de actualización:', updateData);
            
            const response = await fetch(`${this.API_BASE_URL}/${cedula}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updateData)
            });
            
            if (!response.ok) {
                const errorText = await response.text();
                console.error('Actualización falló:', response.status, errorText);
                throw new Error(`Error HTTP! estado: ${response.status}, mensaje: ${errorText}`);
            }
            
            // Verificar si la respuesta tiene contenido
            const contentType = response.headers.get('Content-Type');
            if (contentType && contentType.includes('application/json')) {
                const data = await response.json();
                return Alumno.fromJSON(data);
            } else {
                // Si no hay respuesta JSON, retornar el alumno actualizado
                return alumno;
            }
        } catch (error) {
            console.error('Error al actualizar alumno:', error);
            throw error;
        }
    }

    static async delete(cedula) {
        try {
            const response = await fetch(`${this.API_BASE_URL}/${cedula}`, {
                method: 'DELETE'
            });
            
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(`Error HTTP! estado: ${response.status}, mensaje: ${errorText}`);
            }
            
            return true;
        } catch (error) {
            console.error('Error al eliminar alumno:', error);
            throw error;
        }
    }

    static async getMateriasByAlumno(cedula) {
        try {
            const response = await fetch(`${this.API_BASE_URL}/${cedula}/materias`);
            if (!response.ok) {
                throw new Error(`Error HTTP! estado: ${response.status}`);
            }
            return await response.json();
        } catch (error) {
            console.error('Error al obtener materias del alumno:', error);
            throw error;
        }
    }
}