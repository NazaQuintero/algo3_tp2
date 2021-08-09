package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.turnos.Turno;

public class ColocacionSecundaria extends RondaColocacion {
    public ColocacionSecundaria(Jugador unJugador){
        super(unJugador);
    }

    @Override
    public void calcularEjercitosColocables(Jugador unJugador) {
        cantidadEjercitosColocables = 3;
    }

    @Override
    public String obtenerDescripcion() {
        return "Ronda de colocación secundaria";
    }

    @Override
    public void finalizarRonda(Turno unTurno) {
        unTurno.finalizarTurnoActual();
        if(unTurno.leTocaALPrimerJugador()) unTurno.setRonda(new Ataque());
        else calcularEjercitosColocables(unTurno.obtenerJugadorTurnoActual());
    }
}
