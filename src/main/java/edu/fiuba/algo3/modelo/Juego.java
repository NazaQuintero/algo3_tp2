package edu.fiuba.algo3.modelo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Juego {
    static final String NOMBRE_ARCHIVO = "paises.txt";//"C:/Users/franm/OneDrive/Documentos/Algo III/algo3_tp2/src/main/java/edu/fiuba/algo3/modelo/paises.txt";
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;

    public Juego() {
        jugadores = new ArrayList<>();
        tablero = new Tablero();
    }

    public void agregarJugador(Jugador unJugador) {
        jugadores.add(unJugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException, PaisOcupadoPorOtroJugadorException {
        inicializarPaisesDesdeArchivo(NOMBRE_ARCHIVO);
        if (jugadores.size() < 2) throw new CantidadDeJugadoresInsuficienteException();
        else tablero.repartirPaises(jugadores);

    }

    public void repartirPaises() throws PaisOcupadoPorOtroJugadorException {

        tablero.repartirPaises(jugadores);

    }

    private void inicializarPaisesDesdeArchivo(String nombreArchivo) {
        // nombrePais:limitrofe1,limitrofe2,...,limitrofeN

        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader(nombreArchivo));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {
                String[] campos = linea.split(":");
                Pais nuevoPais;
                if (tablero.obtenerPais(campos[0]) == null) {
                    nuevoPais = new Pais(campos[0]);
                    tablero.agregarPais(campos[0], nuevoPais);
                } else nuevoPais = tablero.obtenerPais(campos[0]);

                String[] paisesLimitrofes = campos[1].split(",");

                for (int i = 0; i < paisesLimitrofes.length; i++) {
                    if (tablero.obtenerPais(paisesLimitrofes[i]) == null) {
                        Pais nuevoLimitrofe = new Pais(paisesLimitrofes[i]);
                        tablero.agregarPais(paisesLimitrofes[i], nuevoLimitrofe);
                        nuevoPais.limitaCon(nuevoLimitrofe);
                    } else nuevoPais.limitaCon(tablero.obtenerPais(paisesLimitrofes[i]));
                }
                linea = bufferLectura.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
