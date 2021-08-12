package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.ReproductorDeSonido;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuInicial extends VBox {

    private Stage stage;
    private static final int ANCHO = 800;
    private static final int ALTO = 550;

    public MenuInicial(Stage stage) {
        this.stage = stage;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        stage.centerOnScreen();

        this.getStylesheets().add("styles.css");
        this.getStyleClass().add("body");

        Label etiqueta = this.crearEtiquetaDeBienvenida();
        Button startButton = createStartButton();
        Button exitButton = createExitButton();
        HBox botonera = this.crearBotoneraHorizontal(startButton, exitButton);

        this.getChildren().addAll(etiqueta, botonera);
    }

    private HBox crearBotoneraHorizontal(Button startButton, Button exitButton) {
        HBox botonera = new HBox(startButton, exitButton);
        botonera.setAlignment(Pos.CENTER);
        botonera.setSpacing(20);
        return botonera;
    }

    private Button createExitButton() {
        Button exitButton = new Button();
        exitButton.setText("Salir");
        exitButton.getStyleClass().add("exitButton");
        exitButton.setOnAction(e -> {
            ReproductorDeSonido.playClick();
            Platform.exit();
        });
        return exitButton;
    }

    private Button createStartButton() {
        Button startButton = new Button();
        startButton.setText("Jugar");
        startButton.getStyleClass().add("startButton");
        startButton.setOnAction(e -> {
            ReproductorDeSonido.playClick();
            Scene nuevaEscena = new Scene(new SeleccionCantidadJugadores(stage), ANCHO, ALTO);
            stage.setScene(nuevaEscena);
        });
        return startButton;
    }

    private Label crearEtiquetaDeBienvenida() {
        Label etiqueta = new Label("Bienvenidos a A.L.T.E.G.O.!");
        etiqueta.getStyleClass().add("title");
        return etiqueta;
    }


}
