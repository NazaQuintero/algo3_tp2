package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.FormJugadoresEventHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeContinentesYPaisesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDePaisesLimitrofesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeTarjetasNoEncontradoException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;


public class CreacionJugadores extends BorderPane {

    private final Stage stage;
    private final VBox panel = new VBox();
    private final HBox botonera;
    private final Label errorLabel;
    private Juego juego;
    private final ArrayList<TextField> textFields = new ArrayList<>();

    public CreacionJugadores(Stage stage) {
        this.stage = stage;

        try {
            juego = new Juego();
        }
        catch (ArchivoDeContinentesYPaisesNoEncontradoException | ArchivoDePaisesLimitrofesNoEncontradoException | ArchivoDeTarjetasNoEncontradoException e) {
            e.printStackTrace();
            Platform.exit();
        }

        this.getStylesheets().add("styles.css");
        this.getStyleClass().add("body");

        errorLabel = crearLabelError();
        Button buttonSubmit = this.crearBotonJugar();
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
        panel.getChildren().add(errorLabel);
        panel.getChildren().add(botonera);
    }

    private void crearFormularioDeCarga(int i) {
        Label label = new Label("Ingrese el nombre del jugador " + (i+1) + ": ");
        label.getStyleClass().add("labelText");
        TextField txtField = crearTextField();
        textFields.add(txtField);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(label, txtField);
        hbox.setSpacing(20);
        panel.getChildren().addAll(hbox);
        this.setCenter(panel);
    }

    private TextField crearTextField() {
        TextField inputText = new TextField();
        inputText.getStyleClass().add("textField");
        inputText.setAlignment(Pos.CENTER);
        inputText.setOnKeyPressed(new FormJugadoresEventHandler(errorLabel, juego, textFields, stage));
        return inputText;
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

    private Button crearBotonJugar() {
        Button buttonSubmit = new Button("Jugar");
        buttonSubmit.getStyleClass().add("startButton");
        buttonSubmit.setOnMouseClicked(new FormJugadoresEventHandler(errorLabel, juego, textFields, stage));
        return buttonSubmit;
    }

    private Label crearLabelError() {
        Label label = new Label("Debe cargar los nombres de todos los jugadores para comenzar");
        label.getStyleClass().add("error");
        label.setVisible(false);
        return label;
    }
}
