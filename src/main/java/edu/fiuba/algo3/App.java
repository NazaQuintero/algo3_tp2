package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.ReproductorDeSonido;
import edu.fiuba.algo3.vista.MenuInicial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final int ANCHO = 800;
    private static final int ALTO = 550;

    @Override
    public void start(Stage stage) {

        ReproductorDeSonido.getInstance().playPrincipal();

        MenuInicial menuInicial = new MenuInicial(stage);
        Scene presentacion = new Scene(menuInicial, ANCHO, ALTO);

        stage.setScene(presentacion);
        stage.setTitle("A.L.T.E.G.O.");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}