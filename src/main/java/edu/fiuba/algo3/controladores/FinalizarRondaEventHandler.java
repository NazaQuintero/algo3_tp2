package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.vista.CampoDeJuego;
import javafx.event.Event;
import javafx.event.EventHandler;

public class FinalizarRondaEventHandler implements EventHandler<Event> {

    private final CampoDeJuego campoDeJuego;

    public FinalizarRondaEventHandler(CampoDeJuego campoDeJuego) {
        this.campoDeJuego = campoDeJuego;
    }

    @Override
    public void handle(Event event) {
        System.out.println("Finalizar Ronda llamado..");
        Jugador jugador = campoDeJuego.getTurno().obtenerJugadorTurnoActual();
        try {
            campoDeJuego.getTurno().finalizarRonda(jugador);
        } catch (ElJugadorNoTieneTurnoException e) {
            e.printStackTrace();
        }
    }
}
