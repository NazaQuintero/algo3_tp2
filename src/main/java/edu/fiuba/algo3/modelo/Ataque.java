package edu.fiuba.algo3.modelo;

public class Ataque implements Ronda {

    @Override
    public String obtenerDescripcion() {
        return "Ataque";
    }

    public void finalizarRonda(Turno unTurno) {
        unTurno.setRonda(new Reagrupe());
    }

    public void atacarA(Pais atacante, Pais defensor) {
        atacante.atacarA(defensor);
    }

    public void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException {
        throw new NoEsRondaDeReagrupeException();
    }

    public void colocarEjercitos(Pais unPais) throws NoEsRondaDeColocacionException {
        throw new NoEsRondaDeColocacionException();
    }


}
