# Library Management System

A Java project for managing library books, searching for titles, and registering book loans using a MySQL database.

## About the Project

This project was developed as part of my academic programming practice. It focuses on creating a basic library management system using Java and MySQL.

The program allows users to register books, display the available catalog, search for books by title, and register loans to students. The information is stored in a MySQL database and accessed through JDBC.

## Features

- Register new books with their titles and authors.
- Display all registered books.
- Search for books by title.
- Check whether a book is available or already borrowed.
- Register book loans to students.
- Save loan dates in the database.
- Display registered loans.
- Update book availability when a loan is registered.

## Technologies and Concepts

- **Language:** Java
- **Database:** MySQL
- **Database connection:** JDBC (Java Database Connectivity)
- **Libraries:** `java.sql`, `java.util`, and `java.time`
- **Database operations:** INSERT, SELECT, and UPDATE.
- **SQL concepts:** Tables, primary keys, foreign keys, and relationships.
- **Programming concepts:** Methods, loops, conditional statements, and exception handling.
- **Prepared statements:** Using `PreparedStatement` to execute parameterized SQL queries.

## Project Structure

```text
library-management-system/
├── src/
│   └── LibraryManagementSystem.java
├── database/
│   └── library.sql
└── README.md
```

## Requirements

- Java 17 or higher.
- MySQL Server.
- MySQL Workbench or phpMyAdmin.
- MySQL Connector/J.

The MySQL database must be created using the `database/library.sql` script.

The database connection settings in `LibraryManagementSystem.java` must match the local MySQL configuration.

## How to Run

### 1. Create the database

Open MySQL Workbench or phpMyAdmin and execute the SQL commands inside:

```text
database/library.sql
```

### 2. Configure the database connection

Open `LibraryManagementSystem.java` and update the connection settings:

```java
private static final String URL = "jdbc:mysql://localhost:3306/library_db";
private static final String USER = "root";
private static final String PASSWORD = "";
```

Replace the username and password with your own MySQL credentials.

### 3. Add MySQL Connector/J

Add the MySQL Connector/J library to the Java project so that JDBC can connect to the database.

### 4. Run the program

Compile and execute `LibraryManagementSystem.java` from your IDE or terminal, making sure the MySQL connector is included in the classpath.

**Note:** MySQL Server must be running before starting the application. The program uses a local database connection.

## What I Learned

While working on this project, I practiced connecting Java applications to MySQL databases, creating tables and relationships, executing SQL queries with JDBC, organizing code into methods, and managing information through basic database operations.

## Author

**Fátima Montoya**  
Information Systems Engineering Student
