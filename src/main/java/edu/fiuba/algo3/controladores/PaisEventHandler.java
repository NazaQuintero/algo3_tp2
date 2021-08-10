package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.excepciones.NoQuedanMasEjercitosPorColocarException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.rondas.Ataque;
import edu.fiuba.algo3.modelo.rondas.Ronda;
import edu.fiuba.algo3.modelo.rondas.RondaColocacion;
import edu.fiuba.algo3.vista.CampoDeJuego;
import edu.fiuba.algo3.vista.MenuLateralDerecho;
import edu.fiuba.algo3.vista.VistaPais;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PaisEventHandler implements EventHandler<MouseEvent> {

    private final CampoDeJuego campoDeJuego;
    private final VistaPais vistaPais;
    private final Pais pais;

    public PaisEventHandler(VistaPais vistaPais, CampoDeJuego campoDeJuego) {
        this.pais = vistaPais.getPais();
        this.vistaPais = vistaPais;
        this.campoDeJuego = campoDeJuego;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        Ronda ronda = this.campoDeJuego.getTurno().obtenerRondaActual();
        MenuLateralDerecho menuLateral = (MenuLateralDerecho) campoDeJuego.getRight();
        VBox formularioDeAccion = (VBox) menuLateral.getChildren().get(1);
        TextField textField = (TextField) formularioDeAccion.getChildren().get(1);
        menuLateral.update();

        if (ronda instanceof RondaColocacion) {
            System.out.println("Estamos en Ronda de Colocacion rey");

            campoDeJuego.mostrarPaisesDelJugadorActual();
            try {
                ronda.colocarEjercitos(pais,1);
                menuLateral.update();
            } catch (NoEsRondaDeColocacionException | NoQuedanMasEjercitosPorColocarException e) {

                menuLateral.mostrarErrorColocacion();
                Label labelDeError = (Label)(formularioDeAccion.getChildren().get(4));
                labelDeError.setText("No quedan más ejércitos por colocar");
                labelDeError.setVisible(true);
            }

        } else if (ronda instanceof Ataque) {
            System.out.println("Estamos en Ronda de Ataque papu");

            if (campoDeJuego.getPaisSeleccionado() == null) {
                campoDeJuego.mostrarPaises();
                vistaPais.resaltarLimitrofesAdversarios();
                vistaPais.marcarComoSeleccionada();
                campoDeJuego.setPaisSeleccionado(pais);
                menuLateral.setTextoLabelAyuda("Seleccione el Pais al que desea atacar.");
            } else {
                menuLateral.setTextoLabelAyuda("Indique la cantidad de Ejercitos con los que desea atacar");
                menuLateral.setInputTextVisible(true);
                menuLateral.setBotonAccionVisible(true);
                menuLateral.setBotonCancelarVisible(true);
                menuLateral.setAccion(new AtaqueEventHandler(campoDeJuego, pais, textField));
                textField.requestFocus();
            }

        } else {
            System.out.println("No queda otra que reagrupar mi ciela");
            campoDeJuego.mostrarPaisesDelJugadorActual();
            if (campoDeJuego.getPaisSeleccionado() == null) {
                vistaPais.marcarComoSeleccionada();
                campoDeJuego.setPaisSeleccionado(pais);
                vistaPais.resaltarLimitrofesPropios();
                menuLateral.setTextoLabelAyuda("Seleccione el Pais al que desea enviar sus tropas.");
            } else {
                menuLateral.setTextoLabelAyuda("Indique la cantidad de Ejercitos que desea reagrupar");
                menuLateral.setInputTextVisible(true);
                menuLateral.setBotonAccionVisible(true);
                menuLateral.setBotonCancelarVisible(true);
                ReagrupeEventHandler reagrupeEventHandler = new ReagrupeEventHandler(campoDeJuego, pais, textField);
                menuLateral.setAccion(reagrupeEventHandler);
                textField.requestFocus();
            }

        }

    }

}
