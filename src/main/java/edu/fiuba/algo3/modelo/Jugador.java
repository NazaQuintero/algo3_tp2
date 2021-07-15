package edu.fiuba.algo3.modelo;

public class Jugador {
    private String color = "";
    private int cantidadPaisesDominados = 0;
    private Objetivo secreto;
    private Objetivo general = new General();


    public String mostrarColor() {
        return color;
    }

    public int cantidadPaisesDominados() {
        return cantidadPaisesDominados;
    }

    public void colocarEjercitos(int cantidadDeEjercitos, Pais pais) throws PaisOcupadoPorOtroJugadorException {
        pais.colocarEjercito(new Ejercito(this, cantidadDeEjercitos));
        cantidadPaisesDominados++;
    }

    public void asignarColor(String color) {
        this.color = color;
    }

    public Objetivo obtenerObjetivoSecreto() {
        return this.secreto;
    }

    public Objetivo obtenerObjetivoGeneral() {
        return this.general;
    }

    public Dados tirarDados(int cantidadEjercitos) {
        return new Dados(cantidadEjercitos);
    }

}
