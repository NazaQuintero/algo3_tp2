package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import edu.fiuba.algo3.modelo.excepciones.*;

public interface Ronda {
    String obtenerDescripcion();
    void finalizarRonda(Turno unTurno);
    ResultadoBatalla atacarA(Pais atacante, Pais defensor, Dados dadosAtacante, Dados dadosDefensor) throws NoEsRondaDeAtaqueException;
    void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException, ElPaisNoEsLimitrofeException;
    void colocarEjercitos(Pais unPais) throws NoEsRondaDeColocacionException;

    void activarTarjeta(Tarjeta unaTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException;
}
