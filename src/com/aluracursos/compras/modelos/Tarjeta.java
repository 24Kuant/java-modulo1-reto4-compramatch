package com.aluracursos.compras.modelos;

import com.aluracursos.compras.util.Utileria;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Tarjeta {
    private int limiteDeCredito;
    private int saldoDisponible;
    private List<Compra> listaCompras;
    private List<Integer> listaPagos;

    public Tarjeta() {
        this.listaCompras = new ArrayList<>();
        this.listaPagos = new ArrayList<>();
        this.limiteDeCredito = 0;
        this.saldoDisponible = 0;
    }

    public void solicitarLimite(Scanner teclado) {
        int totalCompras = this.limiteDeCredito - this.saldoDisponible;
        Utileria.lineaArriba();
        System.out.print("Límite de Crédito de la Tarjeta: ");
        int limite = teclado.nextInt();
        if ( this.limiteDeCredito == 0 && this.saldoDisponible == 0 ) {
            this.limiteDeCredito = limite;
            this.saldoDisponible = this.limiteDeCredito;
        } else if ( limite == this.limiteDeCredito ) {
            Utileria.mensajeCorto(String.format("%sMismo límite de crédito%s.", Utileria.blue, Utileria.reset));
        } else if ( limite >= totalCompras ) {
            //Le puede aumentar o disminuir el limite de credito, siempre y cuando el limite sea mayor o igual a lo que debe (a las compras realizadas, para que pueda pagar las compras realizadas).
            this.limiteDeCredito = limite;  //nuevo limite
            this.saldoDisponible = this.limiteDeCredito - totalCompras;
        }else {
            Utileria.mensajeCorto(String.format("Límite de crédito invalido (%s$%d%s). No puede cubrir las compras pendientes (%s$%d%s)", Utileria.red, limite, Utileria.reset, Utileria.green, totalCompras, Utileria.reset));
        }

        Utileria.lineaAbajo();
    }

    public void imprimeLimiteCredito() {
        Utileria.mensaje("Límite de crédito: $ " + this.limiteDeCredito);
    }

    public void imprimeSaldoDisponible() {
        Utileria.mensaje("Saldo disponible: $ " + this.saldoDisponible);
    }

    public void agregaCompra(Compra miCompra) {
        if (miCompra.getPrecio() <= this.saldoDisponible && miCompra.getPrecio() > 0 && !miCompra.getArticulo().isBlank()) {
            listaCompras.add(miCompra);
            this.saldoDisponible -= miCompra.getPrecio();
        } else {
            Utileria.mensajeCorto(String.format("%sLímite de crédito excedido. %sSaldo insuficiente%s!", Utileria.purple, Utileria.red, Utileria.reset));
        }
    }

    public void ordenar(int opcion) {

        if (opcion == 1 || opcion == 3) { //lista Ordenada Ascendente-Descendente por Artículo - usando Comparator
            //this.listaCompras.sort(Comparator.comparing(Compra::getArticulo));
            Comparator<Compra> articuloAsc = Comparator.comparing(Compra::getArticulo);
            if (opcion == 3) { //Lista Ordenada Descendente por Artículo - usando Comparator
                Comparator<Compra> articuloDesc = articuloAsc.reversed();
                this.listaCompras.sort(articuloDesc);
                System.out.println("Lista Ordenada Descendente por Artículo - usando Comparator: " + this.listaCompras);
            } else { //Lista Ordenada Ascendente por Artículo - usando Comparator
                this.listaCompras.sort(articuloAsc);
                System.out.println("Lista Ordenada Ascendente por Artículo - usando Comparator: " + this.listaCompras);
            }
        } else  if (opcion == 2 || opcion == 4) { //Lista Ordenada Ascendente-Descendente por Precio - usando Comparator
            //this.listaCompras.sort(Comparator.comparing(Compra::getPrecio));
            Comparator<Compra> precioAsc = Comparator.comparing(Compra::getPrecio);
            if (opcion == 4) { //Lista Ordenada Descendente por Precio - usando Comparator
                Comparator<Compra> precioDesc = precioAsc.reversed();
                this.listaCompras.sort(precioDesc);
                System.out.println("Lista Ordenada Descendente por Precio - usando Comparator: " + this.listaCompras);
            } else {  //Lista Ordenada Ascendente por Precio - usando Comparator
                this.listaCompras.sort(precioAsc);
                System.out.println("Lista Ordenada Ascendente por Precio - usando Comparator: " + this.listaCompras);
            }
        }
    }

    public void imprimeCompras() {
        int totalCompras = 0;

        Utileria.lineaArriba();
        System.out.println("=== Compras realizadas : ");
        for (Compra item: this.listaCompras) {
            System.out.println(item.toString());
            totalCompras += item.getPrecio();
        }
        System.out.println(String.format("%sTotal : %s $%d %s", Utileria.green, Utileria.blue, totalCompras, Utileria.reset));
        Utileria.lineaAbajo();
    }

    public boolean ListaCompraIsEmpty() {
        return this.listaCompras.isEmpty();
    }

    public boolean ListaPagosIsEmpty() {
        return this.listaPagos.isEmpty();
    }

    public void pagar(Scanner teclado) {
        int totalCompras = this.limiteDeCredito - this.saldoDisponible;

        if (totalCompras <= 0) {
            Utileria.mensajeCorto(String.format("%sNo%s hay pagos pendientes", Utileria.red, Utileria.reset));
        } else {
            Utileria.lineaArriba();
            System.out.print(String.format("=== %sTotal a pagar : %s $ %d %s", Utileria.green, Utileria.purple, totalCompras, Utileria.reset));
            Utileria.mensajeCorto("¿ Cuánto desea pagar ? ");
            int pago = teclado.nextInt();
            Utileria.lineaAbajo();

            if (pago > 0 && pago <= totalCompras) {
                this.listaPagos.add(pago);
                this.saldoDisponible += pago;  //importante.ojo. el saldo se incrementa, ya que se realizo un pago
            }
        }
    }

    public void imprimePagos() {

        if ( this.ListaPagosIsEmpty() ) {
            Utileria.mensajeCorto(String.format("%sNo%s ha realizado pagos todavía!", Utileria.red, Utileria.reset));
        } else {
            Utileria.lineaArriba();
            System.out.println("=== Pagos realizados : ");
            for (int i = 0; i < this.listaPagos.size(); i++) {
                System.out.println(String.format("%s%d.- $%d", Utileria.blue, i + 1, this.listaPagos.get(i)));
            }
            System.out.println(String.format("%sTotal : %s $%d %s", Utileria.green, Utileria.blue, this.totalPagos(), Utileria.reset));
            Utileria.lineaAbajo();
        }
    }

    private int totalPagos() {
        return this.listaPagos.stream().mapToInt(Integer::intValue).sum();  //codio suerido por Luri, para obtener la suma de todos los pagos, en lugar de usar un for o foreach
    }

    //setters y getters



}
