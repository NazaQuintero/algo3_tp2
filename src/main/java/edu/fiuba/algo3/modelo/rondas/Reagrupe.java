package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import edu.fiuba.algo3.modelo.excepciones.ActivacionTarjetaEnRondaEquivocadaException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeAtaqueException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;

public class Reagrupe implements Ronda {

    @Override
    public String obtenerDescripcion() {
        return "Reagrupe";
    }

    @Override
    public void finalizarRonda(Turno unTurno) {
        unTurno.finalizarTurnoActual();
        if(unTurno.leTocaALPrimerJugador()) unTurno.setRonda(new Colocacion());
        else unTurno.setRonda(new Ataque());
    }

    public ResultadoBatalla atacarA(Pais atacante, Pais defensor, Dados dadosAtacante, Dados dadosDefensor) throws NoEsRondaDeAtaqueException {
        throw new NoEsRondaDeAtaqueException();
    }

    public void reagrupar(Pais origen, Pais destino) throws ElPaisNoEsLimitrofeException {
        try {
            origen.reagrupar(destino);
        } catch (ElPaisNoEsLimitrofeException e) {
            throw new ElPaisNoEsLimitrofeException();
        }
    }

    public void colocarEjercitos(Pais unPais) throws NoEsRondaDeColocacionException {
        throw new NoEsRondaDeColocacionException();
    }

    public void activarTarjeta(Tarjeta unaTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException {
        throw new ActivacionTarjetaEnRondaEquivocadaException();
    }

}
