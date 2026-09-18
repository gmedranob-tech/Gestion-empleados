# Gestión de Empleados

Proyecto individual desarrollado para el curso de Programación II.

La aplicación permite administrar empleados mediante una interfaz gráfica
desarrollada con Java Swing y utiliza MySQL para almacenar la información
de forma persistente.

## Funcionalidades

El sistema permite:

- Registrar empleados.
- Listar los empleados registrados.
- Actualizar la información de un empleado.
- Cambiar el estado de un empleado entre activo e inactivo.
- Eliminar empleados con confirmación previa.
- Validar los datos antes de almacenarlos en la base de datos.

Cada empleado contiene la siguiente información:

- ID autogenerado.
- Nombre completo.
- Departamento.
- Salario mensual.
- Fecha de contratación.
- Estado activo o inactivo.

## Validaciones

Antes de guardar o actualizar un empleado, el sistema verifica que:

- El nombre completo no esté vacío.
- El departamento no esté vacío.
- El salario sea un número válido y mayor que cero.
- La fecha tenga un formato válido.
- La fecha de contratación no sea una fecha futura.

## Tecnologías utilizadas

- Java 11
- Java Swing
- Maven
- JDBC
- MySQL
- Git y GitHub

## Estructura del proyecto

El proyecto utiliza una estructura Maven multi-módulo:

### gestion-empleados-core

Contiene la lógica relacionada con los datos de la aplicación:

- `Empleado.java`: modelo que representa a un empleado.
- `EmpleadoDAO.java`: contiene las operaciones CRUD mediante JDBC.

### gestion-empleados-ui

Contiene la interfaz gráfica de la aplicación:

- `MainUI.java`: punto de entrada de la aplicación.
- `VentanaPrincipal.java`: ventana principal desarrollada con Swing.
- `sql/schema.sql`: script para crear la base de datos y la tabla de empleados.

La interfaz gráfica no realiza operaciones SQL directamente. Las operaciones
con la base de datos son responsabilidad de `EmpleadoDAO`.

## Base de datos

El proyecto utiliza la base de datos:

`gestion_empleados_db`

Para crearla, se debe ejecutar el archivo:

`gestion-empleados-ui/sql/schema.sql`

El script crea la base de datos y la tabla `empleados`.

## Configuración de MySQL

La conexión a MySQL se encuentra configurada en:

`gestion-empleados-core/src/main/java/edu/umg/programacion2/gestionempleados/dao/EmpleadoDAO.java`

Por seguridad, el repositorio no contiene una contraseña real.

Antes de ejecutar la aplicación, se debe reemplazar:

`tu_password_aqui`

por la contraseña correspondiente al usuario local de MySQL.

La configuración utilizada es:

- Base de datos: `gestion_empleados_db`
- Usuario: `root`
- Servidor: `localhost`
- Puerto: `3306`

## Ejecución

1. Ejecutar `gestion-empleados-ui/sql/schema.sql` en MySQL.
2. Configurar la contraseña local de MySQL en `EmpleadoDAO.java`.
3. Desde la raíz del proyecto ejecutar:

   `mvn clean install`

4. Ejecutar `MainUI.java` como aplicación Java.

## Arquitectura

El flujo principal de la aplicación es:

`VentanaPrincipal → EmpleadoDAO → JDBC → MySQL`

De esta manera se mantiene separada la interfaz gráfica de las operaciones
de acceso a la base de datos.