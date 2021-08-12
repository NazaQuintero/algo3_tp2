package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.batallas_de_dados.Resultado;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;

public class Reagrupe implements Ronda {

    @Override
    public String obtenerDescripcion() {
        return "Ronda de reagrupe";
    }

    @Override
    public void finalizarRonda(Turno unTurno) {
        unTurno.finalizarTurnoActual();
        if (unTurno.leTocaALPrimerJugador()) unTurno.setRonda(new Colocacion(unTurno.obtenerJugadorTurnoActual()));
        else unTurno.setRonda(new Ataque());
    }

    public Resultado atacarA(Pais atacante, Pais defensor, int cantidadEjercitos) throws NoEsRondaDeAtaqueException {
        throw new NoEsRondaDeAtaqueException();
    }

    public void reagrupar(Pais origen, Pais destino, int cantidad) throws ElPaisNoEsLimitrofeException, CantidadDeEjercitosInValidaException {
       origen.reagrupar(destino, cantidad);
    }

    public void colocarEjercitos(Pais unPais, int cantidadEjercitos) throws NoEsRondaDeColocacionException {
        throw new NoEsRondaDeColocacionException();
    }

    public void activarTarjeta(Tarjeta unaTarjeta) throws LaTarjetaYaFueActivadaException {
        unaTarjeta.activar();
    }

}
