-- Base de datos para el sistema de gestion de empleados
CREATE DATABASE IF NOT EXISTS gestion_empleados_db;

USE gestion_empleados_db;

-- Tabla principal de empleados
CREATE TABLE IF NOT EXISTS empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);