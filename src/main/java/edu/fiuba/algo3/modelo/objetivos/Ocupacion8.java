package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

public class Ocupacion8 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) {
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar América del Sur, África y 5 países de América del Norte.";
    }
}
