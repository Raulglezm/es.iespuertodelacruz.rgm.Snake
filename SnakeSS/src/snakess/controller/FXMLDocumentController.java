/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import snakess.model.Parte;
import snakess.model.Snake;

/**
 *
 * @author alumno
 */
public class FXMLDocumentController implements Initializable {
    
    Parte parte;

    @FXML
    private Canvas CnvMapa;
    @FXML
    private Button btnEmpezar;

    GraphicsContext gc;

    Snake snake = new Snake();

    Timeline timeline;

    int x;
    int xPrimero;
    int y;
    int yPrimero;
    

    @FXML
    public void handleButtonAction(ActionEvent event) {
        timeline.play();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = CnvMapa.getGraphicsContext2D();
        timeline = obtenerTimeline();
        x = 100;
        y = 100;
        crearCuerpoInicio();crearCuerpo();crearCuerpo();
    }
    
     /**
     * Se obtiene el timeline
     * @param velocidad
     * @return
     */
    Timeline obtenerTimeline() {

        Timeline tl = new Timeline(new KeyFrame(
                Duration.millis(40), movimiento -> moverJuego()
        ));

        tl.setCycleCount(Animation.INDEFINITE);

        return tl;
    }
    
    public void moverJuego() {

        dibujarSnake();

    }

    /**
     * Este metodo dibuja la snake cada vez que avanza en su posicion
     */
    public void dibujarSnake() {

        // x = snake.getLast().getX(); valor x de la ultima posicion
        // y = snake.getLast().getY(); valor y de la ultima posicion
        
        // xPrimero = snake.getFirst().getX(); valor x de la primera posicion
        // yPrimero = snake.getFirst().getY(); valor y de la primera posicion
        
        System.out.println(snake);
        
        gc.clearRect(10, 10, snake.getLast().getX(), snake.getLast().getY()); // borro la ultima posicion de gc
        
        snake.avanzar();
        
        gc.fillRect(snake.getFirst().getX()+10, snake.getFirst().getY(), 10, 10);//coordenadas (X, Y, ancho, largo)
        
    }
    
    public void crearCuerpo() {

        snake.addParte(snake.getLast().getX(), snake.getLast().getY());

    }
    
    public void crearCuerpoInicio(){
        snake.addParte(100, 100);
    }

}
