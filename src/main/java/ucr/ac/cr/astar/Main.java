/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ucr.ac.cr.astar;

import java.awt.Dimension;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orlando Andres Sandi Ulate (C07355)
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        startMenu();

    }

    public static void startMenu() {

        int choice = 0;

        Scanner in = new Scanner(System.in);

        while (true) {

            String menu = "Aplicación Algoritmo A*"
                    + "\nIngrese el número de una de las siguientes opciones"
                    + "\n1-Generar laberinto"
                    + "\n2-Salir";

            System.out.println(menu);

            try {
                choice = Integer.parseInt(in.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Ingrese las dimensiones del laberinto\nAncho:");

                        try {
                            int width = Integer.parseInt(in.nextLine());

                            System.out.println("Alto:");

                            int height = Integer.parseInt(in.nextLine());

                            Dimension dimension = new Dimension(height, width);

                            System.out.println("Ingrese la probabilidad de obstaculos");

                            int prob = Integer.parseInt(in.nextLine());

                            System.out.println("Ingrese la semilla (Presione enter para aleatorio)");

                            String semillaStr = in.nextLine();

                            Laberinto laberinto = null;
                            if (semillaStr.isEmpty()) {

                                laberinto = new Laberinto(dimension, prob);
                            } else {
                                laberinto = new Laberinto(dimension, prob, Long.parseLong(semillaStr));
                            }


                            try {
                                laberinto.generarLaberinto();

                                System.out.println(laberinto);

                                System.out.println("Seleccione heuristico" + "\n1-H=0\n2-MANHATTAN\n3-EUCLIDEO");

                                int heuristicoChoice = Integer.parseInt(in.nextLine());

                                BiFunction<Punto, Punto, Integer> heuristico = null;
                                switch (heuristicoChoice) {
                                    case 1:
                                        heuristico = Heuristico.H0;
                                        break;
                                    case 2:
                                        heuristico = Heuristico.MANHATTAN;
                                        break;

                                    case 3:
                                        heuristico = Heuristico.EUCLIDEA;
                                    default:
                                        heuristico = Heuristico.H0;
                                        break;
                                }

                                Astar astar = new Astar(laberinto, heuristico);

                                Stack<Nodo> solucion = astar.solucionar();

                                laberinto.pintarSolucion(solucion);

                            } catch (NoSolutionException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.WARNING, null, ex);
                            }

                        } catch (NumberFormatException ex) {
                            System.out.println("Por favor, solo ingrese valores númericos");
                        }

                        break;
                    case 2:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Ingrese una opción existente");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido");
            }

        }

    }

}
