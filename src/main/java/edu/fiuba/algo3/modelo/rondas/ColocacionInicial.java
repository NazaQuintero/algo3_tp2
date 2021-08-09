package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.turnos.Turno;

public class ColocacionInicial extends RondaColocacion {
    public ColocacionInicial(Jugador unJugador){
        super(unJugador);
    }

    @Override
    public void calcularEjercitosColocables(Jugador unJugador) {
        cantidadEjercitosColocables = 5;
    }

    @Override
    public String obtenerDescripcion() {
        return "Ronda de colocación inicial";
    }

    @Override
    public void finalizarRonda(Turno unTurno) {
        unTurno.finalizarTurnoActual();
        if (unTurno.leTocaALPrimerJugador()) unTurno.setRonda(new ColocacionSecundaria(unTurno.obtenerJugadorTurnoActual()));
        else calcularEjercitosColocables(unTurno.obtenerJugadorTurnoActual());
    }
}
