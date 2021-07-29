package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;

public class Ocupacion7 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) {

        return MultitonContinentes.obtenerInstanciaDe("Oceania").dominadoPor(jugador) &&
                MultitonContinentes.obtenerInstanciaDe("America del Norte").dominadoPor(jugador) &&
                MultitonContinentes.obtenerInstanciaDe("Europa").dominaCantidadDePaises(jugador, 2);
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar Oceanía, América del Norte y 2 países de Europa.";
    }
}
