package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.CreacionJugadores;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CrearJugadoresEventHandler implements EventHandler<ActionEvent> {

    private final Stage stage;
    private static final int ANCHO = 800;
    private static final int ALTO = 550;
    private final int cantidadJugadores;

    public CrearJugadoresEventHandler(Stage stage, int cantidadJugadores) {
        this.stage = stage;
        this.cantidadJugadores = cantidadJugadores;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        CreacionJugadores creacionJugadores = new CreacionJugadores(stage, cantidadJugadores);
        Scene nuevaEscena = new Scene(creacionJugadores, ANCHO, ALTO);
        stage.setScene(nuevaEscena);
        stage.setTitle("A.L.T.E.G.O.");
    }
}
