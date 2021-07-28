package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;

public class Ocupacion2 implements Objetivo {

    @Override
    public boolean estaCumplido(Jugador jugador) {
        // falta chequear limitrofes
        return MultitonContinentes.obtenerInstanciaDe("America del Sur").dominadoPor(jugador) &&
                MultitonContinentes.obtenerInstanciaDe("Europa").dominaCantidadDePaises(jugador, 7) &&
                jugador.poseeTresPaisesLimitrofes();
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar América del Sur, 7 países de Europa y 3 países limítrofes entre sí en cualquier lugar del mapa.";
    }
}
