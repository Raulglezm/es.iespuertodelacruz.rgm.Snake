/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.model;

import java.util.LinkedList;

/**
 *
 * @author alumno
 */
public class Snake extends LinkedList<Parte> {

    Parte parte = new Parte(100, 100);
    private String direccion;

    public void addParte(int x, int y) {

        this.addLast(new Parte(x, y));

    }

    public void avanzar() {

        this.removeLast(); // borro la ultima posicion del linkedList

        this.addFirst(new Parte(this.getFirst().getX() + 10, this.getFirst().getY()));

        this.getFirst().getX();

    }

    /**
     * Este metodo comprueba si es posible girarr en la dirección deseada
     * @param sentido al que se desea girar
     * @return si es posible el giro
     */
    public boolean posibleGiro(String sentido) {

        boolean esPosible = false;

        switch (sentido) {
            case "UP":
                if (!getDireccion().equals("DOWN")) {
                    setDireccion("UP");
                    esPosible = true;
                }
                break;
                
                
            case "DOWN":
                if (!getDireccion().equals("UP")) {
                    setDireccion("DOWN");
                    esPosible = true;
                }
                break;
                
                
            case "RIGHT":
                if (!getDireccion().equals("LEFT")) {
                    setDireccion("RIGTH");
                    esPosible = true;
                }
                break;
                
                
            case "LEFT":
                if (!getDireccion().equals("RIGHT")) {
                    setDireccion("LEFT");
                    esPosible = true;
                }
                break;
        }

        return esPosible;

    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
