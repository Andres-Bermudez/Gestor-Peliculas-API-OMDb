package basedatos;

import modelos.Pelicula;
import presentacion.Menu;
import java.sql.*;

public class ComunicacionBaseDatos {

    public static void agregarPeliculasFavoritas(Pelicula pelicula) {
        String sql = "INSERT INTO peliculas_favoritas (titulo, año, fecha_publicacion, " +
                "duracion, genero, director, " + "escritor, actores, pais, url_imagen) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ConexionPostgreSQL conexion = new ConexionPostgreSQL();

        // Ejecutar la consulta a la base de datos
        try (Connection connection = conexion.conexionBaseDatos();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, pelicula.titulo());
            preparedStatement.setString(2, pelicula.año());
            preparedStatement.setString(3, pelicula.fechaPublicacion());
            preparedStatement.setString(4, pelicula.duracion());
            preparedStatement.setString(5, pelicula.genero());
            preparedStatement.setString(6, pelicula.director());
            preparedStatement.setString(7, pelicula.escritor());
            preparedStatement.setString(8, pelicula.actores());
            preparedStatement.setString(9, pelicula.pais());
            preparedStatement.setString(10, pelicula.imagen());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("\nPelicula agregada a favoritos!");
                Menu.menuPrincipal();
            }
        } catch (SQLException e) {
            System.out.println("\nError: No se pudo agregar tu pelicula a favoritos.");
            System.out.println(e.getMessage());
            Menu.menuPrincipal();
        }
    }

    public static void verPeliculasFavoritas() {
        ConexionPostgreSQL conexion = new ConexionPostgreSQL();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión
            connection = conexion.conexionBaseDatos();

            // Crear un statement
            statement = connection.createStatement();

            // Ejecutar la consulta
            String sql = "SELECT * FROM peliculas_favoritas;";
            resultSet = statement.executeQuery(sql);

            // Mostrar los datos
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                String año = resultSet.getString("año");
                String fechaPublicacion = resultSet.getString("fecha_publicacion");
                String duracion = resultSet.getString("duracion");
                String genero = resultSet.getString("genero");
                String director = resultSet.getString("director");
                String escritor = resultSet.getString("escritor");
                String actores = resultSet.getString("actores");
                String pais = resultSet.getString("pais");
                String imagen = resultSet.getString("url_imagen");

                String peliculaFavorita = "\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" +
                        "\nID: " + id +
                        "\nTitulo: " + titulo +
                        "\nAño: " + año +
                        "\nFecha publicacion: " + fechaPublicacion +
                        "\nDuracion: " + duracion +
                        "\nGenero: " + genero +
                        "\nDirector: " + director +
                        "\nEscritor: " + escritor +
                        "\nActores: " + actores +
                        "\nPais: " + pais +
                        "\nImagen: " + imagen +
                        "\n";
                System.out.println(peliculaFavorita);
            }
        } catch (SQLException e) {
            System.out.println("\nNo fue posible consultar los datos.");
            System.out.println(e.getMessage());
            Menu.menuPrincipal();
        }
    }

    public static void limpiarPeliculasFavoritas() {
        ConexionPostgreSQL conexion = new ConexionPostgreSQL();
        Connection connection = null;
        Statement statement = null;

        try {
            // Establecer conexión
            connection = conexion.conexionBaseDatos();

            // Crear un statement
            statement = connection.createStatement();

            // Ejecutar la consulta para truncar la tabla
            String eliminarDatos = "TRUNCATE TABLE peliculas_favoritas;";
            statement.executeUpdate(eliminarDatos);

            // Reiniciar el contador de id's
            String reiniciarContadorID = "ALTER SEQUENCE public.peliculas_favoritas_id_seq RESTART WITH 1;";
            statement.executeUpdate(reiniciarContadorID);

            System.out.println("\nSe han eliminado todos los datos de la tabla de peliculas favoritas.");

        } catch (SQLException e) {
            System.out.println("\nNo fue posible eliminar los datos.");
            System.out.println(e.getMessage());
            Menu.menuPrincipal();
        }
    }

    public static void almacenarPeliculasConsultadas(Pelicula pelicula, String peliculaConsultada) {
        String sql = "INSERT INTO peliculas_consultadas (titulo_buscado, titulo_encontrado, año, fecha_publicacion, " +
                "duracion, genero, director, " + "escritor, actores, pais, url_imagen) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ConexionPostgreSQL conexion = new ConexionPostgreSQL();

        // Ejecutar la consulta a la base de datos
        try (Connection connection = conexion.conexionBaseDatos();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, peliculaConsultada);
            preparedStatement.setString(2, pelicula.titulo());
            preparedStatement.setString(3, pelicula.año());
            preparedStatement.setString(4, pelicula.fechaPublicacion());
            preparedStatement.setString(5, pelicula.duracion());
            preparedStatement.setString(6, pelicula.genero());
            preparedStatement.setString(7, pelicula.director());
            preparedStatement.setString(8, pelicula.escritor());
            preparedStatement.setString(9, pelicula.actores());
            preparedStatement.setString(10, pelicula.pais());
            preparedStatement.setString(11, pelicula.imagen());

            int rowsInserted = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("\nError: No se pudo almecenar esta consulta.");
            System.out.println(e.getMessage());
            Menu.menuPrincipal();
        }
    }

    public static void verPeliculasConsultadas() {
        ConexionPostgreSQL conexion = new ConexionPostgreSQL();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión
            connection = conexion.conexionBaseDatos();

            // Crear un statement
            statement = connection.createStatement();

            // Ejecutar la consulta
            String sql = "SELECT * FROM peliculas_consultadas LIMIT 10;";
            resultSet = statement.executeQuery(sql);

            // Mostrar los datos
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo_encontrado");
                String año = resultSet.getString("año");
                String fechaPublicacion = resultSet.getString("fecha_publicacion");
                String duracion = resultSet.getString("duracion");
                String genero = resultSet.getString("genero");
                String director = resultSet.getString("director");
                String escritor = resultSet.getString("escritor");
                String actores = resultSet.getString("actores");
                String pais = resultSet.getString("pais");
                String imagen = resultSet.getString("url_imagen");

                String peliculaConsultada = "\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" +
                        "\nID: " + id +
                        "\nTitulo: " + titulo +
                        "\nAño: " + año +
                        "\nFecha publicacion: " + fechaPublicacion +
                        "\nDuracion: " + duracion +
                        "\nGenero: " + genero +
                        "\nDirector: " + director +
                        "\nEscritor: " + escritor +
                        "\nActores: " + actores +
                        "\nPais: " + pais +
                        "\nImagen: " + imagen +
                        "\n";
                System.out.println(peliculaConsultada);
            }
        } catch (SQLException e) {
            System.out.println("\nNo fue posible consultar los datos.");
            System.out.println(e.getMessage());
            Menu.menuPrincipal();
        }
    }
}
