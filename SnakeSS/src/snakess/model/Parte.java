/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.model;

/**
 *
 * @author alumno
 */
public class Parte {

    private int x;
    private int y;
    private int direccion;
    
    /**
     * Constructor con un parametro
     */
    public Parte() {
        
    }

    /**
     * Constructor con dos parametros
     * @param x
     * @param y
     */
    public Parte(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //GETTERES Y SETTERS

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

}
