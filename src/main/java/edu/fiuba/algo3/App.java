package edu.fiuba.algo3;

import com.google.gson.Gson;
import edu.fiuba.algo3.modelo.CargarJuego;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.PosicionPais;
import edu.fiuba.algo3.modelo.continentes.Africa;
import edu.fiuba.algo3.modelo.continentes.Continenta;
import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDePaisesNoEncontradoException;
import edu.fiuba.algo3.vista.MenuInicial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final int ANCHO = 800;
    private static final int ALTO = 550;

    @Override
    public void start(Stage stage) {


        CargarJuego.cargarContinentes();
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