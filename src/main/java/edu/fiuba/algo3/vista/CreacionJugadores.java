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

    public CreacionJugadores(Stage stage) {

        this.getStylesheets().add("styles.css");

        Label label = new Label("Ingrese el nombre del jugador: ");

        TextField inputText = new TextField();
        inputText.setPrefWidth(300);
        inputText.setPrefHeight(50);
        HBox textFieldHBox = new HBox(inputText);
        textFieldHBox.setAlignment(Pos.CENTER);

//        inputText.getStyleClass().add("textField");

        Button buttonSubmit = new Button("Cargar");

        Button exitButton = new Button("Salir");
        exitButton.setOnAction(e -> Platform.exit());

        HBox hbox = new HBox(buttonSubmit, exitButton);
        hbox.setAlignment(Pos.CENTER);

        panel.setAlignment(Pos.CENTER);
        panel.getChildren().addAll(label, textFieldHBox, hbox);

        this.setCenter(panel);

    }

}
