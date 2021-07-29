package edu.fiuba.algo3.vista;

import javafx.application.Platform;
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
    VBox cajaDeBotones = new VBox();
    private static final int ANCHO = 1200;
    private static final int ALTO = 800;

    public CreacionJugadores(Stage stage, int cantidadJugadores) {

        this.getStylesheets().add("styles.css");

        for (int i = 0; i < 3; i++) {
            Label label = new Label("Ingrese el nombre del jugador " + (i+1) + ": ");
            HBox textFieldHBox = crearTextFieldBox();
            Button botonDeCarga = crearBotonDeCarga();
            panel.getChildren().addAll(label, textFieldHBox, botonDeCarga);
        }
        HBox botonera = crearBotoneraHorizontal(stage);
        botonera.setSpacing(20);
        cajaDeBotones.getChildren().addAll(botonera);
        cajaDeBotones.setAlignment(Pos.BOTTOM_RIGHT);
        panel.setAlignment(Pos.CENTER);
        this.setCenter(panel);
        this.setRight(cajaDeBotones);
    }

    private HBox crearTextFieldBox() {
        TextField inputText = new TextField();
        inputText.setPrefWidth(300);
        inputText.setPrefHeight(50);
        HBox textFieldHBox = new HBox(inputText);
        textFieldHBox.setAlignment(Pos.CENTER);
        return textFieldHBox;
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
