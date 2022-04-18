/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakess.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    Alert perder;

    Snake snake;
    Mapa mapa;
    Comida comida;

    Timeline timeline;
    boolean juegoInciado;
    @FXML
    private Button btnEmpezar1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        perder = new Alert(Alert.AlertType.INFORMATION, "Choque!!!");

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
     *
     * @param event
     */
    @FXML
    public void Iniciar(ActionEvent event) {

        if (juegoInciado) {
            timeline.stop();
            juegoInciado = false;
        } else {
            timeline.play();
            juegoInciado = true;
        }

    }

    /**
     * En este metodo se mueve la serpiuente y se hace las comprobaciones
     */
    public void moverJuego() {

        if (comprobarChoque()) {

            mostrarAlerta();
            timeline.stop();

        } else if (snake.getFirst().getX() == comida.getX()
                && // comer comida
                snake.getFirst().getY() == comida.getY()) {
            serpienteHaComido();
        } else {
            dibujarSnake();
        }
    }

    public void mostrarAlerta() {

        perder.show();

    }

    /**
     * Este metodo dibuja la snake cada vez que avanza en su posicion
     */
    public void dibujarSnake() {

        gc.clearRect(snake.getLast().getX(), snake.getLast().getY(), 15, 15); // borro la ultima posicion de gc

        switch (snake.getDireccion()) {
            case "UP":
                gc.setFill(colorAleatorio());
                gc.fillRect(snake.getFirst().getX(), snake.getFirst().getY() - 15, 15, 15);//coordenadas (X, Y, ancho, largo)
                break;
            case "DOWN":
                gc.setFill(colorAleatorio());
                gc.fillRect(snake.getFirst().getX(), snake.getFirst().getY() + 15, 15, 15);//coordenadas (X, Y, ancho, largo)
                break;

            case "RIGHT":
                gc.setFill(colorAleatorio());
                gc.fillRect(snake.getFirst().getX() + 15, snake.getFirst().getY(), 15, 15);//coordenadas (X, Y, ancho, largo)
                break;

            case "LEFT":
                gc.setFill(colorAleatorio());
                gc.fillRect(snake.getFirst().getX() - 15, snake.getFirst().getY(), 15, 15);//coordenadas (X, Y, ancho, largo)
                break;
        }

        snake.avanzar();

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

    @FXML
    private void nuevaPartida(ActionEvent event) {

        if (!juegoInciado) {
            limpiar();

            snake = new Snake();
            mapa = new Mapa((int) CnvMapa.getWidth(), (int) CnvMapa.getHeight());
            comida = new Comida();

            // inicializcion dle graphics content
            gc = CnvMapa.getGraphicsContext2D();

            //inicializacion del timeline
            timeline = obtenerTimeline();

            // inicializacion de la snake
            crearCuerpoInicio();
            crearCuerpo();
            crearCuerpo();

            juegoInciado = false;
            snake.setDireccion("RIGHT");

            // Se crea la primera comida
            dibujarComida();

            dibujarMuros();
        }

    }

    //Metodos para limpiar codigo
    /**
     * Este metodo se utiliza para realizar los pasos a seguir cuando la snake
     * alcanza una comida
     */
    public void serpienteHaComido() {
        crearCuerpo();
        dibujarSnake();
        comida = new Comida();
        dibujarComida();
    }

    public boolean comprobarChoque() {

        if (mapa.muros.stream().anyMatch((muro) -> (snake.getFirst().getX() == muro.getX() && snake.getFirst().getY() == muro.getY()))) {
            juegoInciado = false;
            timeline.stop();
            return true;
        }

        if (snake.getFirst().getX() >= (int) CnvMapa.getWidth()
                || //Chocar contra los bordes
                snake.getFirst().getY() >= (int) CnvMapa.getHeight()
                || snake.getFirst().getX() < 0 || snake.getFirst().getY() < 0) {

            timeline.stop();
            juegoInciado = false;
            return true;
        }

        for (int i = 2; i < snake.size(); i++) {
            Parte partePrueba = snake.get(i);
            if (snake.getFirst().getX() == partePrueba.getX() && snake.getFirst().getY() == partePrueba.getY()) {
                timeline.stop();
                juegoInciado = false;
                return true;
            }
        }

        return false;
    }

    /**
     * Este metodo se utiliza para añadir la cabeza inicial al cuerpo
     */
    public void crearCuerpoInicio() {
        snake.addParte(60, 60, snake.getDireccion());
    }

    /**
     * Este metodo se utiliza para sumar partes del cuerpo de la snake
     */
    public void crearCuerpo() {
        gc.setFill(colorAleatorio());
        snake.addParte(snake.getLast().getX(), snake.getLast().getY(), snake.getLast().getDireccion());
    }

    /**
     * Metodo que sire para dibujar la comida
     */
    public void dibujarComida() {

        boolean colocable = true;
        
        

        for (Mapa.Muro muro : mapa.muros) {

            if (muro.getX() == comida.getX() && muro.getY() == comida.getY()) {
                colocable = false;
            }

        }
        
        for (Parte parte1 : snake) {
            if (parte1.getX() == comida.getX() && parte1.getY() == comida.getY()) {
                colocable = false;
            }
        }
        if (colocable) {
            gc.setFill(Color.RED);
            gc.fillRect(comida.getX(), comida.getY(), 15, 15);
            gc.drawImage(comida.getManz(), comida.getX(), comida.getY());
            
        }
        
        if (!colocable){
            comida = new Comida();
            dibujarComida();
        }
    }

    /**
     * metodo para dibujar los obstaculos
     */
    public void dibujarMuros() {
        for (Mapa.Muro muro : mapa.muros) {
            gc.setFill(Color.ORANGE);
            gc.fillRect(muro.getX(), muro.getY(), 15, 15);
        }
    }

    private Paint colorAleatorio() {
        Random rnd = new Random();

        int aleatorio = rnd.nextInt(10) + 1;

        switch (aleatorio) {

            case 1:
                return Color.ALICEBLUE;
            case 2:
                return Color.AQUAMARINE;
            case 3:
                return Color.BEIGE;
            case 4:
                return Color.BLUE;
            case 5:
                return Color.CADETBLUE;
            case 6:
                return Color.CORAL;
            case 7:
                return Color.LIGHTGREEN;
            case 8:
                return Color.DARKSLATEGRAY;
            case 9:
                return Color.GREENYELLOW;
            case 10:
                return Color.SLATEBLUE;
        }
        return Color.BLACK;
    }

    private void limpiar() {
        CnvMapa.getGraphicsContext2D().clearRect(0, 0, 403, 403);
    }

}
