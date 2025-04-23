package com.aluracursos.compras.util;

import java.util.Scanner;

public class Utileria {

    public static String green  = "\033[32m";
    public static String blue   = "\033[34m";
    public static String purple = "\033[35m";
    public static String red    = "\033[31m";
    public static String reset  = "\u001B[0m";

    public static void lineaArriba() {
        System.out.println("=========================================");
        System.out.println("=======");
    }

    public static void lineaAbajo() {
        System.out.println("=======");
        System.out.println("=========================================");
    }

    public static void mensaje(String mensaje) {
        System.out.print("\n");
        System.out.println("=============================");
        System.out.println("======");
        System.out.println("=== " + mensaje);
        System.out.println("======");
        System.out.println("=============================");
        System.out.print("\n");
    }

    public  static void mensajeCorto(String mensaje) {
        System.out.println("\n");
        System.out.println("======");
        System.out.println("=== " + mensaje);
        System.out.print("======");
        System.out.print("\n");
    }

    public static void obtenMontoPago(String mensaje, Scanner teclado) {
        System.out.println("\n");
        System.out.println("======");
        System.out.println("=== " + mensaje);
        System.out.print("======");
        System.out.print("\n");
    }

    public static int menuPrincipal(Scanner teclado) {
        System.out.print("\n");
        Utileria.lineaArriba();
        System.out.println("=== " + blue + "Menú " + reset);
        System.out.println("=== " + purple + " 1." + reset + " Comprar");
        System.out.println("=== " + purple + " 2." + reset + " Ordenar por artículo - ascendente");
        System.out.println("=== " + purple + " 3." + reset + " Ordernar por precio - ascendente");
        System.out.println("=== " + purple + " 4." + reset + " Ordenar por artículo - descendente");
        System.out.println("=== " + purple + " 5." + reset + " Ordernar por precio - descendente");
        System.out.println("=== " + purple + " 6." + reset + " Límite de crédito");
        System.out.println("=== " + purple + " 7." + reset + " Saldo o crédito disponible");
        System.out.println("=== " + purple + " 8." + reset + " Mostrar todas las compras");
        System.out.println("=== " + purple + " 9." + reset + " Cambiar límite de crédito");
        System.out.println("=== " + purple + "10." + reset + " Pagar tarjeta");
        System.out.println("=== " + purple + "11." + reset + " Mostrar todos los pagos de tarjeta");
        System.out.println("=== " + blue + "0." + reset + " Salir");
        Utileria.lineaAbajo();
        System.out.print("\n");

        return teclado.nextInt();
    }
}
