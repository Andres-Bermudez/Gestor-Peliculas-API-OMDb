package interfacesusuario;

import basedatos.RecepcionDatos;
import buscadorpeliculas.ConsultaAPI;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int eleccionUsuario = -1;
        String menu = """
                      \n::::::::::::::::::::: Bienvenido a ScreenMatch ::::::::::::::::::::::::
                            1. Buscar una pelicula.
                            2. Ver mis peliculas consultadas (Ultimas 10).
                            3. Mis pellculas favoritas.
                            0. Salir
                      """;
        System.out.println(menu);
        System.out.print("Tu eleccion: ");
        try {
            eleccionUsuario = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nSolo puedes elegir entre las opciones disponibles.");
        }

        switch (eleccionUsuario) {
            case 0:
                System.exit(0);
                break;
            case 1:
                buscarPeliculas();
                break;
            case 2:
                verPeliculasConsultadas();
                break;
            case 3:
                misPeliculasFavoritas();
                break;
            default:
                System.out.println("\nSolo puedes elegir entre las opciones disponibles.");
                break;
        }
    }

    private static void buscarPeliculas() {
        Scanner sc = new Scanner(System.in);
        String peliculaConsultada = "";

        while (true) {
            System.out.println("\nBuscando.....");
            System.out.print("Ingresa el nombre de la pelicula que buscas: ");
            peliculaConsultada = sc.nextLine();

            if (peliculaConsultada.equalsIgnoreCase("salir")) {
                System.exit(0);
            } else if (peliculaConsultada.isEmpty()) {
                System.out.println("\nTu consulta esta vacia.");
            } else {
                int eleccionUsuario = -1;

                while (eleccionUsuario < 0 || eleccionUsuario > 3) {
                    String menuOpciones = """
                            \n1. Agregar a peliculas favoritas.
                            2. Buscar otra pelicula.
                            3. Volver al menu principal.
                            0. Salir.
                            """;
                    System.out.println("\nBuscando tu pelicula.....");
                    ConsultaAPI.consultarOmdbAPI(peliculaConsultada);

                    System.out.println(menuOpciones);
                    System.out.print("Tu eleccion: ");
                    try {
                        eleccionUsuario = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("\n***** Recuerda elegir solo entre las opciones disponibles *****");
                        sc.nextLine();
                        menuPrincipal();
                    }
                }
                switch (eleccionUsuario) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        // Pendiente por enviar el objeto pelicula para guardarlo en base de datos.
                        //RecepcionDatos.agregarPeliculasFavoritas();
                        break;
                    case 2:
                        buscarPeliculas();
                        break;
                    case 3:
                        menuPrincipal();
                        break;
                    default:
                        System.out.println("\nSolo puedes elegir entre las opciones disponibles.");
                        break;
                }
            }
        }
    }

    private static void verPeliculasConsultadas() {

    }

    private static void misPeliculasFavoritas() {

    }
}
