package database;

import models.Book;
import models.LibraryItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    private static DatabaseHelper databaseHelper;

    private Connection connection;

    public static synchronized DatabaseHelper getInstance() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper();
        }
        return databaseHelper;
    }

    private DatabaseHelper() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library_system?useSSL=false",
                    "root", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void aud(LibraryItem item) {

        try {

            if (item.getClass() == Book.class) {
                Statement statement = connection.createStatement();
                Book book = (Book) item;
                statement.executeUpdate("INSERT INTO book(isbn, title, sector, published_date, burrowed_date, " +
                        "reader_id, author, publisher, pages)" +
                        "VALUES (" + book.getIsbn() +"," + book.getTitle() + "," + book.getSector() +","+
                        book.getPublishedDate() + "," + book.getDateTime() + "," + book.getReader() +","+
                        book.getAuthors() + "," + book.getPublisher()+ "," + book.getPages()+")");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
