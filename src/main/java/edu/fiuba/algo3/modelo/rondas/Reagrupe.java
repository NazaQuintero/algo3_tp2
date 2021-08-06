package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import edu.fiuba.algo3.modelo.excepciones.ActivacionTarjetaEnRondaEquivocadaException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeAtaqueException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;

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

    public void reagrupar(Pais origen, Pais destino, int cantidad) throws ElPaisNoEsLimitrofeException {
        if (this.puedeReagrupar(origen)) {
            try {
                origen.reagrupar(destino, cantidad);
            } catch (ElPaisNoEsLimitrofeException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean puedeReagrupar(Pais pais) {
        return pais.cantidadEjercitos() > 1;
    }

    public void colocarEjercitos(Pais unPais, int cantidadEjercitos) throws NoEsRondaDeColocacionException {
        throw new NoEsRondaDeColocacionException();
    }

    public void activarTarjeta(Tarjeta unaTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException {
        throw new ActivacionTarjetaEnRondaEquivocadaException();
    }

}
