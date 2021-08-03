package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDePaisesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeTarjetasNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.CantidadDeJugadoresInsuficienteException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CreacionJugadores extends BorderPane {

    VBox panel = new VBox();
    HBox botonera;
    Label errorLabel;
    private Juego juego;

    public CreacionJugadores(Stage stage) {

        this.getStylesheets().add("styles.css");
        Button buttonSubmit = this.crearBotonJugar(stage);
        Button exitButton = this.crearExitButton();
        botonera = crearBotoneraHorizontal(buttonSubmit, exitButton);
        panel.setAlignment(Pos.CENTER);
        panel.setSpacing(30);

        this.setPadding(new Insets(20, 120, 20, 120));
    }

    public void setCantidadDeJugadores(int cantidadJugadores) {

        for (int i = 0; i < cantidadJugadores; i++) {
            this.crearFormularioDeCarga(i);
        }
        panel.getChildren().add(botonera);
    }

    private void crearFormularioDeCarga(int i) {
        Label label = new Label("Ingrese el nombre del jugador " + (i+1) + ": ");
        label.getStyleClass().add("labelText");
        HBox textFieldHBox = crearTextFieldBox();
        Button botonDeCarga = crearBotonDeCarga();
        HBox hbox = new HBox();
        hbox.getChildren().addAll(label, textFieldHBox, botonDeCarga);

        panel.getChildren().addAll(hbox);
        this.setCenter(panel);
    }

    private TextField crearTextField() {
        TextField inputText = new TextField();
        inputText.getStyleClass().add("textField");
        inputText.setAlignment(Pos.CENTER);
        return inputText;
    }

    private Button crearBotonDeCarga() {
        Button buttonSubmit = new Button("Cargar");
        buttonSubmit.getStyleClass().addAll("startButton", "loadButton");
        return buttonSubmit;
    }

    private HBox crearBotoneraHorizontal(Button buttonSubmit, Button exitButton) {
        HBox hbox = new HBox(buttonSubmit, exitButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50);
        return hbox;
    }

    private Button crearExitButton() {
        Button exitButton = new Button("Salir");
        exitButton.getStyleClass().add("exitButton");
        exitButton.setOnAction(e -> Platform.exit());
        return exitButton;
    }

    private Button crearBotonJugar(Stage stage) {
        Button buttonSubmit = new Button("Jugar");
        buttonSubmit.getStyleClass().add("startButton");
        buttonSubmit.setOnAction(e -> {
            try {
                juego.comenzar();
                new CampoDeJuego(stage);
            } catch (CantidadDeJugadoresInsuficienteException ex) {
                errorLabel.setVisible(true);
            }
        });

        return buttonSubmit;
    }

    private Label crearLabelError() {
        Label label = new Label("Debe cargar los nombres de todos los jugadores para comenzar");
        label.getStyleClass().add("error");
        label.setVisible(false);
        return label;
    }
}
