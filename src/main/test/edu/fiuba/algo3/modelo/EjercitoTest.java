package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallas_de_dados.Dados;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.fichas.EjercitoNulo;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.roles.Atacante;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EjercitoTest {
    @Test
    public void getDadosTest(){

    }

    @Test
    public void tirarDadosTiraTresDadosComoMaximo(){
        Ejercito ej = new Ejercito(new Jugador("Martin", Color.RED));
        Pais arg = new Pais("Argentina");
        arg.colocarEjercito(ej);
        ej.asignarRol(new Atacante(300));
        Dados dados = ej.tirarDados();
        assertEquals(3, dados.obtenerCantidad());
    }

    @Test
    public void tirarDadosTiraLaCantidadPasadaSiEsMenorATres(){
        Ejercito ej = new Ejercito(new Jugador("Martin", Color.RED));
        Pais arg = new Pais("Argentina");
        arg.colocarEjercito(ej);
        ej.asignarRol(new Atacante(2));
        Dados dados = ej.tirarDados();
        assertEquals(2, dados.obtenerCantidad());
    }

    @Test
    public void sePuedenVolverAVerLosDados(){
        Ejercito ej = new Ejercito(new Jugador("Martin", Color.RED));
        Pais arg = new Pais("Argentina");
        arg.colocarEjercito(ej);
        ej.asignarRol(new Atacante(2));
        Dados dados = ej.tirarDados();
        assertEquals(dados, ej.getDados());
    }

    @Test
    public void andanLosObserver(){
        Ejercito ej = new Ejercito(new Jugador("Martin", Color.RED));

        class ObsPersonalizado implements Observer {
            private int cantidad = 1;

            @Override
            public void update() {
                cantidad += 1;
            }
        }

        ObsPersonalizado obs = new ObsPersonalizado();

        ej.addObserver(obs);
        assertEquals(1, obs.cantidad);
        ej.notifyObservers();
        assertEquals(2, obs.cantidad);
        ej.removeObserver(obs);
        ej.notifyObservers();
        assertEquals(2, obs.cantidad);

    }

    @Test
    public void ejercitoNuloTiraDadosNull(){
        EjercitoNulo ej = new EjercitoNulo();
        assertNull(ej.tirarDados());
    }
    @Test
    public void ejercitoNuloObtieneDadosNull(){
        EjercitoNulo ej = new EjercitoNulo();
        assertNull(ej.getDados());
    }
}
