package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import edu.fiuba.algo3.modelo.excepciones.*;

public interface Ronda {
    String obtenerDescripcion();
    void finalizarRonda(Turno unTurno);
    Resultado atacarA(Pais atacante, Pais defensor) throws NoEsRondaDeAtaqueException, ElPaisNoEsLimitrofeException, EjercitosInsuficientesException;
    void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException, ElPaisNoEsLimitrofeException;
    void colocarEjercitos(Pais unPais) throws NoEsRondaDeColocacionException;

    void activarTarjeta(Tarjeta unaTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException, LaTarjetaYaFueActivadaException;
}
