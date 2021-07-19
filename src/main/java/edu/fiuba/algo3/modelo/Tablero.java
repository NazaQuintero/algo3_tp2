package edu.fiuba.algo3.modelo;
import java.util.*;
import java.util.stream.Collectors;


public class Tablero {
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
    public void agregarPais(Pais pais){
        paises.put(pais.nombre, pais);
    }

    public void repartirPaises(ArrayList<Jugador> jugadores) throws PaisOcupadoPorOtroJugadorException {
        ArrayList<Pais> paises = new ArrayList<>(this.paises.values());
        Collections.shuffle(paises);

        int cant_jugadores = jugadores.size();
        for (int i = 0; i < paises.size(); i++) {
            jugadores.get(i % cant_jugadores).colocarEjercitos(1, paises.get(i));
        }
    }

    public Pais obtenerPais(String nombrePais) {
        return paises.get(nombrePais);
    }

}