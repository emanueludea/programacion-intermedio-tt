"use strict";

import { Alumno, AlumnoService } from './alumno.js';

export class AlumnoApp {
    constructor() {
        this.alumnos = [];
        this.editingCedula = null;
        this.deleteTargetCedula = null;
    }

    async init() {
        this.initializeElements();
        this.setupEventListeners();
        await this.loadAlumnos();
    }

    initializeElements() {
        // Elementos del DOM
        this.elements = {
            form: document.getElementById('alumno-form'),
            tableBody: document.getElementById('alumnos-tbody'),
            searchInput: document.getElementById('search-input'),
            formTitle: document.getElementById('form-title'),
            submitBtn: document.getElementById('submit-btn'),
            cancelBtn: document.getElementById('cancel-btn'),
            deleteModal: document.getElementById('delete-modal'),
            confirmDeleteBtn: document.getElementById('confirm-delete'),
            cancelDeleteBtn: document.getElementById('cancel-delete'),
            loadingDiv: document.getElementById('loading'),
            noDataDiv: document.getElementById('no-data'),
            inputs: {
                cedula: document.getElementById('cedula'),
                primerNombre: document.getElementById('primerNombre'),
                segundoNombre: document.getElementById('segundoNombre'),
                primerApellido: document.getElementById('primerApellido'),
                segundoApellido: document.getElementById('segundoApellido'),
                edad: document.getElementById('edad'),
                semestre: document.getElementById('semestre')
            }
        };
    }

    setupEventListeners() {
        // Envío del formulario
        this.elements.form.addEventListener('submit', (e) => this.handleFormSubmit(e));

        // Botón cancelar
        this.elements.cancelBtn.addEventListener('click', () => this.resetForm());

        // Campo de búsqueda
        this.elements.searchInput.addEventListener('input', (e) => this.handleSearch(e));

        // Botones del modal de eliminación
        this.elements.confirmDeleteBtn.addEventListener('click', () => this.confirmDelete());
        this.elements.cancelDeleteBtn.addEventListener('click', () => this.closeDeleteModal());

        // Cerrar modal al hacer clic fuera
        window.addEventListener('click', (event) => {
            if (event.target === this.elements.deleteModal) {
                this.closeDeleteModal();
            }
        });

        // Delegación de eventos para botones de la tabla
        this.elements.tableBody.addEventListener('click', (e) => {
            console.log('Clic detectado en tabla:', e.target); // Log de depuración

            if (e.target.classList.contains('btn-edit')) {
                const cedula = parseInt(e.target.getAttribute('data-cedula'));
                console.log('Botón editar presionado para cédula:', cedula); // Log de depuración
                this.editAlumno(cedula);
            } else if (e.target.classList.contains('btn-delete')) {
                const cedula = parseInt(e.target.getAttribute('data-cedula'));
                console.log('Botón eliminar presionado para cédula:', cedula); // Log de depuración
                this.showDeleteModal(cedula);
            }
        });
    }

    async loadAlumnos() {
        try {
            this.showLoading(true);
            this.alumnos = await AlumnoService.getAll();
            this.renderAlumnos(this.alumnos);
            this.showMessage('Alumnos cargados exitosamente', 'success');
        } catch (error) {
            console.error('Error al cargar alumnos:', error);
            this.showMessage('Error al cargar los alumnos', 'error');
            this.alumnos = [];
            this.renderAlumnos([]);
        } finally {
            this.showLoading(false);
        }
    }

    renderAlumnos(alumnosArray) {
        this.elements.tableBody.innerHTML = '';

        if (alumnosArray.length === 0) {
            this.elements.noDataDiv.style.display = 'block';
            return;
        }

        this.elements.noDataDiv.style.display = 'none';

        alumnosArray.forEach(alumno => {
            const row = this.createAlumnoRow(alumno);
            this.elements.tableBody.appendChild(row);
        });
    }

    createAlumnoRow(alumno) {
        const row = document.createElement('tr');
        row.className = 'fade-in';

        // Crear la fila con atributos de datos apropiados
        row.innerHTML = `
            <td>${alumno.cedula}</td>
            <td>${alumno.primerNombre}</td>
            <td>${alumno.segundoNombre || 'N/A'}</td>
            <td>${alumno.primerApellido}</td>
            <td>${alumno.segundoApellido || 'N/A'}</td>
            <td>${alumno.edad || 'N/A'}</td>
            <td>${alumno.semestre || 'N/A'}</td>
            <td class="action-buttons">
                <button type="button" class="btn-edit" data-cedula="${alumno.cedula}">
                    Editar
                </button>
                <button type="button" class="btn-delete" data-cedula="${alumno.cedula}">
                    Eliminar
                </button>
            </td>
        `;

        return row;
    }

