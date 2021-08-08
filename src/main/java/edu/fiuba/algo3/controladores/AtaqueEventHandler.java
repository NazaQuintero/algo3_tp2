package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.batallasDeDados.ProcesadorResultado;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.excepciones.EjercitosInsuficientesException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.vista.CampoDeJuego;
import edu.fiuba.algo3.vista.MenuLateralDerecho;
import edu.fiuba.algo3.vista.VistaDados;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class AtaqueEventHandler implements EventHandler<Event>  {

    private final CampoDeJuego campoDeJuego;
    private final Pais atacante;
    private final Pais defensor;
    private final TextField inputText;

    public AtaqueEventHandler(CampoDeJuego campoDeJuego, Pais defensor, TextField inputText) {
        this.campoDeJuego = campoDeJuego;
        this.atacante = campoDeJuego.getPaisSeleccionado();
        this.defensor = defensor;
        this.inputText = inputText;
    }

    @Override
    public void handle(Event event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED || ((KeyEvent) event).getCode() == KeyCode.ENTER) {
            try {
                int cantidadDeEjercitos = Integer.parseInt(inputText.getText());
                Resultado resultado = atacante.atacarA(defensor, cantidadDeEjercitos);
                mostrarAtaque();
                ProcesadorResultado.obtenerInstancia().procesar(resultado);
                campoDeJuego.setPaisSeleccionado(null);
                System.out.println("Se ejecuto el ataque de " + atacante.getNombre() + " a " + defensor.getNombre());
            } catch (ElPaisNoEsLimitrofeException | NumberFormatException | EjercitosInsuficientesException e) {
                inputText.getStyleClass().add("invalid");
                inputText.clear();
                inputText.requestFocus();

                MenuLateralDerecho menuLateral = (MenuLateralDerecho) campoDeJuego.getRight();
                VBox form = (VBox) menuLateral.getChildren().get(1);

                form.getChildren().get(3).setVisible(true);
            }
        }
    }

    private void mostrarAtaque() {
        Dados dadosAtacante = atacante.getEjercito().getDados();
        Dados dadosDefensor = defensor.getEjercito().getDados();

        VistaDados vistaDadosAtacante = new VistaDados(dadosAtacante);
        VistaDados vistaDadosDefensor = new VistaDados(dadosDefensor);

        HBox hBox1 = new HBox(crearLabel(atacante), crearLabel(defensor));
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox(vistaDadosAtacante, vistaDadosDefensor);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(20);
        VBox vBox = new VBox(hBox1, hBox2);
        vBox.setSpacing(20);
        MenuLateralDerecho menuLateralDerecho = (MenuLateralDerecho) campoDeJuego.getRight();
        menuLateralDerecho.getChildren().add(vBox);
    }

    private Label crearLabel(Pais pais) {
        Label label = new Label(pais.getNombre());
        label.getStyleClass().add("labelText");
        return label;
    }

}
