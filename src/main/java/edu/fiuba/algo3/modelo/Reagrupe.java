package edu.fiuba.algo3.modelo;

public class Reagrupe implements Ronda {

    @Override
    public String obtenerDescripcion() {
        return "Reagrupe";
    }

    @Override
    public void finalizarRonda(Turno unTurno) {
        unTurno.finalizarTurnoActual();
        if(unTurno.leTocaALPrimerJugador()) unTurno.setRonda(new Colocacion());
        else unTurno.setRonda(new Ataque());
    }

}
