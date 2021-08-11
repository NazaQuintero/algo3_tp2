package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ActivacionTarjetaEnRondaEquivocadaException;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.JugadorNoPoseePaisDeLaTarjetaException;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.vista.VentanaTarjetas;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ActivarTarjetaEventHandler implements EventHandler<MouseEvent> {

    private final Jugador jugador;
    private final VentanaTarjetas ventanaTarjetas;

    public ActivarTarjetaEventHandler(Jugador jugador, VentanaTarjetas ventanaTarjetas) {
        this.jugador = jugador;
        this.ventanaTarjetas = ventanaTarjetas;
    }

    @Override
    public void handle(MouseEvent event) {
        ArrayList<Tarjeta> tarjetas = ventanaTarjetas.getTarjetasSeleccionadas();
        activarTarjetas(tarjetas);
    }

    void activarTarjetas(ArrayList<Tarjeta> tarjetas){
        if (tarjetas.size() == 0) ventanaTarjetas.mostrarError("Se debe seleccionar al menos una tarjeta");
        for (Tarjeta tarjeta: tarjetas) {
            try {
                jugador.activarTarjeta(tarjeta);
                ventanaTarjetas.deseleccionarVistaTarjeta(tarjeta);
            }
            catch (LaTarjetaYaFueActivadaException e) {
                ventanaTarjetas.mostrarError("La tarjeta de " + tarjeta.obtenerPais().getNombre() +" ya fue activada");
            }
            catch (ElJugadorNoTieneTurnoException ignored) {

            } catch (ActivacionTarjetaEnRondaEquivocadaException e) {
                ventanaTarjetas.mostrarError("No se pueden activar tarjetas en ronda de ataque");
            } catch (JugadorNoPoseePaisDeLaTarjetaException e) {
                ventanaTarjetas.mostrarError("El jugador no posee el pais " + tarjeta.obtenerPais().getNombre());
            }
        }
    }
}