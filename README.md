# Library Management System — Java + MySQL

Proyecto académico básico para administrar libros y consultar registros de una biblioteca.

## Funciones
- Registrar libros.
- Mostrar los libros registrados.
- Buscar un libro por título.
- Registrar préstamos de libros.
- Consultar los préstamos.

## Requisitos
- Java 17 o superior.
- MySQL Server y MySQL Workbench o XAMPP.
- MySQL Connector/J (controlador JDBC).

## Preparar la base de datos
1. Abre MySQL Workbench o phpMyAdmin.
2. Ejecuta el contenido de `database/library.sql`.
3. Abre `src/LibraryManagementSystem.java`.
4. En la constante `URL`, `USER` y `PASSWORD`, configura los datos de tu conexión.
5. Agrega MySQL Connector/J al proyecto.
6. Ejecuta la clase `LibraryManagementSystem`.

## Nota
Es una versión de consola para practicar Java, JDBC, SQL y operaciones CRUD básicas. El código usa consultas preparadas (`PreparedStatement`) para enviar datos a MySQL.
