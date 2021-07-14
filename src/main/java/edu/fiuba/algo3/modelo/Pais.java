package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Pais {

    Jugador jugadorDominante;
    private int cantidadEjercitos;
    private ArrayList<Pais> paisesLimitrofes;

    public Pais(String nombre) {

        this.cantidadEjercitos = 0;
        this.paisesLimitrofes = new ArrayList();

    }

    public int cantidadEjercitos() { return this.cantidadEjercitos; }

    public Jugador dominadoPor() {
        return this.jugadorDominante;
    }

    public void colocarEjercitos(int cantidadDeEjercitos, Jugador jugador) throws PaisOcupadoPorOtroJugadorException {
        if (this.jugadorDominante == null) {
            this.jugadorDominante = jugador;
        }
        else if (jugador != this.dominadoPor()) throw new PaisOcupadoPorOtroJugadorException();
        this.cantidadEjercitos += cantidadDeEjercitos;



    }

    public boolean esLimitrofeCon(Pais otroPais) {

        int i = 0;
        boolean encontrado = false;

        while (i < paisesLimitrofes.size() && !encontrado) {
            if (paisesLimitrofes.get(i) == otroPais) encontrado = true;
            else i++;
        }

        return encontrado;
    }

    public void limitaCon(Pais otroPais) { paisesLimitrofes.add(otroPais); }

    // La lista de dados esta ordenada de mayor a menor
    public void atacarA(Pais paisDefensor, ArrayList<Integer> dadosAtacante) {
        paisDefensor.recibirAtaque(dadosAtacante, this);
    }

    // La lista de dados esta ordenada de mayor a menor
    private void recibirAtaque(ArrayList<Integer> dadosAtacante, Pais paisAtacante) {
        ArrayList<Integer> dadosDefensor = new ArrayList<Integer>(Arrays.asList(3, 3, 3));
        int cantidadDeVictoriasDefensor = 0;
        for (int i = 0; i < dadosDefensor.size() ; i++) {
            if(dadosDefensor.get(i) >= dadosAtacante.get(i)) {
                cantidadDeVictoriasDefensor++;
            }

        }
        try {
            this.colocarEjercitos(-(dadosDefensor.size() - cantidadDeVictoriasDefensor), this.dominadoPor());
            paisAtacante.colocarEjercitos(-cantidadDeVictoriasDefensor, paisAtacante.dominadoPor());
        }
        catch (Exception e){

        }

    }

}
