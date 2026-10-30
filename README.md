#### Library Management System

A console-based Java application developed to manage books, track user loans, and maintain digital records for a library using MySQL database persistence. 

#### About the Project

This academic project focuses on backend development fundamentals, object-oriented programming (OOP) in Java, and relational database management systems (RDBMS). It implements core CRUD (Create, Read, Update, Delete) operations and secures database interactions using JDBC. 

To ensure data integrity and prevent security vulnerabilities, the application utilizes parameterized queries via PreparedStatement to guard against SQL Injection. 

#### Features

* **Book Inventory Management:** Register new books into the database and retrieve a complete list of current inventory.
* **Search Engine:** Query and locate specific books instantly filtering by title matching.
* **Loan Tracking System:** Issue and register book loans assigned to users with real-time database updates.
* **History Logging:** Query and review active or past book lending transaction records.

#### Technologies and Concepts

* **Language:** Java (Version 17 or higher)
* **Database:** MySQL Server (Managed via MySQL Workbench / XAMPP)
* **Driver:** JDBC (MySQL Connector/J)
* **Security & DB Practices:** SQL Schema Design, Foreign Keys, Parameterized Queries (PreparedStatement), Connection Management.

#### Project Structure

text

LibraryManagementSystem/
├── database/
│   └── library.sql
├── src/
│   └── LibraryManagementSystem.java
└── README.md

Usa el código con precaución.

#### Setup and Installation

#### 1. Database Initialization

1. Open your preferred database tool (MySQL Workbench or phpMyAdmin).
2. Create a new database schema and execute the source scripts located inside database/library.sql.

#### 2. Connection Configuration

1. Open src/LibraryManagementSystem.java.
2. Locate the database configuration constants and update them with your local credentials:

java

private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";

Usa el código con precaución.

#### 3. Dependencies

* Download and add the **MySQL Connector/J** (.jar file) to your project's build path or dependencies structure.

#### 4. Running the Application

Compile and execute the LibraryManagementSystem main class from your IDE terminal or preferred workspace. 

#### Future Enhancements

* [ ] Implement a full Graphical User Interface (GUI) using JavaFX or Swing.
* [ ] Transition from raw JDBC to an Object-Relational Mapping (ORM) framework like Hibernate/JPA.
* [ ] Add field validation routines for input parameters and loan expiration calculations.

#### Author

**Fátima Montoya**
Information Systems Engineering Student at Universidad de Sonora
