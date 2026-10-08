package com.promehub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Catalogo catalogo = new Catalogo();

        int opcion = 1;
        while (opcion != 0) {
            menu();
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    leerCSV(catalogo);
                    break;
                case 2:
                
                    break;
                case 3:

                    break;
                case 4:
                    exportarCatalogoaXML(catalogo);
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 0:
                    scanner.close();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public static void menu() {
        System.out.println("1) Cargar catalogo desde CSV");
        System.out.println("2) Mostrar catalogo");
        System.out.println("3) Exportar catalogo a XML");
        System.out.println("4) Cargar catalogo desde XML");
        System.out.println("5) Exportar catalogo desde CSV");
        System.out.println("6) Buscar videojuego");
        System.out.println("7) Informacion de ficheros");
        System.out.println("0) Salir");
    }

    public static void exportarCatalogoaXML(Catalogo catalogo) {

        try {
            // Pasar Catalogo.class y Videojuego.class al contexto
            JAXBContext contexto = JAXBContext.newInstance(Catalogo.class, Videojuego.class);

            // Crear Marshaller
            Marshaller marshaller = contexto.createMarshaller();

            // Instruccion para formatear el marshaller
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Crear el xml
            marshaller.marshal(catalogo, new File("catalogo.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public static void leerCSV(Catalogo catalogo) {

        String ruta = "promehub_app/datos/csv/videojuegos.csv";
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            br.readLine(); //Leemos y saltamos la primera línea

            String linea;
            while ((linea = br.readLine()) != null) {

                if (linea.isBlank()) {
                    continue;
                }

                String[] campos = linea.split(separador);

                Videojuego videojuego = new Videojuego();

                videojuego.setId(Integer.parseInt(campos[0].trim()));
                videojuego.setTitulo(campos[1].trim());
                videojuego.setPlataforma(campos[2].trim());
                videojuego.setGenero(campos[3].trim());
                videojuego.setPrecio(Double.parseDouble(campos[4].trim()));
                videojuego.setStock(Integer.parseInt(campos[5].trim()));
                videojuego.setCodigoProveedor(Integer.parseInt(campos[6].trim()));

                catalogo.agregarVideojuego(videojuego);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el CSV");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Línea con formato incorrecto");
        }
    }

    public static void mostrarInformacionFicheros() {

        Path rutaXml = Paths.get("promehub/datos/xml");
        Path rutaCsv = Paths.get("promehub/datos/csv");

        System.out.println(" - FICHEROS XML:");

        try (Stream<Path> stream = Files.list(rutaXml)) { // Try con recursos, stream es una lista de rutas de todos los ficheros de una ruta

            stream.forEach(fichero -> { // .forEach metodo de stream que recorre todos los ficheros
                try {
                    System.out.println("Fichero: " + fichero.getFileName());
                    System.out.println("Existe: " + Files.exists(fichero));
                    System.out.println("Tamaño: " + Files.size(fichero) + " bytes");
                    System.out.println("Ruta: " + fichero.toAbsolutePath());
                    System.out.println();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" - FICHEROS CSV:");

        try (Stream<Path> stream = Files.list(rutaCsv)) {

            stream.forEach(fichero -> {
                try {
                    System.out.println("Fichero: " + fichero.getFileName());
                    System.out.println("Existe: " + Files.exists(fichero));
                    System.out.println("Tamaño: " + Files.size(fichero) + " bytes");
                    System.out.println("Ruta: " + fichero.toAbsolutePath());
                    System.out.println();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializarXML(){

        try {
            
            //Creamos el contexto para utilizar java
            JAXBContext contexto = JAXBContext.newInstance(Catalogo.class);

            //Creamos el objeto para la transformacion de xml -> java
            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            //Lee el XML y genera el objeto tipo Catalogo
            Catalogo catalogo = (Catalogo) unmarshaller.unmarshal(new File("catalogo.xml"));

            System.out.println(catalogo);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
