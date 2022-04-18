/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Raúl
 */
public class Comida {
    
    private int x;
    private int y;
    private Image manz;
    
    Random rnd = new Random();
    
    /**
     * Constructo de la clase
     */
    public Comida(){
        x = rnd.nextInt(400-15);
        y = rnd.nextInt(385-15);

        this.manz = ImageIO.read(new File("c:\\manzana.png"));
                //ImageIO.read(new File("c:\\manzana.png"));

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

    public BufferedImage getManz() {
        return manz;
    }

    public void setManz(BufferedImage manz) {
        this.manz = manz;
    }
    
}
