package edu.fiuba.algo3.modelo.fichas;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.roles.Rol;
import edu.fiuba.algo3.modelo.roles.RolIndefinido;

import java.util.ArrayList;

public class Ejercito implements Fichas {

    private Jugador jugador;
    private int cantidad;
    private Rol rol = new RolIndefinido();
    private Dados dados;
    private ArrayList<Observer> observers = new ArrayList<>();

    public Dados getDados() {
        return dados;
    }

    public Ejercito(Jugador jugador) {
        this.jugador = jugador;
        this.cantidad = 1;
    }

    public void modificarCantidad(int aumentarEn) {
        this.cantidad += aumentarEn;
    }

    public Jugador obtenerJugador() {
        return jugador;
    }

    public int obtenerCantidad() {
        return cantidad;
    }

    public void setDados(Dados dados) {
        this.dados = dados;
    }

    @Override
    public Dados tirarDados(Pais pais) {
        if (dados != null) return dados;
        dados = this.rol.tirarDados(pais);
        return dados;
    }

    public void asignarRol(Rol unRol) { this.rol = unRol; }

    public void agregarPais(Pais pais) {
        this.jugador.agregarPais(pais);
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
