package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

public class Ocupacion5 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) {
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar América del Norte, 2 países de Oceanía y 4 de Asia.";
    }
}
