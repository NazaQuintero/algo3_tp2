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
//        Dados dadosAtacante = atacante.dominadoPor().tirarDados(cantidadEjercitosAtacante);
//        Dados dadosDefensor =  defensor.dominadoPor().tirarDados(defensor.cantidadEjercitos());
        DueloDeDados unDuelo = new DueloDeDados();
        ArrayList<Integer> resultados = unDuelo.iniciarDuelo(atacante.dominadoPor().tirarDados(cantidadEjercitosAtacante), defensor.dominadoPor().tirarDados(defensor.cantidadEjercitos())); // [>0 ==0  <0]
        computarResultados(resultados);
        if(defensor.cantidadEjercitos() <= 0) defensor.colocarEjercito(new Ejercito(atacante.dominadoPor(), 1)); // si solo recibe el jugador, adentro se pide la cantidad de ejercitos que se desea pasar a este pais
    }

    private void computarResultados(ArrayList<Integer> resultados) {
        for(int resultado : resultados) {
            if((resultado > 0)) defensor.modificarCantidadEjercito(-1);// > 0, aca restamos 1 ejercito en defensor
            else atacante.modificarCantidadEjercito(-1);// <= 0, aca restamos 1 ejercito en atacante
        }
    }

}

