package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallas_de_dados.Resultado;
import edu.fiuba.algo3.modelo.canjes.Canje;
import edu.fiuba.algo3.modelo.canjes.CanjeNulo;
import edu.fiuba.algo3.modelo.batallas_de_dados.ResultadoBatallaNulo;
import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.objetivos.General;
import edu.fiuba.algo3.modelo.objetivos.Objetivo;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.MultitonTarjetas;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Jugador {

    private final Color color;
    private final ArrayList<Objetivo> objetivos = new ArrayList<>();
    private final String nombre;
    private Turno turno = new SinTurno();
    private final ArrayList<Pais> paisesDominados = new ArrayList<>();
    private final ArrayList<Tarjeta> tarjetas = new ArrayList<>();
    private Canje canje;
    private boolean conquistoPais = false;

    public Jugador(String nombre, Color color){
        this.nombre = nombre;
        this.color = color;
        this.objetivos.add(new General());
        this.canje = new CanjeNulo();
    }

    public String getNombre() {
        return nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setTurno(Turno unTurno) {
        this.turno = unTurno;
    }

    public int cantidadPaisesDominados() {
        return paisesDominados.size();
    }

    public void agregarPais(Pais pais) {
        if (!paisesDominados.contains(pais)) this.paisesDominados.add(pais);
    }

    public void quitarPais(Pais pais) {
        this.paisesDominados.remove(pais);
    }

    public Resultado atacarA(Pais paisAtacante, Pais paisDefensor, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException, CantidadDeEjercitosInValidaException, ElPaisNoEsLimitrofeException {
        Resultado resultado = new ResultadoBatallaNulo();

        if (this.puedeAtacar()) {
            resultado = turno.atacarA(paisAtacante, paisDefensor, cantidadEjercitos);
        } else {
            turno.finalizarRonda(this);
        }

        return resultado;
    }

    private boolean puedeAtacar() {
        return this.paisesDominados.stream().anyMatch(pais -> pais.cantidadEjercitos() > 1);
    }

    public void reagrupar(Pais origen, Pais destino, int cantidad) throws NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException, ElPaisNoEsLimitrofeException, CantidadDeEjercitosInValidaException {
        this.turno.reagrupar(origen, destino, cantidad);
    }

    public void recibirTarjeta(Tarjeta tarjeta){
        tarjetas.add(tarjeta);
    }

    public void activarTarjeta(Tarjeta tarjeta) throws JugadorNoPoseePaisDeLaTarjetaException, ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException, LaTarjetaYaFueActivadaException, TarjetaNoEncontradaException {

        if (!this.tarjetas.contains(tarjeta)) throw new TarjetaNoEncontradaException();
        if (!this.paisesDominados.contains(tarjeta.getPais())) throw new JugadorNoPoseePaisDeLaTarjetaException();

        this.turno.activarTarjeta(tarjeta);

    }

    public void colocarEjercitos(Pais pais, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException, NoQuedanMasEjercitosPorColocarException {

        if (paisesDominados.contains(pais)) {
            this.turno.colocarEjercitos(pais, cantidadEjercitos);
        }
        else if (pais.estaLibre()) {
            pais.colocarEjercito(new Ejercito(this));
            pais.modificarCantidadEjercito(cantidadEjercitos-1);
        }
        else throw new PaisOcupadoPorOtroJugadorException();

    }

    public void finalizarRonda() throws ElJugadorNoTieneTurnoException{
        this.turno.finalizarRonda(this);
    }

    public void asignarObjetivo(Objetivo unObjetivo) {
        this.objetivos.add(unObjetivo);
    }

    public boolean cumpleObjetivo() {
        return this.objetivos.stream().anyMatch(objetivo -> {
            try {
                return objetivo.estaCumplido(this);
            } catch (ContinenteInvalidoException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    public boolean poseeLimitrofes(int cantLimitrofes) {
        return paisesDominados.stream().anyMatch(pais -> (int) pais.getPaisesLimitrofes().stream().
                filter(pais1 -> pais1.dominadoPor() == this).count() >= cantLimitrofes-1);
    }

    public void canjearTarjetas(ArrayList<Tarjeta> tarjetas) throws JugadorNoTieneTodasLasTarjetasException, SinCanjeHabilitadoException {
        if (!this.tarjetas.containsAll(tarjetas)) throw new JugadorNoTieneTodasLasTarjetasException();
        canje = canje.habilitarCanje(tarjetas);
        devolverTarjetas(tarjetas);
    }

    public void devolverTarjetas(ArrayList<Tarjeta> tarjetasADevolver) {

        tarjetasADevolver.forEach(tarjeta -> {
            tarjeta.desactivar();
            MultitonTarjetas.agregarTarjeta(tarjeta);
            tarjetas.remove(tarjeta);
        });
    }

    public Canje getCanjeActual() {
        return canje;
    }

    public int ordenCanje() {
        return canje.cantidadEjercitos();
    }

    public ArrayList<Tarjeta> getTarjetas() {
        return this.tarjetas;
    }

    public void setConquista(boolean b) {
        conquistoPais = b;
    }

    public boolean huboConquista() {
        return conquistoPais;
    }

    public ArrayList<Objetivo> obtenerObjetivos() {
        return this.objetivos;
    }

}
