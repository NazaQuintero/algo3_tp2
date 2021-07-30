package edu.fiuba.algo3.vista;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreacionJugadores extends BorderPane {

    VBox panel = new VBox();
    HBox botonera;
    private static final int ANCHO = 1200;
    private static final int ALTO = 800;

    public CreacionJugadores(Stage stage) {

        this.getStylesheets().add("styles.css");
        botonera = crearBotoneraHorizontal(stage);
        panel.setAlignment(Pos.CENTER);
        panel.setSpacing(30);

        this.setPadding(new Insets(20, 120, 20, 120));

    }

    private HBox crearTextFieldBox() {
        TextField inputText = new TextField();
        inputText.getStyleClass().add("textField");
        HBox textFieldHBox = new HBox(inputText);
        textFieldHBox.setAlignment(Pos.CENTER);
        return textFieldHBox;
    }

    private Button crearBotonDeCarga() {
        Button buttonSubmit = new Button("Cargar");
        buttonSubmit.getStyleClass().add("startButton");
        return buttonSubmit;
    }

    private HBox crearBotoneraHorizontal(Stage stage) {
        Button buttonSubmit = new Button("Jugar");
        buttonSubmit.getStyleClass().add("startButton");
        buttonSubmit.setOnAction(e -> {
            Scene nuevaEscena = new Scene(new CampoDeJuego(stage), ANCHO, ALTO);
            stage.setScene(nuevaEscena);
        });

        Button exitButton = new Button("Salir");
        exitButton.getStyleClass().add("exitButton");
        exitButton.setOnAction(e -> Platform.exit());

        HBox hbox = new HBox(buttonSubmit, exitButton);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }


}
