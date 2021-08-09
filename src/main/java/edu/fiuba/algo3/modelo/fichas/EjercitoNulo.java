package edu.fiuba.algo3.modelo.fichas;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.roles.Rol;

public class EjercitoNulo implements Fichas {

    public Jugador obtenerJugador() {
        return null;
    }

    public int obtenerCantidad() {
        return 0;
    }

    public void modificarCantidad(int cantidad) {
    }

    @Override
    public Dados tirarDados(Pais pais) {
        return null;
    }

    @Override
    public void asignarRol(Rol unRol) {
    }

    public void agregarPais(Pais pais) {}
    public void setDados(Dados dados) {}

    @Override
    public Dados getDados() {
        return null;
    }

    @Override
    public void addObserver(Observer obs) {}

    @Override
    public void removeObserver(Observer obs) {}

    @Override
    public void notifyObservers() {}
}
