package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.excepciones.CantidadDeJugadoresInsuficienteException;
import edu.fiuba.algo3.vista.CampoDeJuego;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FormJugadoresEventHandler implements EventHandler<MouseEvent> {
    private Juego juego;
    private Label errorLabel;
    private ArrayList<TextField> textFields;
    private Stage stage;

    public FormJugadoresEventHandler(Label errorLabel, Juego juego, ArrayList<TextField> textFields, Stage stage) {
        this.errorLabel = errorLabel;
        this.juego = juego;
        this.textFields = textFields;
        this.stage = stage;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        boolean jugadoresCargados = validarTextFields();
        if (jugadoresCargados) {
            try {
                juego.comenzar();
                new CampoDeJuego(stage);
            }
            catch (CantidadDeJugadoresInsuficienteException ignored){ }
        }
        errorLabel.setVisible(true);
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

    private Color getColor(int i){
        switch (i) {
            case 0: return Color.RED;
            case 1: return Color.BLUE;
            case 2: return Color.DARKORANGE;
            case 3: return Color.GREEN;
            case 4: return Color.BLACK;
            case 5: return Color.DARKMAGENTA;
        }
        return Color.RED;
    }
}
