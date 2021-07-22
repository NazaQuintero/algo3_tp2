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

    public void atacarA(Pais atacante, Pais defensor) throws NoEsRondaDeAtaqueException {
        throw new NoEsRondaDeAtaqueException();
    }

    public void reagrupar(Pais origen, Pais destino) throws ElPaisNoEsLimitrofeException {
        try {
            origen.reagrupar(destino);
        } catch (ElPaisNoEsLimitrofeException e) {
            throw new ElPaisNoEsLimitrofeException();
        }
    }

    public void colocarEjercitos(Pais unPais) throws NoEsRondaDeColocacionException {
        throw new NoEsRondaDeColocacionException();
    }

}
