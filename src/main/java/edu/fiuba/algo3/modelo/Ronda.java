package edu.fiuba.algo3.modelo;

public interface Ronda {
    String obtenerDescripcion();
    void finalizarRonda(Turno unTurno);
    void atacarA(Pais atacante, Pais defensor) throws NoEsRondaDeAtaqueException;
    void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException, ElPaisNoEsLimitrofeException;
    void colocarEjercitos(Pais unPais) throws NoEsRondaDeColocacionException;

    void activarTarjeta(Tarjeta unaTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException;
}
