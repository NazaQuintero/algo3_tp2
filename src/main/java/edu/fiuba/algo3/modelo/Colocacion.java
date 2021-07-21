package edu.fiuba.algo3.modelo;

public class Colocacion implements Ronda {


    @Override
    public String obtenerDescripcion() {
        return "Colocacion";
    }

    @Override
    public void finalizarRonda(Turno unTurno) {
        if(unTurno.leTocaALPrimerJugador()) unTurno.setRonda(new Ataque());
        unTurno.finalizarTurnoActual();
    }


}
