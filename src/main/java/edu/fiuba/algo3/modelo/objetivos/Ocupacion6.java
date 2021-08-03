package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.continentes.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;

public class Ocupacion6 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) throws ContinenteInvalidoException {
        return MultitonContinentes.obtenerInstanciaDe("Africa").dominaCantidadDePaises(jugador, 2) &&
                MultitonContinentes.obtenerInstanciaDe("Oceania").dominaCantidadDePaises(jugador, 2) &&
                MultitonContinentes.obtenerInstanciaDe("America del Sur").dominaCantidadDePaises(jugador, 2) &&
                MultitonContinentes.obtenerInstanciaDe("Europa").dominaCantidadDePaises(jugador, 3) &&
                MultitonContinentes.obtenerInstanciaDe("America del Norte").dominaCantidadDePaises(jugador, 4) &&
                MultitonContinentes.obtenerInstanciaDe("Asia").dominaCantidadDePaises(jugador, 3);
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar 2 países de Oceanía, 2 países de África, 2 países de América del Sur, 3 países de Europa, 4 de América del Norte y 3 de Asia.";
    }
}
