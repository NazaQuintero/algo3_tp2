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

    public SeleccionCantidadJugadores(Stage stage) {

        this.creacionJugadores = new CreacionJugadores(stage);

        this.getStylesheets().add("styles.css");
        this.getStyleClass().add("body");

        ComboBox<String> comboBox = this.crearComboBox();

        Label label = this.crearLabel();
        Label labelError = this.crearLabelError();
        Button startButton = this.createStartButton(stage, labelError);
        Button exitButton = this.createExitButton();
        HBox botonera = this.crearBotoneraHorizontal(startButton, exitButton);

        panel.getChildren().addAll(label, comboBox, botonera);
        panel.setAlignment(Pos.CENTER);
        panel.setSpacing(20);

        this.setCenter(panel);

    }

    private Label crearLabelError() {
        Label label = new Label("Debe seleccionar una cantidad de jugadores para comenzar");
        label.getStyleClass().add("error");
        return label;
    }

    private HBox crearBotoneraHorizontal(Button startButton, Button exitButton) {
        HBox botonera = new HBox(startButton, exitButton);
        botonera.setAlignment(Pos.CENTER);
        botonera.setSpacing(20);
        return botonera;
    }

    private Button createStartButton(Stage stage, Label labelError) {
        Button startButton = new Button("Ingresar");
        startButton.getStyleClass().add("startButton");
        startButton.setOnAction(e -> {
            if (cantidad == 0) {
                if(!panel.getChildren().contains(labelError)) {
                    panel.getChildren().add(labelError);
                }
            } else {
                this.creacionJugadores.setCantidadDeJugadores(cantidad);
                Scene nuevaEscena = new Scene(this.creacionJugadores);
                stage.setScene(nuevaEscena);
            }
        });
        return startButton;
    }

    private Button createExitButton() {
        Button exitButton = new Button("Salir");
        exitButton.getStyleClass().add("exitButton");
        exitButton.setOnAction(e -> Platform.exit());
        return exitButton;
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
