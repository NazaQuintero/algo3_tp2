package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;

public class Ocupacion8 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) throws ContinenteInvalidoException {

        return MultitonContinentes.obtenerInstanciaDe("America del Sur").dominadoPor(jugador) &&
                MultitonContinentes.obtenerInstanciaDe("Africa").dominadoPor(jugador) &&
                MultitonContinentes.obtenerInstanciaDe("America del Norte").dominaCantidadDePaises(jugador, 5);
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar América del Sur, África y 5 países de América del Norte.";
    }
}
