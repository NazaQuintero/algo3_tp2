package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.excepciones.EjercitosInsuficientesException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.fichas.EjercitoNulo;
import edu.fiuba.algo3.modelo.fichas.Fichas;
import edu.fiuba.algo3.modelo.roles.Atacante;
import edu.fiuba.algo3.modelo.roles.Defensor;

import java.util.ArrayList;

public class Pais {
    String nombre;
    Fichas ejercito;
    private ArrayList<Pais> paisesLimitrofes;

    public Pais(String nombre) {
        this.nombre = nombre;
        this.paisesLimitrofes = new ArrayList<>();
        this.ejercito = new EjercitoNulo();

    }

    public ArrayList<Pais> getPaisesLimitrofes() {
        return paisesLimitrofes;
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

    public Resultado atacarA(Pais defensor) throws ElPaisNoEsLimitrofeException, EjercitosInsuficientesException {

        if (!paisesLimitrofes.contains(defensor)) throw new ElPaisNoEsLimitrofeException();

        this.ejercito.asignarRol(new Atacante());
        return defensor.recibirAtaque(this);
    }

    public Resultado recibirAtaque(Pais atacante) {
        this.ejercito.asignarRol(new Defensor());
        return new ResultadoBatalla(atacante, this);
    }

    public Dados tirarDados() {
        return this.ejercito.tirarDados(this);
    }

    public int pedirCantidadAlJugador() {
        return this.ejercito.pedirCantidadAlJugador();
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