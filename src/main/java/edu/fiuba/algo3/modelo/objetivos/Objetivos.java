package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Jugadores;

import java.util.ArrayList;
import java.util.Random;

public class Objetivos {
    private static final ArrayList<Objetivo> objetivos = new ArrayList<>();

    public static void asignarObjetivos(Jugadores jugadores){
        crearObjetivos(jugadores);
        repartirObjetivos(jugadores);
    }

    private static void crearObjetivos(Jugadores jugadores){

        objetivos.add(new Ocupacion1());
        objetivos.add(new Ocupacion2());
        objetivos.add(new Ocupacion3());
        objetivos.add(new Ocupacion4());
        objetivos.add(new Ocupacion5());
        objetivos.add(new Ocupacion6());
        objetivos.add(new Ocupacion7());
        objetivos.add(new Ocupacion8());

        for (Jugador jugador : jugadores) objetivos.add(new Destruccion(jugador));
    }

    private static void repartirObjetivos(Jugadores jugadores){
        Random r = new Random();
        for (Jugador jugador : jugadores) {
            Objetivo objetivo = objetivos.remove(r.nextInt(objetivos.size()));

            // Puede que a un jugador le toque destruirse a si mismo :(
            jugador.asignarObjetivo(objetivo);
        }
    }
}
