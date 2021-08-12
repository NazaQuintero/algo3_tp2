package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.CantidadDeJugadoresInsuficienteException;
import edu.fiuba.algo3.vista.CampoDeJuego;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FormularioJugadoresEventHandler implements EventHandler<Event> {
    private Juego juego;
    private Label errorLabel;
    private ArrayList<TextField> textFields;
    private Stage stage;

    public FormularioJugadoresEventHandler(Label errorLabel, Juego juego, ArrayList<TextField> textFields, Stage stage) {
        this.errorLabel = errorLabel;
        this.juego = juego;
        this.textFields = textFields;
        this.stage = stage;
    }

    @Override
    public void handle(Event event) {

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED || ((KeyEvent) event).getCode() == KeyCode.ENTER) {
            boolean jugadoresCargados = validarTextFields();

            if (jugadoresCargados) {
                ReproductorDeSonido.getInstance().playClick();
                try {
                    juego.comenzar();
                    new CampoDeJuego(stage, juego);
                }
                catch (CantidadDeJugadoresInsuficienteException ignored) {}
            }
            else {
                errorLabel.setVisible(true);
                ReproductorDeSonido.getInstance().playError();
            }
        }
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
            Jugador jugador = new Jugador(nombreJugador.getText(),getColor(i));
            juego.agregarJugador(jugador);
        }

        return jugadoresCargados;
    }

    private Color getColor(int i){
        switch (i) {
            case 0: return Color.web("cc3311");
            case 1: return Color.web("077bb0");
            case 2: return Color.ORANGE;
            case 3: return Color.web("27e63b");
            case 4: return Color.web("ee3377");
            case 5: return Color.web("000000");
        }
        return Color.web("cc3311");
    }
}
