package com.promehub;

import java.util.Scanner;


public class App {
    public static void main( String[] args ){
        Scanner scanner = new Scanner(System.in);
        int opcion = 1;
        while(opcion != 0){
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

    public static void menu(){
        System.out.println("1) Cargar catalogo desde CSV");
        System.out.println("2) Mostrar catalogo");
        System.out.println("3) Exportar catalogo a XML");
        System.out.println("4) Cargar catalogo desde XML");
        System.out.println("5) Exportar catalogo desde CSV");
        System.out.println("6) Buscar videojuego");
        System.out.println("7) Informacion de ficheros");
        System.out.println("0) Salir");
    }
}
