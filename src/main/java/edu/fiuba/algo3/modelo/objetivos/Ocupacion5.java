package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;

public class Ocupacion5 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) throws ContinenteInvalidoException {
        return MultitonContinentes.obtenerInstanciaDe("America del Norte").dominadoPor(jugador) &&
                MultitonContinentes.obtenerInstanciaDe("Oceania").dominaCantidadDePaises(jugador, 2) &&
                MultitonContinentes.obtenerInstanciaDe("Asia").dominaCantidadDePaises(jugador, 4);
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar América del Norte, 2 países de Oceanía y 4 de Asia.";
    }
}
