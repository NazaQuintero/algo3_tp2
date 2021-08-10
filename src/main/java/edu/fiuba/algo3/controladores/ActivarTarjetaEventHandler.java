package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.vista.VentanaTarjetas;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ActivarTarjetaEventHandler implements EventHandler<MouseEvent> {

    private final Jugador jugador;
    private final VentanaTarjetas ventanaTarjetas;

    public ActivarTarjetaEventHandler(Jugador jugador, VentanaTarjetas ventanaTarjetas) {
        this.jugador = jugador;
        this.ventanaTarjetas = ventanaTarjetas;
    }

    @Override
    public void handle(MouseEvent event) {

        if (ventanaTarjetas.getTarjetaSeleccionada() == null)
            ventanaTarjetas.mostrarErrorNingunaTarjetaSeleccionada();
        else {
            Tarjeta tarjeta = ventanaTarjetas.getTarjetaSeleccionada();
            Pais pais = tarjeta.obtenerPais();

            if (pais.dominadoPor().equals(jugador)) {
                try {
                    tarjeta.activar();
                } catch (LaTarjetaYaFueActivadaException e) {
                    ventanaTarjetas.mostrarErrorYaFueActivada(pais.getNombre());
                }
            }
            ventanaTarjetas.setTarjetaSeleccionada(null);
        }
    }
}
