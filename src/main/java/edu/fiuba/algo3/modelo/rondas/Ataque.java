package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import edu.fiuba.algo3.modelo.excepciones.ActivacionTarjetaEnRondaEquivocadaException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeReagrupeException;

public class Ataque implements Ronda {

    @Override
    public String obtenerDescripcion() {
        return "Ataque";
    }

    public void finalizarRonda(Turno unTurno) {
        unTurno.setRonda(new Reagrupe());
    }

    public void atacarA(Pais atacante, Pais defensor) {
        atacante.atacarA(defensor);

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
