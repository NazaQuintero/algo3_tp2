package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.Turno;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeAtaqueException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeReagrupeException;

public class Colocacion implements Ronda {

    private int cantidadEjercitosColocables;
    private String[] nombresContinentes = {"Africa", "America del Norte", "America del Sur", "Asia", "Europa", "Oceania"};


    public Colocacion(Jugador unJugador) {
        calcularEjercitosColocables(unJugador);
    }

    public void calcularEjercitosColocables(Jugador unJugador) {
        this.cantidadEjercitosColocables = 3;
        if (unJugador.cantidadPaisesDominados() > 6) this.cantidadEjercitosColocables = unJugador.cantidadPaisesDominados() / 2;
        this.cantidadEjercitosColocables += unJugador.ordenCanje();
        /*for (int i = 0; i < 6; i++) {
            if (MultitonContinentes.obtenerInstanciaDe(nombresContinentes[i]).dominadoPor(unJugador)) {
                this.cantidadEjercitosColocables += MultitonContinentes.obtenerInstanciaDe(nombresContinentes[i]).obtenerCantidadEjercito();
            }
        }*/
    }


    @Override
    public String obtenerDescripcion() {
        return "Colocacion";
    }

    @Override
    public void finalizarRonda(Turno unTurno) {
        unTurno.finalizarTurnoActual();
        calcularEjercitosColocables(unTurno.obtenerJugadorTurnoActual());
        if(unTurno.leTocaALPrimerJugador()) unTurno.setRonda(new Ataque());
    }

    public Resultado atacarA(Pais atacante, Pais defensor, int cantidadEjercitos) throws NoEsRondaDeAtaqueException {
        throw new NoEsRondaDeAtaqueException();
    }

    public void reagrupar(Pais origen, Pais destino, int cantidad) throws NoEsRondaDeReagrupeException {
        throw new NoEsRondaDeReagrupeException();
    }

    public void colocarEjercitos(Pais unPais, int cantidadEjercitos) {
        if (this.cantidadEjercitosColocables > 0) {
            if (cantidadEjercitos <= this.cantidadEjercitosColocables) {
                unPais.modificarCantidadEjercito(cantidadEjercitos);
                this.cantidadEjercitosColocables -= cantidadEjercitos;
            } // else chequear que ingrese una cantidad valida o que elija no colocar mas
        }
    }

    /*
    public void colocarEjercitos(Jugador jugador) {

        int cantidadEjercitosAColocar = jugador.cantidadPaisesDominados()/2;

    }
    */

    public void activarTarjeta(Tarjeta unaTarjeta) throws LaTarjetaYaFueActivadaException {
        unaTarjeta.activar();
    }
}
