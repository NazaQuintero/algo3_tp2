package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.objetivos.General;
import edu.fiuba.algo3.modelo.objetivos.Objetivo;
import edu.fiuba.algo3.modelo.roles.Atacante;
import edu.fiuba.algo3.modelo.roles.Defensor;
import edu.fiuba.algo3.modelo.roles.Rol;
import edu.fiuba.algo3.modelo.roles.RolIndefinido;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;

import java.util.HashMap;

public class Jugador {

    private final int id;
    private String color = "";
    private Objetivo secreto;
    private Objetivo general = new General();
    private Rol rol = new RolIndefinido();
    private Turno turno = new SinTurno();
    private HashMap<String, Pais> paisesDominados = new HashMap<>();
    private HashMap<String, Tarjeta> tarjetas = new HashMap<>();
    private Usuario usuario;

    public Jugador(int id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
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

    public Dados tirarDados(Pais pais) { return this.rol.tirarDados(pais); }

    public int pedirCantidad() {
        return usuario.pedirCantidad();
    }

    public void rolAtacante() {
        rol = new Atacante();
    }

    public void rolDefensor() {
        rol = new Defensor();
    }

    public void setTurno(Turno unTurno) {
        this.turno = unTurno;
    }

    public void finalizarRonda() {
        this.turno.finalizarRonda();
    }

    public void atacarA(Pais paisAtacante, Pais paisDefensor) throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException {
        try {
            turno.atacarA(paisAtacante, paisDefensor);
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

    public void activarTarjetaPais(String nombrePais) throws TarjetaNoEncontradaException, JugadorNoPoseePaisDeLaTarjetaException, ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException {
        if(!this.poseePais(nombrePais)) throw new JugadorNoPoseePaisDeLaTarjetaException();

        this.turno.activarTarjeta(this.buscarTarjeta(nombrePais));
        tarjetas.remove(nombrePais); // this.mandarAlFondoDelMazo(nombrePais); deeaa
    }

    private Tarjeta buscarTarjeta(String nombrePais) throws TarjetaNoEncontradaException {
       try {
            return this.tarjetas.get(nombrePais);
        } catch (NullPointerException e) {
            throw new TarjetaNoEncontradaException();
        }
    }

    private boolean poseePais(String nombrePais) {
        return paisesDominados.containsKey(nombrePais);
    }

    /*public void finalizarReagrupe() {
        this.turno.finalizarReagrupe();
//        this.turno.cambiarRonda();
    }

    public void finalizarColocacion() {
        this.turno.finalizarColocacion();
    }*/
}
