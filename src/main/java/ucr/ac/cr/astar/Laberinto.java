/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.astar;

import java.awt.Dimension;
import java.util.Random;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 *
 * @author Orlando Andres Sandi Ulate (C07355)
 */
public class Laberinto {

    Dimension dimension;

    char[][] matriz;

    int probabilidad;
    Punto inicio, objetivo;
    long semilla;
    Random random;

    final static char OBSTACULO = '*';
    final static char OBJETIVO = 'G';
    final static char BLANCO = ' ';
    final static char TRAYECTORIA = '+';
    final static char INICIO = 'I';
    final static char FRONTERA = 'O';
    final static char ACTUAL = 'X';

    public Laberinto(Dimension dimension, int probabilidad) {
        this.dimension = dimension;
        this.probabilidad = probabilidad;

        this.semilla = new Random().nextLong();
        this.random = new Random(this.semilla);
    }

    public Laberinto(Dimension dimension, int probabilidad, long semilla) {
        this.dimension = dimension;
        this.probabilidad = probabilidad;
        this.semilla = semilla;
        this.random = new Random(this.semilla);
    }

    public void generarLaberinto() throws NoSolutionException {

        this.matriz = new char[this.dimension.width][this.dimension.height];

        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                matriz[fila][columna] = BLANCO;
            }

        }

        inicio = new Punto(random.nextInt(dimension.width), random.nextInt(dimension.height));
        objetivo = new Punto(random.nextInt(dimension.width), random.nextInt(dimension.height));

        while (inicio.equals(objetivo)) {
            objetivo = new Punto(random.nextInt(dimension.width), random.nextInt(dimension.height));
        }

        matriz[inicio.getX()][inicio.getY()] = INICIO;
        matriz[objetivo.getX()][objetivo.getY()] = OBJETIVO;

        for (int fila = 0; fila < this.matriz.length; fila++) {
            for (int columna = 0; columna < this.matriz[fila].length; columna++) {

                int resultado = random.nextInt(101);
                if (resultado < probabilidad) {
                    matriz[fila][columna] = OBSTACULO;
                }
            }
        }

        for (int fila = 0; fila < this.matriz.length; fila++) {
            for (int columna = 0; columna < this.matriz[fila].length; columna++) {

                Punto coordenadaActual = new Punto(fila, columna);

                if (matriz[fila][columna] == OBSTACULO && (coordenadaActual.equals(inicio) || coordenadaActual.equals(objetivo))) {

                    throw new NoSolutionException("El laberinto con semilla " + semilla + " no tiene solución");
                }
            }
        }


    }

    public void pintarSolucion(Stack<Nodo> solucion) {


        Nodo paso = solucion.pop();

        while (!solucion.peek().coordenadas.equals(objetivo)) {
            paso = solucion.pop();
            matriz[paso.coordenadas.X][paso.coordenadas.Y] = Laberinto.TRAYECTORIA;

        }
        System.out.println("Solución Encontrada\nCoste=" + solucion.peek().costeG);
        System.out.println(toString());
    }
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public int getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(int probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Punto getInicio() {
        return inicio;
    }

    public void setInicio(Punto inicio) {
        this.inicio = inicio;
    }

    public Punto getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Punto objetivo) {
        this.objetivo = objetivo;
    }


    public long getSemilla() {
        return semilla;
    }

    public void setSemilla(long semilla) {
        this.semilla = semilla;
    }


    @Override
    public String toString() {
        String laberinto = "Laberinto " + this.semilla + "\n";

        for (int i = 0; i < this.matriz[0].length; i++) {
            laberinto += " - ";
        }

        laberinto += "\n";
        for (int fila = 0; fila < this.matriz.length; fila++) {
            laberinto += "|";
            for (int columna = 0; columna < this.matriz[fila].length; columna++) {
                laberinto += " " + this.matriz[fila][columna] + " ";
            }
            laberinto += "|\n";
        }

        for (int i = 0; i < this.matriz[0].length; i++) {
            laberinto += " - ";
        }
        return laberinto;
    }

}
