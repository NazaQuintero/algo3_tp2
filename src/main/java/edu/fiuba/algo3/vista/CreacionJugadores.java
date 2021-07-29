package edu.fiuba.algo3.vista;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreacionJugadores extends BorderPane {

    VBox panel = new VBox();

    public CreacionJugadores(Stage stage, int cantidadJugadores) {

        this.getStylesheets().add("styles.css");

        for (int i = 0; i < cantidadJugadores; i++) {
            Label label = new Label("Ingrese el nombre del jugador " + (i+1) + ": ");
            HBox textFieldHBox = crearTextFieldBox();
            HBox botonera = crearBotoneraHorizontal();
            panel.getChildren().addAll(label, textFieldHBox, botonera);
        }

        panel.setAlignment(Pos.CENTER);
        this.setCenter(panel);
    }

    private HBox crearTextFieldBox() {
        TextField inputText = new TextField();
        inputText.setPrefWidth(300);
        inputText.setPrefHeight(50);
        HBox textFieldHBox = new HBox(inputText);
        textFieldHBox.setAlignment(Pos.CENTER);
        return textFieldHBox;
    }

    private HBox crearBotoneraHorizontal() {
        Button buttonSubmit = new Button("Cargar");
        buttonSubmit.getStyleClass().add("startButton");

        Button exitButton = new Button("Salir");
        exitButton.getStyleClass().add("exitButton");
        exitButton.setOnAction(e -> Platform.exit());

        HBox hbox = new HBox(buttonSubmit, exitButton);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

}
