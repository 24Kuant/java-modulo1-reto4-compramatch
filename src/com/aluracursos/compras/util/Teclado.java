package com.aluracursos.compras.util;

import java.util.Scanner;

public class Teclado {
    Scanner teclado;

    public Teclado() {
        this.teclado = new Scanner(System.in);
    }

    public Scanner obtenTeclado() {
        return teclado;
    }

    public void cierraTeclado() {
        teclado.close();
    }
}
