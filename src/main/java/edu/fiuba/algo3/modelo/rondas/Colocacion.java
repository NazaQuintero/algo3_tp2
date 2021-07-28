package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeAtaqueException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeReagrupeException;

public class Colocacion implements Ronda {


    @Override
    public String obtenerDescripcion() {
        return "Colocacion";
    }

    @Override
    public void finalizarRonda(Turno unTurno) {
        unTurno.finalizarTurnoActual();
        if(unTurno.leTocaALPrimerJugador()) unTurno.setRonda(new Ataque());
    }

    public ResultadoBatalla atacarA(Pais atacante, Pais defensor) throws NoEsRondaDeAtaqueException {
        throw new NoEsRondaDeAtaqueException();
    }

    public void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException {
        throw new NoEsRondaDeReagrupeException();
    }

    public void colocarEjercitos(Pais unPais) {
        unPais.modificarCantidadEjercito(unPais.pedirCantidadAlJugador());
    }

    /*
    public void colocarEjercitos(Jugador jugador) {

        int cantidadEjercitosAColocar = jugador.cantidadPaisesDominados()/2;

    }
    */

    public void activarTarjeta(Tarjeta unaTarjeta) throws LaTarjetaYaFueActivadaException {
        unaTarjeta.activar();
    }
}
