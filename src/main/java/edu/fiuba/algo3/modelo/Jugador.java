package edu.fiuba.algo3.modelo;

public class Jugador {
    private final int id;
    private String color = "";
    private int cantidadPaisesDominados = 0;
    private Objetivo secreto;
    private Objetivo general = new General();
    private Rol rol = new RolIndefinido();
    private Turno turno = new SinTurno();

    public Jugador(int id) {
        this.id = id;
    }

    public Jugador() { // despues lo volamo
        this.id = 0;
    }

    public String mostrarColor() {
        return color;
    }

    public int cantidadPaisesDominados() {
        return cantidadPaisesDominados;
    }

    public int obtenerId() {
        return this.id;
    }

    public void colocarEjercitos(Pais pais) {
        pais.colocarEjercito(new Ejercito(this));
        cantidadPaisesDominados++;
    }

    public void asignarColor(String color) {
        this.color = color;
    }

    public Objetivo obtenerObjetivoSecreto() {
        return this.secreto;
    }

    public Objetivo obtenerObjetivoGeneral() {
        return this.general;
    }

    public Dados tirarDados(Pais pais) {
        return this.rol.tirarDados(pais);
    }

    public int pedirCantidad() {
        return 4; // por ahora, despues se la pedimos al usuario
    }

    public void rolAtacante() {
        rol = new Atacante();
    }

    public void rolDefensor() {
        rol = new Defensor();
    }

    public void setTurno(Turno unTurno) {
        this.turno = unTurno;
    }

    public void finalizarAtaque() {
        this.turno.finalizarAtaque();
    }

    public void finalizarReagrupe() {
        this.turno.finalizarReagrupe();
//        this.turno.cambiarRonda();
    }

    public void finalizarColocacion() {
        this.turno.finalizarColocacion();
    }
}
