package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.batallas_de_dados.Resultado;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import edu.fiuba.algo3.modelo.excepciones.*;

public interface Ronda {

    String obtenerDescripcion();

    void finalizarRonda(Turno unTurno);

    Resultado atacarA(Pais atacante, Pais defensor, int cantidadEjercitos) throws NoEsRondaDeAtaqueException, ElPaisNoEsLimitrofeException, CantidadDeEjercitosInValidaException;

    void reagrupar(Pais origen, Pais destino, int cantidad) throws NoEsRondaDeReagrupeException, ElPaisNoEsLimitrofeException, CantidadDeEjercitosInValidaException;

    void colocarEjercitos(Pais unPais, int cantidadEjercitos) throws NoEsRondaDeColocacionException, NoQuedanMasEjercitosPorColocarException;

    void activarTarjeta(Tarjeta unaTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException, LaTarjetaYaFueActivadaException;

}
