package edu.fiuba.algo3.modelo.fichas;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.roles.Rol;
import edu.fiuba.algo3.modelo.roles.RolIndefinido;

public class Ejercito implements Fichas {

    private Jugador jugador;
    private int cantidad;
    private Rol rol = new RolIndefinido();

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

    public int pedirCantidadAlJugador() {
        return this.jugador.pedirCantidad();
    }

    @Override
    public Dados tirarDados(Pais pais) {
        return this.rol.tirarDados(pais);
    }

    public void asignarRol(Rol unRol) { this.rol = unRol; }

    public void agregarPais(Pais pais) {
        this.jugador.agregarPais(pais);
    }
}
