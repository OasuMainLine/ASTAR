/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.astar;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orlando Andres Sandi Ulate (C07355)
 */
public class Astar {

    ArrayList<Nodo> abiertos, cerrados;

    char[][] matriz;
    BiFunction<Punto, Punto, Integer> heuristico;
    Punto objetivo;
    Punto inicio;
    char[][] matrizPasos;

    public Astar(Laberinto laberinto, BiFunction<Punto, Punto, Integer> heuristico) {
        abiertos = new ArrayList<>();
        cerrados = new ArrayList<>();

        this.matriz = laberinto.matriz;
        this.heuristico = heuristico;
        this.objetivo = laberinto.objetivo;
        this.inicio = laberinto.inicio;

        matrizPasos = new char[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            matrizPasos[i] = Arrays.copyOf(matriz[i], matriz[i].length);
        }

    }

    public Stack<Nodo> solucionar() throws NoSolutionException {
        Nodo nodoInicial = new Nodo(inicio, 0, null, Laberinto.INICIO);

        abiertos.add(nodoInicial);

        while (abiertos.size() > 0) {

            Nodo nodoActual = mejor();
            cerrados.add(nodoActual);

            abiertos.remove(nodoActual);

            if (isObjetivo(nodoActual)) {

                Stack<Nodo> solucion = new Stack();

                Nodo explorador = nodoActual;

                while (explorador != null) {
                    solucion.push(explorador);
                    explorador = explorador.padre;

                }
                return solucion;
            }

            expandir(nodoActual);

            for (Nodo nodo : nodoActual.getSucesores()) {

                if (abiertos.indexOf(nodo) == -1 && cerrados.indexOf(nodo) == -1) {
                    nodo.setPadre(nodoActual);
                    abiertos.add(nodo);
                } else {

                    if (nodo.padre != null && nodo.padre.costeG > nodoActual.costeG) {
                        nodo.padre = nodoActual;
                    }
                }

            }

        }

        throw new NoSolutionException("Este laberinto no cuenta con solucion");

    }

    // g(n) + h(n) = f(n)


    public void expandir(Nodo nodoActual) {
        Punto coordenadasActual = nodoActual.coordenadas;

        Punto arriba, abajo, izq, der;

        arriba = new Punto(coordenadasActual.X, coordenadasActual.Y + 1);

        abajo = new Punto(coordenadasActual.X, coordenadasActual.Y - 1);

        izq = new Punto(coordenadasActual.X - 1, coordenadasActual.Y);

        der = new Punto(coordenadasActual.X + 1, coordenadasActual.Y);

        Punto[] puntos = new Punto[]{arriba, abajo, izq, der};

        for (Punto punto : puntos) {

            if (puntoValido(punto)) {
                Nodo sucesor = new Nodo(punto, nodoActual.costeG + 1, null, matriz[punto.X][punto.Y]);

                if (!isEqualAntecesores(sucesor)) {

                    nodoActual.sucesores.add(sucesor);

                    printPaso(sucesor.coordenadas, coordenadasActual);

                }

            }
        }

    }
    public boolean puntoValido(Punto punto) {
        return punto.X < matriz.length && punto.Y < matriz[0].length && punto.Y >= 0 && punto.X >= 0 && matriz[punto.X][punto.Y] != Laberinto.OBSTACULO;
    }

    private boolean isEqualAntecesores(Nodo nodo) {

        Nodo explorador = nodo.padre;

        while (explorador != null) {
            if (explorador.equals(nodo)) {
                return true;
            }
            explorador = explorador.padre;
        }

        return false;
    }

    private Nodo mejor() {
        Nodo mejorNodo = abiertos.get(0);

        for (Nodo abierto : abiertos) {

            if (f(abierto) < f(mejorNodo)) {
                mejorNodo = abierto;
            }
        }

        return mejorNodo;
    }

    private int f(Nodo nodo) {

        return nodo.costeG + heuristico.apply(nodo.coordenadas, objetivo);
    }

    private boolean isObjetivo(Nodo nodo) {
        return nodo.coordenadas.equals(objetivo);
    }

    private void printPaso(Punto coordenadas, Punto actual) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        if (!coordenadas.equals(this.inicio) && !coordenadas.equals(this.objetivo)) {
            matrizPasos[coordenadas.X][coordenadas.Y] = Laberinto.FRONTERA;
        }

        for (Nodo nodo : cerrados) {
            if (!nodo.coordenadas.equals(this.inicio) && !nodo.coordenadas.equals(this.objetivo)) {
                matrizPasos[nodo.coordenadas.X][nodo.coordenadas.Y] = Laberinto.BLANCO;
            }
        }

        if (!actual.equals(this.inicio) && !actual.equals(this.objetivo)) {
            matrizPasos[actual.X][actual.Y] = Laberinto.ACTUAL;
        }


        String laberinto = "";

        for (int i = 0; i < this.matrizPasos[0].length; i++) {
            laberinto += " - ";
        }

        laberinto += "\n";
        for (int fila = 0; fila < this.matrizPasos.length; fila++) {
            laberinto += "|";
            for (int columna = 0; columna < this.matrizPasos[fila].length; columna++) {
                laberinto += " " + this.matrizPasos[fila][columna] + " ";
            }
            laberinto += "|\n";
        }

        for (int i = 0; i < this.matrizPasos[0].length; i++) {
            laberinto += " - ";
        }
        System.out.println(laberinto);

        try {
            sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(Astar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
