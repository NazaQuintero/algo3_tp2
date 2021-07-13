package edu.fiuba.algo3.modelo;

public class Pais {

    Jugador jugadorDominante;
    private int cantidadEjercitos;

    public Pais(String nombre) {
        this.cantidadEjercitos = 0;
    }

    public int cantidadEjercitos() { return this.cantidadEjercitos; }

    public Jugador dominadoPor() {
        return this.jugadorDominante;
    }

    public void colocarEjercitos(int cantidadDeEjercitos, Jugador jugador) throws PaisOcupadoPorOtroJugadorException {
        if (this.jugadorDominante == null) {
            this.jugadorDominante = jugador;
            this.cantidadEjercitos += cantidadDeEjercitos;
        } else if (this.jugadorDominante == jugador) {
            this.cantidadEjercitos += cantidadDeEjercitos;
        } else {
            throw new PaisOcupadoPorOtroJugadorException();
        }

    }
}
