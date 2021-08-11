package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;
import edu.fiuba.algo3.modelo.turnos.Turno;

public class Colocacion extends RondaColocacion {

    public Colocacion(Jugador unJugador) {
        super(unJugador);
    }

    @Override
    public void calcularEjercitosColocables(Jugador unJugador) {
        this.cantidadEjercitosColocables = 3;
        if (unJugador.cantidadPaisesDominados() > 6) this.cantidadEjercitosColocables = unJugador.cantidadPaisesDominados() / 2;
        this.cantidadEjercitosColocables += unJugador.ordenCanje();
        MultitonContinentes.obtenerContinentes().stream().filter(continente -> continente.dominadoPor(unJugador))
                .forEach(continente -> cantidadEjercitosColocables += continente.getCantidadDeEjercitos());
    }

    @Override
    public void finalizarRonda(Turno unTurno) {
        unTurno.finalizarTurnoActual();
        if(unTurno.leTocaALPrimerJugador()) unTurno.setRonda(new Ataque());
        else calcularEjercitosColocables(unTurno.obtenerJugadorTurnoActual());
    }

    @Override
    public String obtenerDescripcion() {
        return "Ronda de colocación";
    }
}
