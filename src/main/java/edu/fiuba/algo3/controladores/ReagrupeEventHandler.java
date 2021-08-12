package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.excepciones.CantidadDeEjercitosInValidaException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.vista.CampoDeJuego;
import edu.fiuba.algo3.vista.MenuLateralDerecho;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ReagrupeEventHandler implements EventHandler<Event> {
    private final CampoDeJuego campoDeJuego;
    private final Pais desde;
    private final Pais hasta;
    private final TextField inputText;

    public ReagrupeEventHandler(CampoDeJuego campoDeJuego, Pais pais, TextField textField) {
        this.campoDeJuego = campoDeJuego;
        this.desde = campoDeJuego.getPaisSeleccionado();
        this.hasta = pais;
        this.inputText = textField;
    }

    @Override
    public void handle(Event event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED || ((KeyEvent) event).getCode() == KeyCode.ENTER) {
            MenuLateralDerecho menuLateralDerecho = (MenuLateralDerecho) campoDeJuego.getRight();
            int cantidadDeEjercitos = Integer.parseInt(inputText.getText());
            try {
                this.desde.reagrupar(hasta, cantidadDeEjercitos);
                ReproductorDeSonido.playClick();
                campoDeJuego.setPaisSeleccionado(null);
                campoDeJuego.mostrarPaisesDelJugadorActual();
                menuLateralDerecho.update();

            } catch (ElPaisNoEsLimitrofeException | CantidadDeEjercitosInValidaException e) {
                ReproductorDeSonido.playError();
                menuLateralDerecho.mostrarErrorAtaqueYReagrupe(desde.cantidadEjercitos()-1);
            }
        }
    }
}
