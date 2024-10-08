package presentacion;

import basedatos.ComunicacionBaseDatos;
import buscadorpeliculas.ConsultaAPI;
import modelos.Pelicula;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Pelicula pelicula = null;
    private static String peliculaConsultada = "";

    public static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int eleccionUsuario = -1;
        String menu = """
                      \n::::::::::::::::::::: Bienvenido a ScreenMatch ::::::::::::::::::::::::
                            1. Buscar una pelicula.
                            2. Ver mis peliculas consultadas.
                            3. Mis peliculas favoritas.
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
                        ComunicacionBaseDatos.agregarPeliculasFavoritas(Menu.pelicula);
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

    private static void misPeliculasFavoritas() {
        ComunicacionBaseDatos.verPeliculasFavoritas();

        String opciones = """
                          \n1. Volver al menu principal.
                          2. Limpiar lista de peliculas favoritas.
                          0. Salir.
                          """;
        Scanner sc = new Scanner(System.in);
        int eleccionUsuario = -1;

        System.out.println(opciones);
        System.out.print("Tu eleccion: ");

        try {
            eleccionUsuario = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\n***** Recuerda elegir solo entre las opciones disponibles *****");
            sc.nextLine();
            menuPrincipal();
        }

        switch (eleccionUsuario) {
            case 0:
                System.exit(0);
                break;
            case 1:
                Menu.menuPrincipal();
                break;
            case 2:
                ComunicacionBaseDatos.limpiarPeliculasFavoritas();
                Menu.menuPrincipal();
                break;
            default:
                System.out.println("\nSolo puedes elegir entre las opciones disponibles.");
                Menu.menuPrincipal();
                break;
        }
    }

    public static void recibirPeliculaConsultada(Pelicula pelicula, String peliculaConsultada) {
        Menu.pelicula = pelicula;
        Menu.peliculaConsultada = peliculaConsultada;
        ComunicacionBaseDatos.almacenarPeliculasConsultadas(pelicula, peliculaConsultada);
    }

    private static void verPeliculasConsultadas() {
        ComunicacionBaseDatos.verPeliculasConsultadas();
        Menu.menuPrincipal();
    }
}
