package edu.fiuba.algo3.vista;

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

        this.getStylesheets().add("styles.css");
        this.getStyleClass().add("body");

        Label etiqueta = this.crearEtiquetaDeBienvenida();
        HBox botonera = this.crearBotoneraHorizontal(stage);

        this.getChildren().addAll(etiqueta, botonera);
    }

    private HBox crearBotoneraHorizontal(Stage stage) {
        Button startButton = new Button();
        Button exitButton = new Button();
        startButton.setText("Jugar");
        exitButton.setText("Salir");

        startButton.getStyleClass().add("startButton");
        exitButton.getStyleClass().add("exitButton");

        startButton.setOnAction(e -> {
            Scene nuevaEscena = new Scene(new SeleccionCantidadJugadores(stage), ANCHO, ALTO);
            stage.setScene(nuevaEscena);
        });

        exitButton.setOnAction(e -> Platform.exit());

        HBox botonera = new HBox(startButton, exitButton);
        botonera.setAlignment(Pos.CENTER);
        botonera.setSpacing(20);

        return botonera;
    }

    private Label crearEtiquetaDeBienvenida() {
        Label etiqueta = new Label("Bienvenidos a A.L.T.E.G.O.!");
        etiqueta.getStyleClass().add("title");
        return etiqueta;
    }


}
