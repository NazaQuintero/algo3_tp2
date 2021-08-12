package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.vista.VentanaTarjetas;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class CanjearTarjetasEventHandler implements EventHandler<MouseEvent> {
    private final Jugador jugador;
    private final VentanaTarjetas ventanaTarjetas;

    public CanjearTarjetasEventHandler(Jugador jugador, VentanaTarjetas ventanaTarjetas) {
        this.jugador = jugador;
        this.ventanaTarjetas = ventanaTarjetas;
    }

    @Override
    public void handle(MouseEvent event) {
        ArrayList<Tarjeta> tarjetas = ventanaTarjetas.getTarjetasSeleccionadas();
        canjear(tarjetas);
    }

    void canjear(ArrayList<Tarjeta> tarjetas){

        if (tarjetas.size() != 3){
            ventanaTarjetas.mostrarError("Se deben seleccionar 3 tarjetas");
            ReproductorDeSonido.getInstance().playError();
            return;
        }

        try {
            jugador.canjearTarjetas(tarjetas);
            ReproductorDeSonido.getInstance().playCanje();
            ventanaTarjetas.update();
            ventanaTarjetas.mostrarMensajeValido("Tarjetas canjeadas correctamente!");
        }
        catch (JugadorNoTieneTodasLasTarjetasException ignored) {}

        catch (SinCanjeHabilitadoException e) {
            ReproductorDeSonido.getInstance().playError();
            ventanaTarjetas.mostrarError("El canje no es válido");
        }
    }
}
