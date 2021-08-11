package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.VentanaTarjetas;
import edu.fiuba.algo3.vista.VistaTarjeta;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TarjetaEventHandler implements EventHandler<MouseEvent> {

    private final VistaTarjeta vistaTarjeta;
    private final VentanaTarjetas ventanaTarjetas;

    public TarjetaEventHandler(VentanaTarjetas ventanaTarjetas, VistaTarjeta vistaTarjeta) {
        this.vistaTarjeta = vistaTarjeta;
        this.ventanaTarjetas = ventanaTarjetas;
    }

    @Override
    public void handle(MouseEvent event) {
        ventanaTarjetas.ocultarError();
        vistaTarjeta.cambiarSeleccion();
    }
}
