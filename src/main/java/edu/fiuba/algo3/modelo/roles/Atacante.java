package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.Pais;

public class Atacante implements Rol {
    public Dados tirarDados(Pais pais) {
        int cantidadEjercitosAtacante = this.obtenerCantidadDeEjercitos(pais.pedirCantidadAlJugador());

        return new Dados(cantidadEjercitosAtacante);
    }

    private int obtenerCantidadDeEjercitos(int cantidad) {
        return (cantidad > 3) ? 3 : cantidad - 1;
    }

}
