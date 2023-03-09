/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.astar;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Orlando Andres Sandi Ulate (C07355)
 */
public class Nodo {

    Punto coordenadas;
    int costeG;
    char value;
    Nodo padre;
    ArrayList<Nodo> sucesores;

    public Nodo(Punto coordenadas, int costeG, Nodo padre, char value) {
        this.coordenadas = coordenadas;
        this.costeG = costeG;
        this.padre = padre;
        this.value = value;

        sucesores = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.coordenadas);
        hash = 41 * hash + this.costeG;
        hash = 41 * hash + Objects.hashCode(this.padre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        return this.coordenadas.equals(other.coordenadas);

    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getPadre() {
        return padre;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public ArrayList<Nodo> getSucesores() {
        return sucesores;
    }

    public void setSucesores(ArrayList<Nodo> sucesores) {
        this.sucesores = sucesores;
    }
}
