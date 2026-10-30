import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Sistema básico de biblioteca usando Java y MySQL.
 * Proyecto académico para practicar JDBC y consultas SQL.
 */
public class LibraryManagementSystem {

    // Cambia estos datos según tu instalación de MySQL.
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conexión a la base de datos exitosa.");

            boolean running = true;
            while (running) {
                System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
                System.out.println("1. Registrar libro");
                System.out.println("2. Mostrar libros");
                System.out.println("3. Buscar libro por título");
                System.out.println("4. Registrar préstamo");
                System.out.println("5. Mostrar préstamos");
                System.out.println("6. Salir");
                System.out.print("Selecciona una opción: ");

                String option = scanner.nextLine();

                switch (option) {
                    case "1" -> addBook(connection);
                    case "2" -> showBooks(connection);
                    case "3" -> searchBook(connection);
                    case "4" -> registerLoan(connection);
                    case "5" -> showLoans(connection);
                    case "6" -> running = false;
                    default -> System.out.println("Opción no válida.");
                }
            }
            System.out.println("Programa finalizado.");
        } catch (SQLException error) {
            System.out.println("No fue posible conectar o consultar la base de datos.");
            System.out.println("Detalle: " + error.getMessage());
        }
    }

    private static void addBook(Connection connection) throws SQLException {
        System.out.print("Título: ");
        String title = scanner.nextLine().trim();
        System.out.print("Autor: ");
        String author = scanner.nextLine().trim();

        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("El título y el autor son obligatorios.");
            return;
        }

        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.executeUpdate();
            System.out.println("Libro registrado correctamente.");
        }
    }

    private static void showBooks(Connection connection) throws SQLException {
        String sql = "SELECT id, title, author, available FROM books ORDER BY id";
        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            System.out.println("\n--- LIBROS ---");
            while (result.next()) {
                String status = result.getBoolean("available") ? "Disponible" : "Prestado";
                System.out.printf("%d | %s | %s | %s%n",
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("author"),
                        status);
            }
        }
    }

    private static void searchBook(Connection connection) throws SQLException {
        System.out.print("Escribe el título o una parte del título: ");
        String title = scanner.nextLine().trim();

        String sql = "SELECT id, title, author, available FROM books WHERE title LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + title + "%");
            try (ResultSet result = statement.executeQuery()) {
                boolean found = false;
                while (result.next()) {
                    found = true;
                    String status = result.getBoolean("available") ? "Disponible" : "Prestado";
                    System.out.printf("%d | %s | %s | %s%n",
                            result.getInt("id"),
                            result.getString("title"),
                            result.getString("author"),
                            status);
                }
                if (!found) {
                    System.out.println("No se encontraron libros.");
                }
            }
        }
    }

    private static void registerLoan(Connection connection) throws SQLException {
        System.out.print("ID del libro: ");
        int bookId;
        try {
            bookId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException error) {
            System.out.println("El ID debe ser un número.");
            return;
        }

        System.out.print("Nombre del estudiante: ");
        String student = scanner.nextLine().trim();
        if (student.isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return;
        }

        String checkSql = "SELECT available FROM books WHERE id = ?";
        try (PreparedStatement check = connection.prepareStatement(checkSql)) {
            check.setInt(1, bookId);
            try (ResultSet result = check.executeQuery()) {
                if (!result.next()) {
                    System.out.println("No existe un libro con ese ID.");
                    return;
                }
                if (!result.getBoolean("available")) {
                    System.out.println("Ese libro ya está prestado.");
                    return;
                }
            }
        }

        connection.setAutoCommit(false);
        try {
            String loanSql = "INSERT INTO loans (book_id, student_name, loan_date) VALUES (?, ?, ?)";
            try (PreparedStatement loan = connection.prepareStatement(loanSql)) {
                loan.setInt(1, bookId);
                loan.setString(2, student);
                loan.setDate(3, Date.valueOf(LocalDate.now()));
                loan.executeUpdate();
            }

            String updateSql = "UPDATE books SET available = FALSE WHERE id = ?";
            try (PreparedStatement update = connection.prepareStatement(updateSql)) {
                update.setInt(1, bookId);
                update.executeUpdate();
            }

            connection.commit();
            System.out.println("Préstamo registrado correctamente.");
        } catch (SQLException error) {
            connection.rollback();
            throw error;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private static void showLoans(Connection connection) throws SQLException {
        String sql = """
                SELECT loans.id, books.title, loans.student_name,
                       loans.loan_date, loans.returned
                FROM loans
                INNER JOIN books ON loans.book_id = books.id
                ORDER BY loans.id
                """;

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            System.out.println("\n--- PRÉSTAMOS ---");
            while (result.next()) {
                String status = result.getBoolean("returned") ? "Devuelto" : "Pendiente";
                System.out.printf("%d | %s | %s | %s | %s%n",
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("student_name"),
                        result.getDate("loan_date"),
                        status);
            }
        }
    }
}
