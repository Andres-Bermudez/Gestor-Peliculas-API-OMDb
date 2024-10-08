package buscadorpeliculas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import presentacion.Menu;
import modelos.Pelicula;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaAPI extends DatosAutenticacion {
    private static final List<Pelicula> listaPeliculas = new ArrayList<>();

    public static void consultarOmdbAPI(String peliculaConsultada)  {

        // Recibimos la pelicula a consultar y le hacemos un formateO
        peliculaConsultada.toLowerCase().replace(" ", "+");

        // Crear la URL a consultar
        String URL = "http://www.omdbapi.com/?t=" + peliculaConsultada
                + "&apikey=" + DatosAutenticacion.apiKeyOmdb;

        // Crear una instancia de OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Construir la solicitud
        Request request = new Request.Builder().url(URL).build();

        try {
            // Ejecutar la solicitud
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String json = response.body().string();
                System.out.println("Hemos encontrado tu pelicula:");

                // Convertir la respuesta JSON en un objeto JAVA.
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Pelicula pelicula = gson.fromJson(json, Pelicula.class);

                // Agregar la respuesta a nuestra lista de peliculas consultadas para posteriormente
                // agregarlas a un archivo .txt
                listaPeliculas.add(pelicula);

                // Enviar el objeto pelicula a la clase Menu, para posteriormente almacenarla
                // en la base de datos.
                Menu.recibirPeliculaConsultada(pelicula, peliculaConsultada);

                // Imprimir la respuesta
                System.out.println(pelicula);

                // Generar un archivo .txt con las peliculas consultadas.
                FileWriter fileWriter = new FileWriter("almacenamiento/ultimasPeliculasConsultadas.txt");
                fileWriter.write(gson.toJson(listaPeliculas));
                fileWriter.close();
            } else {
                System.out.println("Consulta erronea: " + response);
            }
        } catch (IOException e) {
            System.out.println("Error: No fue posible hacer la consulta a la API de OMDB.");
            Menu.menuPrincipal();
        }
    }
}
