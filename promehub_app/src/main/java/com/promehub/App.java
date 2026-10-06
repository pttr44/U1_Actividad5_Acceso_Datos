package com.promehub;

import java.io.File;
import java.util.Scanner;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 1;
        while (opcion != 0) {
            menu();
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

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

}
