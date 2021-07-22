package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class Pais {
    String nombre;
    String continente;
    Fichas ejercito;
    private ArrayList<Pais> paisesLimitrofes;

    public Pais(String nombre) {
        this.nombre = nombre;
        this.paisesLimitrofes = new ArrayList<>();
        this.ejercito = new EjercitoNulo();

    }

    public int cantidadEjercitos() {
        return this.ejercito.obtenerCantidad();
    }

    public Jugador dominadoPor() {
        return this.ejercito.obtenerJugador();
    }

    public void colocarEjercito(Fichas unEjercito) {
        this.ejercito = unEjercito;
        this.ejercito.agregarPais(this);
    }

    public void modificarCantidadEjercito(int unaCantidad) {
        this.ejercito.modificarCantidad(unaCantidad);
    }

    public boolean esLimitrofeCon(Pais otroPais) {
        return this.paisesLimitrofes.contains(otroPais);
    }

    public void limitaCon(Pais otroPais) {
        paisesLimitrofes.add(otroPais);
    }

    public void atacarA(Pais defensor) {

        // if (!paisesLimitrofes.contains(defensor)) throw new ElPaisNoEsLimitrofeException();

        this.rolAtacante();
        defensor.recibirAtaque(this);
    }

    public void recibirAtaque(Pais atacante) {
        this.rolDefensor();
        Batalla nuevaBatalla = new Batalla(atacante, this);
        nuevaBatalla.batallar();
    }

    public Dados tirarDados() {
        return this.ejercito.tirarDados(this);
    }

    public int pedirCantidadAlJugador() {
        return this.ejercito.pedirCantidadAlJugador();
    }

    public void rolAtacante() {
        this.ejercito.rolAtacante();
    }

    public void rolDefensor() {
        this.ejercito.rolDefensor();
    }

    public void reagrupar(Pais destino) throws ElPaisNoEsLimitrofeException {
        if (this.esLimitrofeCon(destino)) {
            int cantidadEjercitos = pedirCantidadAlJugador(); //checkear que la cantidad es valida
            this.modificarCantidadEjercito(-cantidadEjercitos);
            destino.modificarCantidadEjercito(cantidadEjercitos);
        } else throw new ElPaisNoEsLimitrofeException();
    }

    public String obtenerNombre() {
        return this.nombre;
    }

    public boolean estaLibre() {
        return this.dominadoPor() == null;
    }
}