    async handleFormSubmit(event) {
        event.preventDefault();

        console.log('Formulario enviado, editando cédula:', this.editingCedula); // Log de depuración

        const formData = new FormData(this.elements.form);
        const cedulaValue = this.editingCedula || parseInt(formData.get('cedula'));
        const alumno = new Alumno(
            cedulaValue,
            formData.get('primerNombre'),
            formData.get('segundoNombre') || null,
            formData.get('primerApellido'),
            formData.get('segundoApellido') || null,
            formData.get('edad') ? parseInt(formData.get('edad')) : null,
            formData.get('semestre') ? parseInt(formData.get('semestre')) : null
        );

        if (!alumno.isValid()) {
            console.log('Datos del alumno:', alumno, alumno.isValid()); // Log de depuración

            this.showMessage('Por favor complete los campos requeridos (cédula, primer nombre y primer apellido)', 'error');
            return;
        }

        try {
            if (this.editingCedula) {
                console.log('Llamando actualización para cédula:', this.editingCedula); // Log de depuración
                await AlumnoService.update(this.editingCedula, alumno);
                this.showMessage('Alumno actualizado exitosamente', 'success');
            } else {
                console.log('Creando nuevo alumno'); // Log de depuración
                await AlumnoService.create(alumno);
                this.showMessage('Alumno creado exitosamente', 'success');
            }

            this.resetForm();
            await this.loadAlumnos();
        } catch (error) {
            console.error('Error al guardar alumno:', error);
            this.showMessage('Error al guardar el alumno: ' + error.message, 'error');
        }
    }

    editAlumno(cedula) {
        console.log('Editar alumno llamado con cédula:', cedula); // Log de depuración

        const alumno = this.alumnos.find(a => a.cedula === cedula);
        if (!alumno) {
            console.error('Alumno no encontrado con cédula:', cedula);
            return;
        }

        console.log('Alumno encontrado para editar:', alumno); // Log de depuración

        // Llenar el formulario con los datos del alumno
        this.elements.inputs.cedula.value = alumno.cedula;
        this.elements.inputs.primerNombre.value = alumno.primerNombre;
        this.elements.inputs.segundoNombre.value = alumno.segundoNombre || '';
        this.elements.inputs.primerApellido.value = alumno.primerApellido;
        this.elements.inputs.segundoApellido.value = alumno.segundoApellido || '';
        this.elements.inputs.edad.value = alumno.edad || '';
        this.elements.inputs.semestre.value = alumno.semestre || '';

        // Cambiar el estado del formulario a edición
        this.editingCedula = cedula;
        this.elements.formTitle.textContent = 'Editar Alumno';
        this.elements.submitBtn.textContent = 'Actualizar';
        this.elements.cancelBtn.style.display = 'inline-block';

        // Deshabilitar el campo cédula
        this.elements.inputs.cedula.disabled = true;

        console.log('Formulario actualizado para edición, editingCedula establecida a:', this.editingCedula); // Log de depuración

        // Desplazarse al formulario
        document.querySelector('.form-section').scrollIntoView({ behavior: 'smooth' });
    }

    showDeleteModal(cedula) {
        this.elements.deleteModal.style.display = 'block';
        this.deleteTargetCedula = cedula;
    }

    closeDeleteModal() {
        this.elements.deleteModal.style.display = 'none';
        this.deleteTargetCedula = null;
    }

    async confirmDelete() {
        if (!this.deleteTargetCedula) return;

        try {
            await AlumnoService.delete(this.deleteTargetCedula);
            this.showMessage('Alumno eliminado exitosamente', 'success');
            this.closeDeleteModal();
            await this.loadAlumnos();
        } catch (error) {
            console.error('Error al eliminar alumno:', error);
            this.showMessage('Error al eliminar el alumno: ' + error.message, 'error');
        }
    }

    resetForm() {
        console.log('Reiniciando formulario'); // Log de depuración
        this.elements.form.reset();
        this.editingCedula = null;
        this.elements.formTitle.textContent = 'Agregar Nuevo Alumno';
        this.elements.submitBtn.textContent = 'Guardar';
        this.elements.cancelBtn.style.display = 'none';
        this.elements.inputs.cedula.disabled = false;
    }

    handleSearch(event) {
        const searchTerm = event.target.value;
        const filteredAlumnos = this.alumnos.filter(alumno =>
            alumno.matchesSearch(searchTerm)
        );
        this.renderAlumnos(filteredAlumnos);
    }

    showLoading(show) {
        this.elements.loadingDiv.style.display = show ? 'block' : 'none';
    }

    showMessage(message, type) {
        // Remover mensajes anteriores
        const existingMessages = document.querySelectorAll('.success-message, .error-message');
        existingMessages.forEach(msg => msg.remove());

        const messageDiv = document.createElement('div');
        messageDiv.className = type === 'success' ? 'success-message' : 'error-message';
        messageDiv.textContent = message;

        // Insertar el mensaje al inicio del contenedor
        const container = document.querySelector('.container');
        container.insertBefore(messageDiv, container.firstChild);

        // Remover el mensaje después de 5 segundos
        setTimeout(() => {
            messageDiv.remove();
        }, 5000);
    }
}