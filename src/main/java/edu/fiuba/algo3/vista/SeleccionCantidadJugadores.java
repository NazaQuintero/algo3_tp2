package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.CrearJugadoresEventHandler;
import edu.fiuba.algo3.modelo.Juego;
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

    public SeleccionCantidadJugadores(Stage stage, Juego juego) {
        this.getStylesheets().add("styles.css");

        ComboBox<String> comboBox = crearComboBox();

        int cantidad = comboBox.getSelectionModel().getSelectedIndex() + 2;

        HBox botonera = this.crearBotoneraHorizontal(stage, cantidad);
        Label label = this.crearLabel();

        panel.getChildren().addAll(label, comboBox, botonera);
        panel.setAlignment(Pos.CENTER);
        panel.setSpacing(20);

        this.setCenter(panel);

    }

    private HBox crearBotoneraHorizontal(Stage stage, int cantidad) {
        Button startButton = new Button("Ingresar");
        Button exitButton = new Button("Salir");

        startButton.getStyleClass().add("startButton");
        exitButton.getStyleClass().add("exitButton");

        HBox botonera = new HBox(startButton, exitButton);
        botonera.setAlignment(Pos.CENTER);
        botonera.setSpacing(20);

        exitButton.setOnAction(e -> Platform.exit());
        startButton.setOnAction(new CrearJugadoresEventHandler(stage, cantidad));

        return botonera;
    }

    private ComboBox<String> crearComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "2 Jugadores","3 Jugadores", "4 Jugadores", "5 Jugadores", "6 Jugadores"
        );
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.getStyleClass().add("comboBox");
        return comboBox;
    }

    private Label crearLabel() {
        Label label = new Label("Seleccione la cantidad de jugadores: ");
        label.getStyleClass().add("title");
        return label;
    }

}
