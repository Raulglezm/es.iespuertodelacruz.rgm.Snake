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
    
    public Comida(){
        x = rnd.nextInt();
    }
    
}
