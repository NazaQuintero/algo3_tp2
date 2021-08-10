package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;
import edu.fiuba.algo3.modelo.excepciones.NoQuedanMasEjercitosPorColocarException;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeAtaqueException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeReagrupeException;

public abstract class RondaColocacion implements Ronda {

    protected int cantidadEjercitosColocables;

    public RondaColocacion(Jugador unJugador) {
        calcularEjercitosColocables(unJugador);
    }

    public int getCantidadEjercitosColocables() {
        return cantidadEjercitosColocables;
    }

    public abstract void calcularEjercitosColocables(Jugador unJugador);

    @Override
    public abstract String obtenerDescripcion();

    public abstract void finalizarRonda(Turno unTurno);

    public Resultado atacarA(Pais atacante, Pais defensor, int cantidadEjercitos) throws NoEsRondaDeAtaqueException {
        throw new NoEsRondaDeAtaqueException();
    }

    public void reagrupar(Pais origen, Pais destino, int cantidad) throws NoEsRondaDeReagrupeException {
        throw new NoEsRondaDeReagrupeException();
    }

    public void colocarEjercitos(Pais unPais, int cantidadEjercitos) throws NoQuedanMasEjercitosPorColocarException {
        if (this.cantidadEjercitosColocables > 0) {
            if (cantidadEjercitos <= this.cantidadEjercitosColocables) {
                unPais.modificarCantidadEjercito(cantidadEjercitos);
                this.cantidadEjercitosColocables -= cantidadEjercitos;
            }
        }
        else throw new NoQuedanMasEjercitosPorColocarException();
    }

    public void activarTarjeta(Tarjeta unaTarjeta) throws LaTarjetaYaFueActivadaException {
        unaTarjeta.activar();
    }

}
