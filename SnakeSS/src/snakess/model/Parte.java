/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.model;

/**
 *
 * @author Raúl González Martín
 */
public class Parte {
    
    private String direccion;
    private int x;
    private int y;
    
    /**
     * Constructor con un parametro
     */
    public Parte() {
        
    }

    /**
     * Constructor con dos parametros
     * @param x
     * @param y
     * @param direccion
     */
    public Parte(int x, int y, String direccion) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
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

    public String getDireccion() {
        return direccion;
    }

}
