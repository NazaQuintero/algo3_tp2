package edu.fiuba.algo3.modelo;
import java.util.ArrayList;

public class Batalla {

    public Batalla(Pais atacante, int cantidadEjercitos, Pais defensor) {
        Dados dadosAtacante = atacante.dominadoPor().tirarDados(cantidadEjercitos);
        Dados dadosDefensor =  defensor.dominadoPor().tirarDados(defensor.cantidadEjercitos());
        ArrayList<Integer> resultados = dadosAtacante.compararDadosCon(dadosDefensor); // [>0 ==0  <0]
        for(int resultado : resultados) {
            if((resultado > 0)) defensor.modificarCantidadEjercito(-1);// > 0, aca restamos 1 ejercito en defensor
            else atacante.modificarCantidadEjercito(-1);// <= 0, aca restamos 1 ejercito en atacante
        }
        if(defensor.cantidadEjercitos() <= 0) defensor.colocarEjercito(new Ejercito(atacante.dominadoPor(), 1)); // si solo recibe el jugador, adentro se pide la cantidad de ejercitos que se desea pasar a este pais
    }

}

