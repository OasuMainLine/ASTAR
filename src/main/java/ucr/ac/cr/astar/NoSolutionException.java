/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package ucr.ac.cr.astar;

/**
 *
 * @author Orlando Andres Sandi Ulate (C07355)
 */
public class NoSolutionException extends Exception {

    /**
     * Creates a new instance of <code>NoSolutionException</code> without detail
     * message.
     */
    public NoSolutionException() {
    }

    /**
     * Constructs an instance of <code>NoSolutionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoSolutionException(String msg) {
        super(msg);
    }
}
