package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Jugadores;

import java.util.ArrayList;
import java.util.Random;

public class Objetivos {
    public static void asignarObjetivos(Jugadores jugadores){
        ArrayList<Objetivo> objetivos = new ArrayList<>();
        crearObjetivos(objetivos, jugadores);
        repartirObjetivos(objetivos, jugadores);
    }

    private static void crearObjetivos(ArrayList<Objetivo> objetivos, Jugadores jugadores){

        // Crea los objetivos de ocupacion
        objetivos.add(new Ocupacion1());
        objetivos.add(new Ocupacion2());
        objetivos.add(new Ocupacion3());
        objetivos.add(new Ocupacion4());
        objetivos.add(new Ocupacion5());
        objetivos.add(new Ocupacion6());
        objetivos.add(new Ocupacion7());
        objetivos.add(new Ocupacion8());

        // Crea los objetivos de Destruccion
        for (Jugador jugador : jugadores) {objetivos.add(new Destruccion(jugador));}
    }

    private static void repartirObjetivos(ArrayList<Objetivo> objetivos, Jugadores jugadores){
        Random r = new Random();
        for (Jugador j : jugadores) {
            Objetivo objetivo = objetivos.remove(r.nextInt(objetivos.size()));

            // Puede que a un jugador le toque destruirse a si mismo :(
            j.asignarObjetivo(objetivo);
        }
    }

}
