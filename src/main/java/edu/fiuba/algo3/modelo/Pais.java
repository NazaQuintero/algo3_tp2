package edu.fiuba.algo3.modelo;

import com.google.gson.annotations.Expose;
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
    @Expose
    private String nombre;
    @Expose
    private int posX;
    @Expose
    private int posY;

    private Fichas ejercito;
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

    public void colocarEjercito(Fichas ejercito) {
        this.ejercito = ejercito;
        this.ejercito.agregarPais(this);
    }

    public void modificarCantidadEjercito(int aumentarEn) {
        this.ejercito.modificarCantidad(aumentarEn);
    }

    public boolean esLimitrofeCon(Pais otroPais) {
        return this.paisesLimitrofes.contains(otroPais);
    }

    public void limitaCon(Pais otroPais) {
        otroPais.agregarLimitrofe(this);
        paisesLimitrofes.add(otroPais);
    }

    private void agregarLimitrofe(Pais unPais) {
        paisesLimitrofes.add(unPais);
    }

    public Resultado atacarA(Pais defensor, int cantidadDeEjercitos) throws ElPaisNoEsLimitrofeException, EjercitosInsuficientesException {

        if (!paisesLimitrofes.contains(defensor)) throw new ElPaisNoEsLimitrofeException();

        this.ejercito.asignarRol(new Atacante(cantidadDeEjercitos));
        return defensor.recibirAtaque(this);
    }

    public Resultado recibirAtaque(Pais atacante) {
        this.ejercito.asignarRol(new Defensor(this.cantidadEjercitos()));
        return new ResultadoBatalla(atacante, this);
    }

    public Dados tirarDados() {
        return this.ejercito.tirarDados(this);
    }


    public void reagrupar(Pais destino, int cantidad) throws ElPaisNoEsLimitrofeException {
        if (this.esLimitrofeCon(destino)) {
            this.modificarCantidadEjercito(-cantidad);
            destino.modificarCantidadEjercito(cantidad);
        } else throw new ElPaisNoEsLimitrofeException();
    }

    public String obtenerNombre() {
        return this.nombre;
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

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getNombre() {
        return this.nombre;
    }
}