/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.controller;

import java.net.URL;
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
import snakess.model.Mapa;
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

    Snake snake;
    Mapa mapa;

    Timeline timeline;
    boolean juegoInciado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        snake = new Snake();
        mapa = new Mapa();
        gc = CnvMapa.getGraphicsContext2D();
        timeline = obtenerTimeline();
        crearCuerpoInicio();
        crearCuerpo();
        crearCuerpo();
        snake.setDireccion("RIGHT");
        juegoInciado = false;

    }

    /**
     * Se obtiene el timeline
     *
     * @param velocidad
     * @return
     */
    Timeline obtenerTimeline() {

        Timeline tl = new Timeline(new KeyFrame(
                Duration.millis(100), movimiento -> moverJuego()
        ));

        tl.setCycleCount(Animation.INDEFINITE);

        return tl;
    }
    
    
    @FXML
    public void Iniciar(ActionEvent event) {
        
        if (juegoInciado){
            timeline.stop();
            juegoInciado = false;
        }else{
            timeline.play();
            juegoInciado = true;
        }
        
    }

    
    public void moverJuego() {
        
        if(snake.getFirst().getX() > (int)CnvMapa.getWidth() || snake.getFirst().getY() > (int)CnvMapa.getHeight()|| //Chocar contra paredes
           snake.getFirst().getX() < 0 || snake.getFirst().getY() < 0){
            
        }else{
            dibujarSnake();
        }
        

    }

    /**
     * Este metodo dibuja la snake cada vez que avanza en su posicion
     */
    public void dibujarSnake() {

        gc.clearRect(snake.getLast().getX(), snake.getLast().getY(), 15, 15); // borro la ultima posicion de gc

        switch (snake.getDireccion()) {
            case "UP":
                gc.fillRect(snake.getFirst().getX(), snake.getFirst().getY() - 15, 15, 15);//coordenadas (X, Y, ancho, largo)
                break;
            case "DOWN":
                gc.fillRect(snake.getFirst().getX(), snake.getFirst().getY() + 15, 15, 15);//coordenadas (X, Y, ancho, largo)
                break;

            case "RIGHT":
                gc.fillRect(snake.getFirst().getX() + 15, snake.getFirst().getY(), 15, 15);//coordenadas (X, Y, ancho, largo)
                break;

            case "LEFT":

                gc.fillRect(snake.getFirst().getX() - 15, snake.getFirst().getY(), 15, 15);//coordenadas (X, Y, ancho, largo)
                break;
        }

        snake.avanzar();

    }

    /**
     * Este metodo se utiliza para sumar partes del cuerpo de la snake
     */
    public void crearCuerpo() {

        snake.addParte(snake.getLast().getX(), snake.getLast().getY(), snake.getLast().getDireccion());
    }

    /**
     * Este metodo se utiliza para añadir la cabeza inicial al cuerpo
     */
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
    
    
    public void newGame(){
        
        
        
    }

}
