package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.vista.CampoDeJuego;
import edu.fiuba.algo3.vista.MenuLateralDerecho;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class FinalizarRondaEventHandler implements EventHandler<Event> {

    private final CampoDeJuego campoDeJuego;

    public FinalizarRondaEventHandler(CampoDeJuego campoDeJuego) {
        this.campoDeJuego = campoDeJuego;
    }

    @Override
    public void handle(Event event) {
        Jugador jugador = campoDeJuego.getTurno().obtenerJugadorTurnoActual();
        MenuLateralDerecho menuLateralDerecho = (MenuLateralDerecho) campoDeJuego.getRight();
        menuLateralDerecho.ocultarError();

        try {
            campoDeJuego.getTurno().finalizarRonda(jugador);
        } catch (ElJugadorNoTieneTurnoException e) {
            e.printStackTrace();
        }
    }
}
