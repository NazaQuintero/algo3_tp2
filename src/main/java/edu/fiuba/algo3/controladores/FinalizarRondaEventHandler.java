package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.vista.CampoDeJuego;
import edu.fiuba.algo3.vista.MenuLateralDerecho;
import javafx.event.Event;
import javafx.event.EventHandler;

public class FinalizarRondaEventHandler implements EventHandler<Event> {

    private final CampoDeJuego campoDeJuego;

    public FinalizarRondaEventHandler(CampoDeJuego campoDeJuego) {
        this.campoDeJuego = campoDeJuego;
    }

    @Override
    public void handle(Event event) {
        Jugador jugador = campoDeJuego.getJuego().getTurno().obtenerJugadorTurnoActual();
        MenuLateralDerecho menuLateralDerecho = (MenuLateralDerecho) campoDeJuego.getRight();
        menuLateralDerecho.ocultarError();

        try {
            campoDeJuego.getJuego().getTurno().finalizarRonda(jugador);
            campoDeJuego.setPaisSeleccionado(null);
        } catch (ElJugadorNoTieneTurnoException ignored) { }
    }
}
