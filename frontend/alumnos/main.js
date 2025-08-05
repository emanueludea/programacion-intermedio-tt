"use strict";

import { AlumnoApp } from './resources/modules/app.js';

// Inicialización de la aplicación
document.addEventListener('DOMContentLoaded', async function() {
    const app = new AlumnoApp();
    await app.init();
});