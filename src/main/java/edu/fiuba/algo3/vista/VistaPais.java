package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.AtaqueEventHandler;
import edu.fiuba.algo3.controladores.PaisEventHandler;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.fichas.Fichas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class VistaPais extends StackPane implements Observer {
    private CampoDeJuego campoDeJuego;
    private Pais pais;
    private ArrayList<VistaPais> vistaLimitrofes = new ArrayList<>();

    public VistaPais(Pais pais, CampoDeJuego campoDeJuego) {
        this.getStylesheets().add("styles.css");
        this.pais = pais;
        this.campoDeJuego = campoDeJuego;

        this.setLayoutX(pais.getPosX());
        this.setLayoutY(pais.getPosY());

        Circle ficha = new Circle(10, pais.dominadoPor().color());
        Label cantEjercitos = crearLabelCantEjercitos();
        VBox formularioDeAtaque = crearFormularioDeAtaque();
        Rectangle boton = crearBotonPais(formularioDeAtaque);

        this.getChildren().addAll(ficha, cantEjercitos, boton);

        pais.addObserver(this);
    }

    private Label crearLabelCantEjercitos(){
        Label cantEjercitos = new Label();
        cantEjercitos.getStyleClass().add("ejercito");
        cantEjercitos.setText(Integer.toString(pais.cantidadEjercitos()));
        return cantEjercitos;
    }

    private Rectangle crearBotonPais(VBox formularioDeAtaque){
        Rectangle boton = new Rectangle(50, 50);
        boton.setFill(new Color(0f,0f,0f,0));
        boton.getStyleClass().add("pais");
        boton.setOnMouseClicked(new PaisEventHandler(this, campoDeJuego, formularioDeAtaque));
        return boton;
    }

    private VBox crearFormularioDeAtaque() {
        Label label = new Label("Ingrese la cantidad de ejercitos para atacar: ");
        label.getStyleClass().add("labelText");
        TextField inputText = new TextField();
        inputText.getStyleClass().add("textField");
        Button botonAtacar = new Button("Atacarrr!!!");
        botonAtacar.getStyleClass().add("startButton");
        botonAtacar.setOnMouseClicked(new AtaqueEventHandler(campoDeJuego, pais, inputText));

        campoDeJuego.setSpacing(50);
        VBox vBox = new VBox(label, inputText, botonAtacar);
        vBox.setSpacing(10);
        return vBox;
    }

    @Override
    public void update() {
        Label l = (Label) this.getChildren().get(1);
        l.setText(Integer.toString(pais.cantidadEjercitos()));

        System.out.println("Hay "+ this.pais.cantidadEjercitos() + " ejercito/s en " + this.pais.getNombre() +
                " que pertenece a " + pais.dominadoPor().obtenerNombre());
    }

    public ArrayList<Pais> getLimitrofes() {
        return pais.getPaisesLimitrofes();
    }

    public void setVistaLimitrofe(VistaPais vistaPais){
        vistaLimitrofes.add(vistaPais);
    }
    public Pais getPais(){
        return pais;
    }

    public void resaltarLimitrofes(){
        campoDeJuego.resaltarLimitrofes(this);
    }

    public ArrayList<VistaPais> getVistaLimitrofes() { return vistaLimitrofes; }

    public void desactivar(){
        this.setDisable(true);
        String color = pais.dominadoPor().color().toString();
        ((Circle) this.getChildren().get(0)).setFill(Color.web(color, 0.4));
    }

    public void activar() {
        this.setDisable(false);
        ((Circle) this.getChildren().get(0)).setFill(pais.dominadoPor().color());
    }

}
