package com.aluracursos.compras.modelos;

import com.aluracursos.compras.util.Utileria;

import java.util.Scanner;

public class Compra {
    private String articulo;
    private int precio;


    @Override
    public String toString() {
        return String.format("%s - %s$%s%d ", this.articulo, Utileria.green, Utileria.reset, this.precio);
    }

    public void realizaCompra(Scanner teclado) {
        Utileria.lineaArriba();

        String articulo = "";
        while (articulo.isBlank()) {
            System.out.print("==Nombre del producto a comprar: ");
            articulo = teclado.next();
        }
        int precio = -1;
        while (precio < 0) {  //si el precio es 0 (cero), se cancela la compra
            System.out.print("==Precio: ");
            precio = teclado.nextInt();
        }

        Utileria.lineaAbajo();

        this.articulo = articulo;
        this.precio = precio;
    }

    //getters

    public String getArticulo() {
        return articulo;
    }
    public int getPrecio() {
        return precio;
    }
}
