package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPostgreSQL {

    public Connection conexionBaseDatos() {
        // URL de conexión
        String url = "jdbc:postgresql://localhost:5432/gestorpeliculasomdb";
        String user = "arsenius";
        String password = "debiandell*";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("\nConectado a la base de datos en PostgreSQL!");
            } else {
                System.out.println("\nFallo en la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
