package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionPostgreSQL {
    public void conexionBaseDatos() {
        // URL de conexión
        String url = "jdbc:postgresql://localhost:5432/testdb";
        String user = "testuser";
        String password = "testpassword";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establecer la conexión
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida.");

            // Crear una declaración
            stmt = conn.createStatement();

            // Ejecutar una consulta
            String sql = "SELECT id, name FROM test_table";
            rs = stmt.executeQuery(sql);

            // Procesar el resultado
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("ID: %d, Name: %s%n", id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
