package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.SeleccionCantidadJugadores;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SeleccionDeJugadoresEventHandler implements EventHandler<ActionEvent> {

    private final Stage stage;
    private static final int ANCHO = 800;
    private static final int ALTO = 550;

    public SeleccionDeJugadoresEventHandler(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        SeleccionCantidadJugadores seleccionJugadores = new SeleccionCantidadJugadores(stage);
        Scene nuevaEscena = new Scene(seleccionJugadores, ANCHO, ALTO);
        stage.setScene(nuevaEscena);
        stage.setTitle("A.L.T.E.G.O.");
    }
}
