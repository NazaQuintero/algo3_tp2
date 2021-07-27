package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.CreacionJugadores;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CrearJugadoresEventHandler implements EventHandler<ActionEvent> {

    /*
    private final int cantidadJugadores;

    public CrearJugadoresEventHandler(int cantidadJugadores) {
        this.cantidadJugadores = cantidadJugadores;
    }
    */

    private final Stage stage;

    public CrearJugadoresEventHandler(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        CreacionJugadores creacionJugadores = new CreacionJugadores(stage);
        Scene nuevaEscena = new Scene(creacionJugadores);
        stage.setScene(nuevaEscena);
        stage.setTitle("Creacion de jugadores");
        stage.setMinHeight(600);
        stage.setMinWidth(600);
    }
}
