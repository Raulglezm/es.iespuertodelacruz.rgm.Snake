/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Hari
 */
public class Mapa {
    
    ArrayList<Muro> muros;
    
    int tamX;
    int tamY;
    
    
    /**
     * Constructor de a clase mapa
     * @param x
     * @param y 
     */
    public void Mapa(int x, int y){
        this.tamX = x;
        this.tamY = y;
        muros = new ArrayList<>();
    }
    
    
    
    /**
     * Clase anidada de mapa
     */
    class Muro{
        
    }
    
}
