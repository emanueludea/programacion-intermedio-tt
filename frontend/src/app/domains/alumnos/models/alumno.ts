export interface Alumno {
    cedula: number;
    primerNombre: string;
    segundoNombre?: string;
    primerApellido: string;
    segundoApellido?: string;
    edad?: number;
    semestre?: number;
}
export interface AlumnoDTO {
    cedula: number;
    primerNombre: string;
    segundoNombre?: string;
    primerApellido: string;
    segundoApellido?: string;
    edad?: number;
    semestre?: number;
}
