package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;

public class Ocupacion1 implements Objetivo {

    @Override
    public boolean estaCumplido(Jugador jugador) throws ContinenteInvalidoException {
        return MultitonContinentes.obtenerInstanciaDe("Africa").dominadoPor(jugador) &&
                MultitonContinentes.obtenerInstanciaDe("America del Norte").dominaCantidadDePaises(jugador, 5) &&
                MultitonContinentes.obtenerInstanciaDe("Europa").dominaCantidadDePaises(jugador, 4);
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar África, 5 países de América del Norte y 4 países de Europa.";
    }
}
