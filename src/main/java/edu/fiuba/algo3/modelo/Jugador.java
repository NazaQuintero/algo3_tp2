package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.canjes.Canje;
import edu.fiuba.algo3.modelo.canjes.CanjeNulo;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatallaNulo;
import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.objetivos.General;
import edu.fiuba.algo3.modelo.objetivos.Objetivo;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Jugador {

    private Color color;
    private final ArrayList<Objetivo> objetivos = new ArrayList<>();
    private final String nombre;
    private Turno turno = new SinTurno();
    private final ArrayList<Pais> paisesDominados = new ArrayList<>();
    private final HashMap<Pais, Tarjeta> tarjetas = new HashMap<>();
    private Canje canje;

    public Jugador(String nombre, Color color){
        this.objetivos.add(new General());
        this.canje = new CanjeNulo();
        this.nombre = nombre;
        this.color = color;
    }

    public Jugador(String nombre){
        this.objetivos.add(new General());
        this.canje = new CanjeNulo();
        this.nombre = nombre;
    }

    public Color color() {
        return color;
    }

    public int cantidadPaisesDominados() {
        return paisesDominados.size();
    }

    public void colocarEjercitos(Pais pais, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        if (paisesDominados.contains(pais)) {
            this.turno.colocarEjercitos(pais, cantidadEjercitos);
        }

        else if (pais.estaLibre()){
            pais.colocarEjercito(new Ejercito(this));
            pais.modificarCantidadEjercito(cantidadEjercitos-1);
        }
        else throw new PaisOcupadoPorOtroJugadorException();
    }

    public void asignarColor(Color color) {
        this.color = color;
    }
    public void setTurno(Turno unTurno) {
        this.turno = unTurno;
    }

    public void finalizarRonda() throws ElJugadorNoTieneTurnoException{
        this.turno.finalizarRonda(this);
    }

    public Resultado atacarA(Pais paisAtacante, Pais paisDefensor, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException, EjercitosInsuficientesException, ElPaisNoEsLimitrofeException {
        Resultado resultado = new ResultadoBatallaNulo();
       if (this.puedeAtacar()){
           resultado = turno.atacarA(paisAtacante, paisDefensor, cantidadEjercitos);
       } else {
           turno.finalizarRonda(this);
       }
        return resultado;
    }

    private boolean puedeAtacar() {
        return this.paisesDominados.stream().anyMatch(pais -> pais.cantidadEjercitos() > 1);
    }

    public void reagrupar(Pais origen, Pais destino, int cantidad) throws NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException, ElPaisNoEsLimitrofeException {
        try {
            this.turno.reagrupar(origen, destino, cantidad);
        }
        catch (ElJugadorNoTieneTurnoException e) {
            throw new ElJugadorNoTieneTurnoException();
        }
    }

    public void agregarPais(Pais pais) {
        if (!paisesDominados.contains(pais)) this.paisesDominados.add(pais);
    }

    public void quitarPais(Pais defensor) {
        this.paisesDominados.remove(defensor);
    }

    public void recibirTarjeta(Tarjeta tarjeta){
        tarjetas.put(tarjeta.obtenerPais(), tarjeta);
    }

    public void activarTarjetaPais(Pais unPais) throws TarjetaNoEncontradaException, JugadorNoPoseePaisDeLaTarjetaException, ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException, LaTarjetaYaFueActivadaException {
        if(!this.paisesDominados.contains(unPais)) throw new JugadorNoPoseePaisDeLaTarjetaException();
        this.turno.activarTarjeta(this.buscarTarjeta(unPais));
    }

    public Tarjeta buscarTarjeta(Pais unPais) throws TarjetaNoEncontradaException {
        if (tarjetas.containsKey(unPais)) return tarjetas.get(unPais);
        else throw new TarjetaNoEncontradaException();
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

    public String obtenerNombre() {
        return nombre;
    }

    public boolean poseeLimitrofes(int cantLimitrofes) {
        return paisesDominados.stream().anyMatch(pais -> (int) pais.getPaisesLimitrofes().stream().filter(pais1 -> pais1.dominadoPor() == this).count() >= cantLimitrofes-1);
    }

    public void canjearTarjetas(ArrayList<Tarjeta> tarjetas) throws JugadorSinTarjetasException, SinCanjeHabilitadoException, LaTarjetaYaEstaDesactivadaException {
        if (!comprobarTarjetas(tarjetas)) throw new JugadorSinTarjetasException();
        canje = canje.habilitarCanje(tarjetas);
        devolverTarjetas(tarjetas);
    }

    boolean comprobarTarjetas(ArrayList<Tarjeta> tarjetas) {
        for (Tarjeta t: tarjetas){
            if (!this.tarjetas.containsValue(t)) return false;
        }
        return true;
    }

    public void devolverTarjetas(ArrayList<Tarjeta> tarjetasADevolver) throws LaTarjetaYaEstaDesactivadaException {
        for (Tarjeta tarjeta : tarjetasADevolver) {
            tarjeta.desactivar();
            tarjetas.remove(tarjeta.obtenerPais());
        }
    }

    public Canje obtenerCanjeActual() {
        return canje;
    }

    public int ordenCanje() {
        return canje.cantidadEjercitos();
    }
}
