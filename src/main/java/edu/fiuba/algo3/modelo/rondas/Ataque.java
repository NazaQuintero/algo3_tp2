package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;

public class Ataque implements Ronda {

    @Override
    public String obtenerDescripcion() {
        return "Ataque";
    }

    public void finalizarRonda(Turno unTurno) {
        unTurno.setRonda(new Reagrupe());
    }

    public Resultado atacarA(Pais atacante, Pais defensor) throws ElPaisNoEsLimitrofeException, EjercitosInsuficientesException {
        if (!this.puedeAtacar(atacante)) throw new EjercitosInsuficientesException();
        return atacante.atacarA(defensor);
    }

    public void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException {
        throw new NoEsRondaDeReagrupeException();
    }

    public void colocarEjercitos(Pais unPais) throws NoEsRondaDeColocacionException {
        throw new NoEsRondaDeColocacionException();
    }

    public void activarTarjeta(Tarjeta unaTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException {
        throw new ActivacionTarjetaEnRondaEquivocadaException();
    }


}
