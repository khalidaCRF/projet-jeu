package jeu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/JEU";
        String utilisateurDB = "postgres";
        String motDePasseDB = "admin";

        return DriverManager.getConnection(url, utilisateurDB, motDePasseDB);
    }
}
