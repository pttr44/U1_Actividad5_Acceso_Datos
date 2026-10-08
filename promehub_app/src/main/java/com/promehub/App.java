package com.promehub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class App {

    // RUTAS DE LOS FICHEROS
    private static final String RUTA_CSV_ORIGINAL
            = "U1_Actividad5_Acceso_Datos/promehub_app/datos/csv/videojuegos.csv";

    private static final String RUTA_CSV_EXPORTADO
            = "U1_Actividad5_Acceso_Datos/promehub_app/datos/csv/catalogo.csv";

    private static final String RUTA_XML
            = "U1_Actividad5_Acceso_Datos/promehub_app/datos/xml/catalogo.xml";

    private static final String CARPETA_CSV
            = "U1_Actividad5_Acceso_Datos/promehub_app/datos/csv";

    private static final String CARPETA_XML
            = "U1_Actividad5_Acceso_Datos/promehub_app/datos/xml";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Catalogo catalogo = new Catalogo();

        int opcion = -1;

        while (opcion != 0) {

            menu();

            opcion = scanner.nextInt();

            switch (opcion) {

                case 1:
                    leerCSV(catalogo);
                    break;

                case 2:
                    catalogo.mostrarCatalogo();
                    break;

                case 3:
                    exportarCatalogoaXML(catalogo);
                    break;

                case 4:

                    Catalogo catalogoXml = cargarCatalogodesdeXml();

                    if (catalogoXml != null) {
                        catalogo = catalogoXml;
                    }

                    break;

                case 5:
                    exportarCatalogoaCSV(catalogo);
                    break;

                case 6:
                    buscarVideojuegoPorId(scanner, catalogo);
                    break;

                case 7:
                    mostrarInformacionFicheros();
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }

    // MENÚ
    public static void menu() {

        System.out.println();
        System.out.println("1) Cargar catalogo desde CSV");
        System.out.println("2) Mostrar catalogo");
        System.out.println("3) Exportar catalogo a XML");
        System.out.println("4) Cargar catalogo desde XML");
        System.out.println("5) Exportar catalogo a CSV");
        System.out.println("6) Buscar videojuego");
        System.out.println("7) Informacion de ficheros");
        System.out.println("0) Salir");

        System.out.print("Opcion: ");
    }

    // CARGAR CSV -> JAVA
    public static void leerCSV(Catalogo catalogo) {

        String separador = ",";

        try (BufferedReader br
                = new BufferedReader(new FileReader(RUTA_CSV_ORIGINAL))) {

            // Saltamos la cabecera
            br.readLine();

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.isBlank()) {
                    continue;
                }

                String[] campos = linea.split(separador);

                Videojuego videojuego = new Videojuego();

                videojuego.setId(
                        Integer.parseInt(campos[0].trim())
                );

                videojuego.setTitulo(
                        campos[1].trim()
                );

                videojuego.setPlataforma(
                        campos[2].trim()
                );

                videojuego.setGenero(
                        campos[3].trim()
                );

                videojuego.setPrecio(
                        Double.parseDouble(campos[4].trim())
                );

                videojuego.setStock(
                        Integer.parseInt(campos[5].trim())
                );

                videojuego.setCodigoProveedor(
                        campos[6].trim()
                );

                catalogo.agregarVideojuego(videojuego);
            }

            System.out.println(
                    "Catalogo cargado desde CSV correctamente."
            );

        } catch (IOException e) {

            System.err.println(
                    "Error al leer el CSV: " + e.getMessage()
            );

        } catch (NumberFormatException
                | ArrayIndexOutOfBoundsException e) {

            System.err.println(
                    "Linea con formato incorrecto: " + e.getMessage()
            );
        }
    }

    // JAVA -> XML
    public static void exportarCatalogoaXML(Catalogo catalogo) {

        try {

            JAXBContext contexto
                    = JAXBContext.newInstance(
                            Catalogo.class,
                            Videojuego.class
                    );

            Marshaller marshaller
                    = contexto.createMarshaller();

            marshaller.setProperty(
                    Marshaller.JAXB_FORMATTED_OUTPUT,
                    true
            );

            marshaller.marshal(
                    catalogo,
                    new File(RUTA_XML)
            );

            System.out.println(
                    "Catalogo exportado a XML correctamente."
            );

        } catch (JAXBException e) {

            System.err.println(
                    "Error al exportar a XML: "
                    + e.getMessage()
            );
        }
    }

    // XML -> JAVA
    public static Catalogo cargarCatalogodesdeXml() {

        try {

            JAXBContext contexto
                    = JAXBContext.newInstance(
                            Catalogo.class,
                            Videojuego.class
                    );

            Unmarshaller unmarshaller
                    = contexto.createUnmarshaller();

            Catalogo catalogo
                    = (Catalogo) unmarshaller.unmarshal(
                            new File(RUTA_XML)
                    );

            System.out.println(
                    "Catalogo cargado desde XML correctamente."
            );

            return catalogo;

        } catch (JAXBException e) {

            System.err.println(
                    "Error al cargar el XML: "
                    + e.getMessage()
            );

            return null;
        }
    }

    // JAVA -> CSV
    public static void exportarCatalogoaCSV(Catalogo catalogo) {

        try (BufferedWriter bw
                = new BufferedWriter(
                        new FileWriter(RUTA_CSV_EXPORTADO)
                )) {

            // Cabecera
            bw.write(
                    "id,titulo,plataforma,genero,precio,stock,codigoProveedor"
            );

            bw.newLine();

            for (Videojuego game : catalogo.getCatalogo()) {

                String datos
                        = game.getId() + ","
                        + game.getTitulo() + ","
                        + game.getPlataforma() + ","
                        + game.getGenero() + ","
                        + game.getPrecio() + ","
                        + game.getStock() + ","
                        + game.getCodigoProveedor();

                bw.write(datos);
                bw.newLine();
            }

            System.out.println(
                    "Archivo CSV creado correctamente."
            );

        } catch (IOException e) {

            System.err.println(
                    "Error al escribir el CSV: "
                    + e.getMessage()
            );
        }
    }

    // BUSCAR VIDEOJUEGO POR ID
    public static void buscarVideojuegoPorId(
            Scanner scanner,
            Catalogo catalogo) {

        System.out.print(
                "Introduce el ID del videojuego a buscar: "
        );

        int id = scanner.nextInt();

        Videojuego juegoEncontrado
                = catalogo.buscarPorId(id);

        if (juegoEncontrado != null) {

            System.out.println();
            System.out.println("Videojuego encontrado:");
            System.out.println(juegoEncontrado);

        } else {

            System.out.println(
                    "No se encontro ningun videojuego con el ID: "
                    + id
            );
        }
    }

    // INFORMACIÓN DE LOS FICHEROS
    public static void mostrarInformacionFicheros() {

        Path rutaXml
                = Paths.get(CARPETA_XML);

        Path rutaCsv
                = Paths.get(CARPETA_CSV);

        System.out.println();
        System.out.println("----- FICHEROS XML -----");

        try (Stream<Path> stream
                = Files.list(rutaXml)) {

            stream.forEach(fichero -> {

                try {

                    System.out.println(
                            "Fichero: "
                            + fichero.getFileName()
                    );

                    System.out.println(
                            "Existe: "
                            + Files.exists(fichero)
                    );

                    System.out.println(
                            "Tamaño: "
                            + Files.size(fichero)
                            + " bytes"
                    );

                    System.out.println(
                            "Ruta: "
                            + fichero.toAbsolutePath()
                    );

                    System.out.println();

                } catch (IOException e) {

                    System.err.println(
                            "Error al obtener informacion del fichero: "
                            + e.getMessage()
                    );
                }
            });

        } catch (IOException e) {

            System.err.println(
                    "Error al acceder a la carpeta XML: "
                    + e.getMessage()
            );
        }

        System.out.println();
        System.out.println("----- FICHEROS CSV -----");

        try (Stream<Path> stream
                = Files.list(rutaCsv)) {

            stream.forEach(fichero -> {

                try {

                    System.out.println(
                            "Fichero: "
                            + fichero.getFileName()
                    );

                    System.out.println(
                            "Existe: "
                            + Files.exists(fichero)
                    );

                    System.out.println(
                            "Tamaño: "
                            + Files.size(fichero)
                            + " bytes"
                    );

                    System.out.println(
                            "Ruta: "
                            + fichero.toAbsolutePath()
                    );

                    System.out.println();

                } catch (IOException e) {

                    System.err.println(
                            "Error al obtener informacion del fichero: "
                            + e.getMessage()
                    );
                }
            });

        } catch (IOException e) {

            System.err.println(
                    "Error al acceder a la carpeta CSV: "
                    + e.getMessage()
            );
        }
    }
}
