/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Hari
 */
public class Mapa {
    
    public ArrayList<Muro> muros;
    public Muro muro;
    
    int tamX;
    int tamY;

    public Mapa(int tamX, int tamY) {
        this.tamX = tamX;
        this.tamY = tamY;
        muros = new ArrayList<>(15);
        crearMuros();
    }
    public void crearMuros(){
        for (int i = 0; i < 15; i++) {
            muro = new Muro();
            muros.add(muro);
        }
    }
    
    /**
     * Clase anidada de mapa
     */
    public class Muro{
        
        int x;
        int y;
    
        Random rnd = new Random();
    
        /**
         * Constructo de la clase anidada Muro
         */
        public Muro(){
            x = rnd.nextInt(415);
            y = rnd.nextInt(400);
            colocar();
        }

        /**
         * Este metodo se utiliza para colocar los muros
         */
        private void colocar(){

            while (x % 15 != 0){
                x += 1;
            }
            while (y % 15 != 0){
                y += 1;
            }
        }

        // GETTERS Y SETTERS
        
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
        
    }
    
}
