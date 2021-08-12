package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.batallas_de_dados.Resultado;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.tarjetas.MultitonTarjetas;
import edu.fiuba.algo3.modelo.turnos.Turno;

public class Ataque implements Ronda {

    @Override
    public String obtenerDescripcion() {
        return "Ronda de ataque";
    }

    public void finalizarRonda(Turno unTurno) {
        Jugador jugador = unTurno.obtenerJugadorTurnoActual();
        if (jugador.huboConquista()) jugador.recibirTarjeta(MultitonTarjetas.obtenerTarjetaAleatoria());
        jugador.setConquista(false);

        unTurno.setRonda(new Reagrupe());
    }

    public Resultado atacarA(Pais atacante, Pais defensor, int cantidadEjercitos) throws ElPaisNoEsLimitrofeException, CantidadDeEjercitosInValidaException {
        return atacante.atacarA(defensor, cantidadEjercitos);
    }

    public void reagrupar(Pais origen, Pais destino, int cantidad) throws NoEsRondaDeReagrupeException {
        throw new NoEsRondaDeReagrupeException();
    }

    public void colocarEjercitos(Pais unPais, int cantidadEjercitos) throws NoEsRondaDeColocacionException {
        throw new NoEsRondaDeColocacionException();
    }

    public void activarTarjeta(Tarjeta unaTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException {
        throw new ActivacionTarjetaEnRondaEquivocadaException();
    }

}
