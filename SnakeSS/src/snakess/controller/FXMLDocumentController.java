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
import snakess.model.Comida;
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
    Comida comida;

    Timeline timeline;
    boolean juegoInciado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // inicializacion de objetos
        snake = new Snake();
        mapa = new Mapa();
        comida = new Comida();
        
        // inicializcion dle graphics content
        gc = CnvMapa.getGraphicsContext2D();
        
        //inicializacion del timeline
        timeline = obtenerTimeline();
        
        // inicializacion de la snake
        crearCuerpoInicio();crearCuerpo();crearCuerpo();
        juegoInciado = false;
        snake.setDireccion("RIGHT");
        
        // Se crea la primera comida
        dibujarComida();

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
    
    /**
     * Metodo para inciar o parar el movimiento de la snake
     * @param event 
     */
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

    /**
     * En este metodo se mueve la serpiuente y se hace las comprobaciones
     */
    public void moverJuego() {
        
        if(snake.getFirst().getX() >= (int)CnvMapa.getWidth()-15  || //Chocar contra los bordes
           snake.getFirst().getY() >= (int)CnvMapa.getHeight()-15 || 
           snake.getFirst().getX() < 0 || snake.getFirst().getY() < 0){
            
        }else if(snake.getFirst().getX() == comida.getX() &&  // comer comida
                snake.getFirst().getY() == comida.getY()) {
            serpienteHaComido();
        }else if(false){ // chocar contra muros
            
        }else{
            dibujarSnake();
        }
        
        
    }
    
    /**
     * Este metodo se utiliza para realizar los pasos a seguir cuando la snak ealcanza una comida
     */
    public void serpienteHaComido(){
        crearCuerpo();
        dibujarSnake();
        comida = new Comida();
        dibujarComida();
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
    
    public void dibujarComida(){
        gc.fillRect(comida.getX(), comida.getY(), 15, 15);
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
        snake.addParte(60, 60, snake.getDireccion());
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
