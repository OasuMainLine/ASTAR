/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.astar;

import java.util.function.BiFunction;

/**
 *
 * @author Orlando Andres Sandi Ulate (C07355)
 */
public class Heuristico {

    static final BiFunction<Punto, Punto, Integer> H0 = (Punto actual, Punto objetivo) -> {
        return 0;
    };

    static final BiFunction<Punto, Punto, Integer> MANHATTAN = (Punto actual, Punto objetivo) -> {
        return Math.abs(actual.X - objetivo.X) + Math.abs(actual.Y - objetivo.Y);
    };

    static final BiFunction<Punto, Punto, Integer> EUCLIDEA = (Punto actual, Punto objetivo) -> {
        return (int) Math.sqrt(Math.pow((actual.X - objetivo.X), 2) + Math.pow((actual.Y - objetivo.Y), 2));
    };
}
