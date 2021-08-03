package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDePaisesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeTarjetasNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.CantidadDeJugadoresInsuficienteException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.css.StyleClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.Console;
import java.util.ArrayList;


public class CreacionJugadores extends BorderPane {

    VBox panel = new VBox();
    HBox botonera;
    Label errorLabel;
    private Juego juego;
    ArrayList<TextField> textFields = new ArrayList<>();

    public CreacionJugadores(Stage stage) {

        try {
            juego = new Juego();
        } catch (Exception e) {
            Platform.exit();
        }
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
        errorLabel = crearLabelError();
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

        panel.getChildren().addAll(hbox);
        this.setCenter(panel);
    }

    private TextField crearTextField() {
        TextField inputText = new TextField();
        inputText.getStyleClass().add("textField");
        inputText.setAlignment(Pos.CENTER);
        return inputText;
    }

    private Color getColor(int i){
        switch (i) {
            case 0: return Color.RED;
            case 1: return Color.BLUE;
            case 2: return Color.YELLOW;
            case 3: return Color.GREEN;
            case 4: return Color.BLACK;
            case 5: return Color.DARKMAGENTA;
        }
        return Color.RED;
    }
/*
    private Button crearBotonDeCarga(TextField tField, Color color) {
        Button buttonSubmit = new Button("Cargar");
        buttonSubmit.setOnAction(e -> juego.agregarJugador(tField.getText(), color));
        buttonSubmit.getStyleClass().addAll("startButton", "loadButton");
        return buttonSubmit;
    }
*/
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
            // Comprueba que todos los campos de texto se hayan llenado
            boolean jugadoresCargados = validarTextFields();
            if (jugadoresCargados) {
                try {
                    juego.comenzar();
                    new CampoDeJuego(stage);
                }

                catch (CantidadDeJugadoresInsuficienteException ignored){ }
            }
            errorLabel.setVisible(true);
        });

        return buttonSubmit;
    }

    private boolean validarTextFields(){
        boolean jugadoresCargados = true;
        for (int i = 0; i< textFields.size(); i++){
            TextField nombreJugador = textFields.get(i);
            ObservableList<String> styles = nombreJugador.getStyleClass();
            if (nombreJugador.isDisable()) continue;

            if (nombreJugador.getText().trim().isEmpty()){
                jugadoresCargados = false;
                if (!styles.contains("invalid")){
                    styles.removeAll("valid");
                    styles.add("invalid");
                }
                nombreJugador.requestFocus();
                continue;
            }

            if (!styles.contains("valid")){
                styles.removeAll("invalid");
                styles.add("valid");
            }

            nombreJugador.getStyleClass().add("valid");
            nombreJugador.setDisable(true);
            juego.agregarJugador(nombreJugador.getText(), getColor(i));
        }
        return jugadoresCargados;
    }

    private Label crearLabelError() {
        Label label = new Label("Debe cargar los nombres de todos los jugadores para comenzar");
        label.getStyleClass().add("error");
        label.setVisible(false);
        return label;
    }
}
