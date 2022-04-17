/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.model;

import java.util.Random;

/**
 *
 * @author alumno
 */
public class Comida {
    
    int x;
    int y;
    
    Random rnd = new Random();
    
    /**
     * Constructo de la clase
     */
    public Comida(){
        x = rnd.nextInt(415);
        y = rnd.nextInt(400);
        colocar();
    }
    
    private void colocar(){
        
        while (x % 15 != 0){
            x += 1;
        }
        while (y % 15 != 0){
            y += 1;
        }
    }

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

    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }
    
}
