package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.vista.VentanaTarjetas;
import edu.fiuba.algo3.vista.VistaTarjeta;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TarjetaEventHandler implements EventHandler<MouseEvent> {

    private final VentanaTarjetas ventanaTarjetas;
    private final VistaTarjeta vistaTarjeta;

    public TarjetaEventHandler(VentanaTarjetas ventanaTarjetas, VistaTarjeta vistaTarjeta) {
        this.ventanaTarjetas = ventanaTarjetas;
        this.vistaTarjeta = vistaTarjeta;
    }

    @Override
    public void handle(MouseEvent event) {
        ventanaTarjetas.setTarjetaSeleccionada(vistaTarjeta.getTarjeta());
        ventanaTarjetas.update();
    }
}
