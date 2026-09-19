-- Base de datos para el sistema de gestion de empleados
CREATE DATABASE IF NOT EXISTS gestion_empleados_db;

USE gestion_empleados_db;

-- Tabla principal de empleados.
--
-- id: INT AUTO_INCREMENT porque el identificador debe ser unico
-- y generado automaticamente por el sistema.
--
-- nombre_completo: VARCHAR(100) permite almacenar nombres completos
-- y NOT NULL evita valores nulos.
--
-- departamento: VARCHAR(100) porque la variante indica que el
-- departamento se ingresa como texto libre.
--
-- salario: DECIMAL(10,2) porque representa dinero y necesitamos
-- almacenar valores decimales exactos con dos posiciones decimales.
--
-- fecha_contratacion: DATE porque solamente necesitamos almacenar
-- la fecha de contratacion, sin hora.
--
-- activo: BOOLEAN representa los dos estados del empleado.
-- Por defecto un empleado nuevo se registra como activo.
CREATE TABLE IF NOT EXISTS empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);