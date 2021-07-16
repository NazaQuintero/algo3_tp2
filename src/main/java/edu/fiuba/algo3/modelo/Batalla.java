package edu.fiuba.algo3.modelo;
import java.util.ArrayList;

public class Batalla  {

    private Pais atacante;
    private Pais defensor;
    int cantidadEjercitosAtacante;

    public Batalla(Pais atacante, int cantidadEjercitos, Pais defensor) {
        this.atacante = atacante;
        this.cantidadEjercitosAtacante = cantidadEjercitos;
        this.defensor = defensor;
    }

    public void batallar() {
        ResultadoBatalla resultado = new ResultadoBatalla(atacante, defensor);
        resultado.computarResultado(cantidadEjercitosAtacante);
        resultado.procesarResultado();
         // si solo recibe el jugador, adentro se pide la cantidad de ejercitos que se desea pasar a este pais
    }

    private void computarResultados(ArrayList<Integer> resultados) {
        for(int resultado : resultados) {
            if((resultado > 0)) defensor.modificarCantidadEjercito(-1);// > 0, aca restamos 1 ejercito en defensor
            else atacante.modificarCantidadEjercito(-1);// <= 0, aca restamos 1 ejercito en atacante
        }
    }

}

