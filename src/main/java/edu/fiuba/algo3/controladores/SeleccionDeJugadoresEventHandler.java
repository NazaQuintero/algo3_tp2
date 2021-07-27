package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.SeleccionCantidadJugadores;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SeleccionDeJugadoresEventHandler implements EventHandler<ActionEvent> {

    private final Stage stage;

    public SeleccionDeJugadoresEventHandler(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        SeleccionCantidadJugadores seleccionJugadores = new SeleccionCantidadJugadores();
        Scene nuevaEscena = new Scene(seleccionJugadores);
        stage.setScene(nuevaEscena);
        stage.setTitle("Seleccion de jugadores");
//        stage.setFullScreen(true);
        //stage.setFullScreenExitHint("");
        stage.setMinHeight(600);
        stage.setMinWidth(600);
    }
}
