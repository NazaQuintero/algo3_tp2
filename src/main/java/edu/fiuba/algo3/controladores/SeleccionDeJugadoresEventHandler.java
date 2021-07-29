package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vista.SeleccionCantidadJugadores;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SeleccionDeJugadoresEventHandler implements EventHandler<ActionEvent> {

    private final Stage stage;
    private final Juego juego;

    public SeleccionDeJugadoresEventHandler(Stage stage, Juego juego) {
        this.stage = stage;
        this.juego = juego;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        SeleccionCantidadJugadores seleccionJugadores = new SeleccionCantidadJugadores(stage, juego);
        Scene nuevaEscena = new Scene(seleccionJugadores);
        stage.setScene(nuevaEscena);
        stage.setTitle("Seleccion de jugadores");
        stage.setMinHeight(600);
        stage.setMinWidth(600);
    }
}
