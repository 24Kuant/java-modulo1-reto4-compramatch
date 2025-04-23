package com.aluracursos.compras.principal;

import com.aluracursos.compras.modelos.Compra;
import com.aluracursos.compras.modelos.Tarjeta;
import com.aluracursos.compras.util.Teclado;
import com.aluracursos.compras.util.Utileria;
import jdk.jshell.execution.Util;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Tarjeta tarjeta = new Tarjeta();
        //Menus menu = new Menus();
        Teclado teclado = new Teclado();
        int opcion = -1;

        Utileria.mensaje("Bienvenido a ComprasMatch");  //bienvenida   menu.bienvenida("Bienvenido a ComprasMatch");
        tarjeta.solicitarLimite(teclado.obtenTeclado());

        while (opcion != 0) {
            opcion = Utileria.menuPrincipal(teclado.obtenTeclado());  //Menú Principal   menu.menuMain(teclado.obtenTeclado());

            if (opcion == 0) {
                Utileria.mensaje("Gracias por usar ComprasMatch...");  //menu.bienvenida("Gracias por usar ComprasMatch...");
                break;
            } else if (Arrays.asList(2, 3, 4, 5, 8).contains(opcion) && tarjeta.ListaCompraIsEmpty()) {   //Con esta sintaxis, estás creando una lista de números y luego verificando si opcion está contenida en esa lista. ayuda obtenida de Luri.
                Utileria.mensajeCorto(String.format("%sNo%s hay compras todavía!", Utileria.red, Utileria.reset));
                continue;
            }

            if (opcion == 1) {
                Compra miCompra = new Compra();
                miCompra.realizaCompra(teclado.obtenTeclado());
                if (miCompra.getPrecio() > 0 && !miCompra.getArticulo().isBlank()) {  //si el precio es 0 (cero) o el nombre del articulo es vacio ==> SE Cancela la compra.
                    tarjeta.agregaCompra(miCompra);
                }
            } else if (opcion == 2) {
                //Lista Ordenada Ascendente por Artículo - usando Comparator
                tarjeta.ordenar(1);
            } else if (opcion == 3) {
                //Lista Ordenada Ascendente por Precio - usando Comparator
                tarjeta.ordenar(2);
            } else if (opcion == 4) {
                //Lista Ordenada Descendente por Artículo - usando Comparator
                tarjeta.ordenar(3);
            } else if (opcion == 5) {
                //Lista Ordenada Descendente por Precio - usando Comparator
                tarjeta.ordenar(4);
            } else if (opcion == 6) {
                tarjeta.imprimeLimiteCredito();
            } else if (opcion == 7) {
                tarjeta.imprimeSaldoDisponible();
            } else if (opcion == 8) {
                tarjeta.imprimeCompras();
            } else if (opcion == 9) {
                tarjeta.solicitarLimite(teclado.obtenTeclado());
            } else if (opcion == 10) {
                tarjeta.pagar(teclado.obtenTeclado());
            } else if (opcion == 11) {
                tarjeta.imprimePagos();
            } else {
                Utileria.mensajeCorto(String.format("%sOpción NO válida%s!!!%s", Utileria.red, Utileria.purple, Utileria.reset));  //menu.bienvenida("Opción NO válida!!!");
            }
        }
    }
}