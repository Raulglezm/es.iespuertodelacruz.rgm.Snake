/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.model;

import java.util.LinkedList;

/**
 *
 * @author Raúl
 */
public class Snake extends LinkedList<Parte> {
    
    private String direccion;

    Parte parte = new Parte(100, 100, direccion);
    
    public void addParte(int x, int y, String direccion) {

        this.addLast(new Parte(x+15, y+15, direccion));

    }

    public void avanzar() {
        
        //System.out.println(this);

        this.removeLast(); // borro la ultima posicion del linkedList
        
        switch (this.getDireccion()) {
            case "UP":
                this.addFirst(new Parte(this.getFirst().getX(), this.getFirst().getY()-15, "UP"));//coordenadas (X, Y, ancho, largo)
                this.direccion = "UP";
                break;

            case "DOWN":
                this.addFirst(new Parte(this.getFirst().getX(), this.getFirst().getY()+15, "DOWN"));//coordenadas (X, Y, ancho, largo)
                this.direccion = "DOWN";
                break;

            case "RIGHT":
                this.addFirst(new Parte(this.getFirst().getX()+15, this.getFirst().getY(), "RIGHT"));//coordenadas (X, Y, ancho, largo)
                this.direccion = "RIGHT";
                break;

            case "LEFT":
                this.addFirst(new Parte(this.getFirst().getX()-15, this.getFirst().getY(), "LEFT"));//coordenadas (X, Y, ancho, largo)
                this.direccion = "LEFT";
                break;
        }

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
                    setDireccion("RIGHT");
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

