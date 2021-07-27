package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.CrearJugadoresEventHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SeleccionCantidadJugadores extends BorderPane {

    VBox panel = new VBox();

    public SeleccionCantidadJugadores(Stage stage) {
        this.getStylesheets().add("styles.css");
        ObservableList<String> options = FXCollections.observableArrayList(
                "2 Jugadores","3 Jugadores", "4 Jugadores", "5 Jugadores", "6 Jugadores"
        );
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.getStyleClass().add("comboBox");

        Button startButton = new Button("Ingresar");

        Button exitButton = new Button("Salir");
        exitButton.setOnAction(e -> Platform.exit());

        startButton.getStyleClass().add("startButton");
        exitButton.getStyleClass().add("exitButton");
        HBox botonera = new HBox(startButton, exitButton);
        botonera.setAlignment(Pos.CENTER);
        botonera.setSpacing(20);

        Label label = new Label("Seleccione la cantidad de jugadores: ");
        label.getStyleClass().add("title");

        panel.getChildren().addAll(label, comboBox, botonera);
        panel.setAlignment(Pos.CENTER);
        panel.setSpacing(20);

        this.setCenter(panel);

        startButton.setOnAction(new CrearJugadoresEventHandler(stage));

    }



}
