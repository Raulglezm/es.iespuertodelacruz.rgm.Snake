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

    public void addParte(int x, int y) {

        this.addLast(new Parte(x, y));

    }

    public void avanzar() {

        this.removeLast(); // borro la ultima posicion del linkedList

        this.addFirst(new Parte(this.getFirst().getX() + 10, this.getFirst().getY()));

        this.getFirst().getX();

    }

}
