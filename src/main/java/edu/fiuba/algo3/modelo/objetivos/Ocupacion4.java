package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.continentes.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;

public class Ocupacion4 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) throws ContinenteInvalidoException {
        return MultitonContinentes.obtenerInstanciaDe("Europa").dominadoPor(jugador) &&
                MultitonContinentes.obtenerInstanciaDe("Asia").dominaCantidadDePaises(jugador, 4) &&
                MultitonContinentes.obtenerInstanciaDe("America del Sur").dominaCantidadDePaises(jugador, 2);
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar Europa, 4 países de Asia y 2 países de América de Sur.";
    }
}
