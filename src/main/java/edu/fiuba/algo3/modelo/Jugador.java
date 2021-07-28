package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
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
    private ArrayList<Objetivo> objetivos = new ArrayList<>();
    private Turno turno = new SinTurno();
    private ArrayList<Pais> paisesDominados = new ArrayList<>();
    private HashMap<String, Tarjeta> tarjetas = new HashMap<>();
    private Usuario usuario;
    private String nombre;

    public Jugador(int id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
        this.objetivos.add(new General());
    }

    public Jugador() { // despues lo volamo
        this.id = 0;
        this.objetivos.add(new General());
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (paisesDominados.contains(pais)) {
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
        if (!paisesDominados.contains(pais)) this.paisesDominados.add(pais);
    }

    public void quitarPais(Pais defensor) {
        this.paisesDominados.remove(defensor);
    }

    public void recibirTarjeta(Tarjeta tarjeta){
        tarjetas.put(tarjeta.nombrePais(), tarjeta);
    }

    public void activarTarjetaPais(Pais unPais) throws TarjetaNoEncontradaException, JugadorNoPoseePaisDeLaTarjetaException, ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException {
        if(!this.paisesDominados.contains(unPais)) throw new JugadorNoPoseePaisDeLaTarjetaException();

        this.turno.activarTarjeta(this.buscarTarjeta(unPais));
        tarjetas.remove(unPais.obtenerNombre()); // this.mandarAlFondoDelMazo(nombrePais); deeaa
    }

    private Tarjeta buscarTarjeta(Pais unPais) throws TarjetaNoEncontradaException {
       try {
            return this.tarjetas.get(unPais.obtenerNombre());
        } catch (NullPointerException e) {
            throw new TarjetaNoEncontradaException();
        }
    }

    public void asignarObjetivo(Objetivo unObjetivo) {
        this.objetivos.add(unObjetivo);
    }

    public boolean cumpleObjetivo() {
        return this.objetivos.stream().anyMatch(objetivo -> objetivo.estaCumplido(this));
    }

    public String obtenerNombre() {
        return nombre;
    }
}
