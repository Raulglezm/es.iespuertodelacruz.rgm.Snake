/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.controller;

import java.net.URL;
import java.util.HashSet;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    @FXML
    public void Iniciar(ActionEvent event) {
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = CnvMapa.getGraphicsContext2D();
        timeline = obtenerTimeline();
        crearCuerpoInicio();
        crearCuerpo();
        crearCuerpo();
        
        snake.setDireccion("RIGHT");
        
        
    }

    /**
     * Se obtiene el timeline
     *
     * @param velocidad
     * @return
     */
    Timeline obtenerTimeline() {

        Timeline tl = new Timeline(new KeyFrame(
                Duration.millis(200), movimiento -> moverJuego()
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

        switch (snake.getDireccion()) {
            case "UP":
                gc.fillRect(snake.getFirst().getX(), snake.getFirst().getY()-15, 15, 15);//coordenadas (X, Y, ancho, largo)
                break;
            case "DOWN":
                gc.fillRect(snake.getFirst().getX(), snake.getFirst().getY()+15, 15, 15);//coordenadas (X, Y, ancho, largo)
                break;

            case "RIGHT":
                gc.fillRect(snake.getFirst().getX()+15, snake.getFirst().getY(), 15, 15);//coordenadas (X, Y, ancho, largo)
                break;

            case "LEFT":
                
                gc.fillRect(snake.getFirst().getX()-15, snake.getFirst().getY(), 15, 15);//coordenadas (X, Y, ancho, largo)
                break;
        }
        
        
        
        snake.avanzar();
        
        switch (snake.getLast().getDireccion()) {
            case "UP":
                    
                break;
            case "DOWN":
                gc.clearRect(15, 15, snake.getLast().getX(), snake.getLast().getY()); // borro la ultima posicion de gc
                break;

            case "RIGHT":
                gc.clearRect(15, 15, snake.getLast().getX(), snake.getLast().getY()); // borro la ultima posicion de gc
                break;

            case "LEFT":
                
                break;
        }
        
    }

    public void crearCuerpo() {

        snake.addParte(snake.getLast().getX(), snake.getLast().getY(), snake.getLast().getDireccion());

    }

    public void crearCuerpoInicio() {
        snake.addParte(100, 100, snake.getDireccion());
    }

    /**
     * Este metodo registra las teclas por teclados y manda a comprobar si es
     * posible girar en el sentido deseado.
     *
     * @param event
     */
    @FXML
    private void movimiento(KeyEvent event) {
        KeyCode kc = event.getCode();

        if (kc != null) {
            snake.posibleGiro(kc.toString());
        }
    }

}
