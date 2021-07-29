package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.objetivos.*;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;

import java.util.ArrayList;
import java.util.Random;


public class Juego {
    static final String ARCHIVO_PAISES = "paises.json";
    static final String ARCHIVO_TARJETAS = "tarjetas.json";

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Tarjeta> tarjetas;
    private ArrayList<Objetivo> objetivos;

    public Juego() {
        tablero = new Tablero();
        jugadores = new ArrayList<>();
        tarjetas = new ArrayList<>();
        objetivos = new ArrayList<>();

        try {
            CargarJuego.cargarPaisesAlTablero(tablero, ARCHIVO_PAISES);
            CargarJuego.cargarTarjetas(tarjetas, ARCHIVO_TARJETAS, tablero);
            CargarJuego.cargarObjetivos(objetivos, jugadores);
        }

        //Error al cargar los archivos
        catch (Exception e) { e.printStackTrace(); }
    }

    public void agregarJugador(String nombre){
        Jugador jugador = new Jugador(jugadores.size(), nombre);
        jugadores.add(jugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException, ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        if (jugadores.size() < 2) throw new CantidadDeJugadoresInsuficienteException();
        else tablero.repartirPaises(jugadores);

        asignarObjetivos();
    }

    // Crea los objetivos de Ocupacion y Destruccion y le asigna uno a cada Jugador
    private void asignarObjetivos() {
        CargarJuego.cargarObjetivos(objetivos, jugadores);
        for (Jugador jugador: jugadores) jugador.asignarObjetivo(nuevoObjetivo());
    }

    private Objetivo nuevoObjetivo() {
        Random r = new Random();
        return objetivos.remove(r.nextInt(objetivos.size())-1);
    }

    public void ataque(String paisAtacante, String paisDefensor, int cantidadEjercitos) throws ElPaisNoEsLimitrofeException, EjercitosInsuficientesException {
        tablero.ataque(paisAtacante, paisDefensor, cantidadEjercitos);
    }

    public void colocarEjercitos(String nombreJugador, String nombrePais, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException{
        tablero.colocarEjercitos(obtenerJugador(nombreJugador), nombrePais, cantidadEjercitos);
    }

    public int cantidadPaisesDominados(String nombre){
        return  obtenerJugador(nombre).cantidadPaisesDominados();
    }
    private Jugador obtenerJugador(String nombre){
        return jugadores.stream().filter(jugador -> nombre.equals(jugador.obtenerNombre())).findFirst().orElse(new Jugador(-1,""));
    }

    public int cantidadEjercitosEn(String nombrePais) {
        return tablero.cantidadEjercitosEn(nombrePais);
    }

    public String paisDominadoPor(String nombrePais) {
        return tablero.paisDominadoPor(nombrePais).obtenerNombre();
    }
}
