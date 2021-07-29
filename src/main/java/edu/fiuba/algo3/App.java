package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vista.MenuInicial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final int ANCHO = 640;
    private static final int ALTO = 480;

    @Override
    public void start(Stage stage) {
        Juego juego = new Juego();
        MenuInicial menuInicial = new MenuInicial(stage, juego);
        Scene presentacion = new Scene(menuInicial, ANCHO, ALTO);
        stage.setScene(presentacion);
        stage.setTitle("Comenzar juego");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}