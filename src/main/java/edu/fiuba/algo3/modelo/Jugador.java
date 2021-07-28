package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.canjes.Canje;
import edu.fiuba.algo3.modelo.canjes.CanjeNulo;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.objetivos.General;
import edu.fiuba.algo3.modelo.objetivos.Objetivo;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;

import java.util.ArrayList;
import java.util.HashMap;

public class Jugador {

    private final int id;
    private String color = "";
    private Objetivo secreto;
    private Objetivo general = new General();
    private Turno turno = new SinTurno();
    private HashMap<String, Pais> paisesDominados = new HashMap<>();
    private HashMap<String, Tarjeta> tarjetas = new HashMap<>();
    private Usuario usuario;
    private Canje canje;

    public Jugador(int id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
        this.canje = new CanjeNulo();
    }

    public Jugador() { // despues lo volamo
        this.id = 0;
    }

    public String mostrarColor() {
        return color;
    }

    public int cantidadPaisesDominados() {
        return paisesDominados.size();
    }

    public int obtenerId() {
        return this.id;
    }

    public void colocarEjercitos(Pais pais) throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        if (paisesDominados.containsKey(pais.obtenerNombre())) { //qsy
            try {
                this.turno.colocarEjercitos(pais);
            } catch (ElJugadorNoTieneTurnoException e) {
                throw new ElJugadorNoTieneTurnoException();
            }
        } else if (pais.estaLibre()) pais.colocarEjercito(new Ejercito(this));
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

    public Dados tirarDados(Pais pais) { return pais.tirarDados(); }

    public int pedirCantidad() {
        return usuario.pedirCantidad();
    }

    public void setTurno(Turno unTurno) {
        this.turno = unTurno;
    }

    public void finalizarRonda() {
        this.turno.finalizarRonda();
    }

    public ResultadoBatalla atacarA(Pais paisAtacante, Pais paisDefensor) throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException {
        try {
            return turno.atacarA(paisAtacante, paisDefensor);
        } catch (ElJugadorNoTieneTurnoException e) {
            throw new ElJugadorNoTieneTurnoException();
        }
    }

    public void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException, ElPaisNoEsLimitrofeException {
        try {
            this.turno.reagrupar(origen, destino);
        } catch (ElJugadorNoTieneTurnoException e) {
            throw new ElJugadorNoTieneTurnoException();
        }
    }

    public void agregarPais(Pais pais) {
        this.paisesDominados.putIfAbsent(pais.obtenerNombre(), pais);
    }

    public void quitarPais(Pais defensor) {
        this.paisesDominados.remove(defensor.obtenerNombre());
    }

    public void recibirTarjeta(Tarjeta tarjeta){
        tarjetas.put(tarjeta.nombrePais(), tarjeta);
    }

    public void activarTarjetaPais(Pais unPais) throws TarjetaNoEncontradaException, JugadorNoPoseePaisDeLaTarjetaException, ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException, LaTarjetaYaFueActivadaException {
        if(!this.poseePais(unPais.obtenerNombre())) throw new JugadorNoPoseePaisDeLaTarjetaException();
        this.turno.activarTarjeta(this.buscarTarjeta(unPais.obtenerNombre())); // x ahora, despues lo mejoramos
    }

    public Tarjeta buscarTarjeta(String nombrePais) throws TarjetaNoEncontradaException {
       /*try {
            return this.tarjetas.get(nombrePais);// esto devolverianull
        } catch (NullPointerException e) { //??? com el get no devolvia un null de esos o algo? pregunte por disc si estaba testeado esto tipo si te acordas
            throw new TarjetaNoEncontradaException();
        }*/
        if (tarjetas.containsKey(nombrePais)) return tarjetas.get(nombrePais);
        else throw new TarjetaNoEncontradaException();
    }

    private boolean poseePais(String nombrePais) {
        return paisesDominados.containsKey(nombrePais);
    }

    public ArrayList<Tarjeta> pedirTarjetasACanjear() {
        return usuario.pedirTarjetasACanjear();
    }

    public void solicitarCanje() throws JugadorSinTarjetasException, SinCanjeHabilitadoException, LaTarjetaYaEstaDesactivadaException {
        if (tarjetas.size() == 0) throw new JugadorSinTarjetasException();
        else {
            ArrayList<Tarjeta> tarjetasACanjear = this.pedirTarjetasACanjear();
            canje = canje.habilitarCanje(tarjetasACanjear);
            devolverTarjetas(tarjetasACanjear);
        }
    }

    public void devolverTarjetas(ArrayList<Tarjeta> tarjetasADevolver) throws LaTarjetaYaEstaDesactivadaException {
        for (Tarjeta tarjeta : tarjetasADevolver) {
            tarjeta.desactivar();
            tarjetas.remove(tarjeta.nombrePais());
        }
    }

}
