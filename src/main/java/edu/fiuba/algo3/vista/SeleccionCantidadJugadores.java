package edu.fiuba.algo3.vista;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SeleccionCantidadJugadores extends BorderPane {

    VBox panel = new VBox();
    int cantidad = 0;
    CreacionJugadores creacionJugadores;
    private static final int ANCHO = 1200;
    private static final int ALTO = 800;

    public SeleccionCantidadJugadores(Stage stage) {

        this.creacionJugadores = new CreacionJugadores(stage);

        this.getStylesheets().add("styles.css");

        ComboBox<String> comboBox = crearComboBox();

        HBox botonera = this.crearBotoneraHorizontal(stage);
        Label label = this.crearLabel();

        panel.getChildren().addAll(label, comboBox, botonera);
        panel.setAlignment(Pos.CENTER);
        panel.setSpacing(20);

        this.setCenter(panel);

    }

    private HBox crearBotoneraHorizontal(Stage stage) {
        Button startButton = new Button("Ingresar");
        Button exitButton = new Button("Salir");

        startButton.getStyleClass().add("startButton");
        exitButton.getStyleClass().add("exitButton");

        HBox botonera = new HBox(startButton, exitButton);
        botonera.setAlignment(Pos.CENTER);
        botonera.setSpacing(20);

        exitButton.setOnAction(e -> Platform.exit());
        startButton.setOnAction(e -> {
            this.creacionJugadores.setCantidadDeJugadores(cantidad);
            Scene nuevaEscena = new Scene(this.creacionJugadores, ANCHO, ALTO);
            stage.setScene(nuevaEscena);
        });

        return botonera;
    }

    private ComboBox<String> crearComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "2 Jugadores","3 Jugadores", "4 Jugadores", "5 Jugadores", "6 Jugadores"
        );
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.getStyleClass().add("comboBox");

        comboBox.setOnAction(e -> this.cantidad = comboBox.getSelectionModel().getSelectedIndex() + 2);

        return comboBox;
    }

    private Label crearLabel() {
        Label label = new Label("Seleccione la cantidad de jugadores: ");
        label.getStyleClass().add("title");
        return label;
    }

}
