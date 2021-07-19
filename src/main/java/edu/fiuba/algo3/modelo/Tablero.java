package edu.fiuba.algo3.modelo;
import java.util.*;


public class Tablero {
    static final int CANTIDAD_PAISES = 6; // tiene que ser 50 pero es 6 para testear
    private Map<String,Pais> paises;
    private ArrayList<String> nombresPaises;

    public Tablero() {
        nombresPaises = new ArrayList<>();
        paises = new HashMap<>();
    }

    public void agregarPais(String nombrePais, Pais unPais) {
        paises.put(nombrePais, unPais);
        nombresPaises.add(nombrePais);
    }

    public void repartirPaises(ArrayList<Jugador> jugadores) throws PaisOcupadoPorOtroJugadorException {
        Random r = new Random();
        int numeroJugador = 0; // seria el jugador 1 xd
        String nombrePaisActual;
        while ((nombresPaises.size()-1) > 0) {
            nombrePaisActual = nombresPaises.get(r.nextInt((nombresPaises.size()-1)+1));
            jugadores.get(numeroJugador).colocarEjercitos(paises.get(nombrePaisActual));
            nombresPaises.remove(nombrePaisActual);
            numeroJugador++;
            if (numeroJugador >= jugadores.size()) numeroJugador = 0;
        }
        jugadores.get(numeroJugador).colocarEjercitos(paises.get(nombresPaises.get(0)));
    }

    public Pais obtenerPais(String nombrePais) {
        return paises.get(nombrePais);
    }

}