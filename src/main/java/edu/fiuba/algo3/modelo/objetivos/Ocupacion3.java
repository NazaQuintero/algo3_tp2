package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;

public class Ocupacion3 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) {
        return MultitonContinentes.obtenerInstanciaDe("Asia").dominadoPor(jugador) &&
                MultitonContinentes.obtenerInstanciaDe("America del Sur").dominaCantidadDePaises(jugador, 2);
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar Asia y 2 países de América del Sur.";
    }
}
