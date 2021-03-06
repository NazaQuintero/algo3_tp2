package edu.fiuba.algo3.modelo.paises;

import com.google.gson.annotations.Expose;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.batallas_de_dados.Dados;
import edu.fiuba.algo3.modelo.batallas_de_dados.Resultado;
import edu.fiuba.algo3.modelo.batallas_de_dados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.excepciones.CantidadDeEjercitosInValidaException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.fichas.EjercitoNulo;
import edu.fiuba.algo3.modelo.fichas.Fichas;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.observables.Subject;
import edu.fiuba.algo3.modelo.roles.Atacante;
import edu.fiuba.algo3.modelo.roles.Defensor;

import java.util.ArrayList;

public class Pais implements Subject {
    @Expose
    private final String nombre;
    @Expose
    private int posX = 0;
    @Expose
    private int posY = 0;

    private final ArrayList<Observer> observers = new ArrayList<>();

    private Fichas ejercito;
    private final ArrayList<Pais> paisesLimitrofes;

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

    public void colocarEjercito(Fichas ejercito) {
        this.ejercito = ejercito;
        this.ejercito.agregarPais(this);
        this.notifyObservers();
    }

    public void modificarCantidadEjercito(int aumentarEn) {
        this.ejercito.modificarCantidad(aumentarEn);
        this.notifyObservers();
    }

    public boolean esLimitrofeCon(Pais otroPais) {
        return this.paisesLimitrofes.contains(otroPais);
    }

    public void limitaCon(Pais otroPais) {
        paisesLimitrofes.add(otroPais);
    }

    public Resultado atacarA(Pais defensor, int cantidadEjercitos) throws ElPaisNoEsLimitrofeException, CantidadDeEjercitosInValidaException {

        if (!this.esLimitrofeCon(defensor)) throw new ElPaisNoEsLimitrofeException();
        if (cantidadEjercitos > this.cantidadEjercitos()-1 || cantidadEjercitos <= 0) throw new CantidadDeEjercitosInValidaException();

        this.ejercito.asignarRol(new Atacante(cantidadEjercitos));
        return defensor.recibirAtaque(this);
    }

    public Resultado recibirAtaque(Pais atacante) {
        this.ejercito.asignarRol(new Defensor(this.cantidadEjercitos()));
        return new ResultadoBatalla(atacante, this);
    }

    public Dados tirarDados() {
        return this.ejercito.tirarDados();
    }


    public void reagrupar(Pais destino, int cantidadEjercitos) throws ElPaisNoEsLimitrofeException, CantidadDeEjercitosInValidaException {
        if (!this.esLimitrofeCon(destino)) throw new ElPaisNoEsLimitrofeException();
        if (this.cantidadEjercitos() - cantidadEjercitos <= 0 || cantidadEjercitos <= 0) throw new CantidadDeEjercitosInValidaException();

        this.modificarCantidadEjercito(-cantidadEjercitos);
        destino.modificarCantidadEjercito(cantidadEjercitos);

    }

    public boolean estaLibre() {
        return this.dominadoPor() == null;
    }

    public void setDados(Dados dados) {
        ejercito.setDados(dados);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void addObserver(Observer obs) {
        this.observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        this.observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

    public Fichas getEjercito() {
        return ejercito;
    }
}