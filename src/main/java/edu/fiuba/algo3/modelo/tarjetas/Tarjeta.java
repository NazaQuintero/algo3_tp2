package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaEstaDesactivadaException;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;

public class Tarjeta {

    private final Pais pais;
    private final Simbolo simbolo;
    private EstadoTarjeta estadoTarjeta;

    public Tarjeta(Pais pais, Simbolo simbolo) {
        this.pais = pais;
        this.simbolo = simbolo;
        this.estadoTarjeta = new TarjetaDesactivada();
    }

    public Pais obtenerPais(){ return pais; }

    public String nombrePais() {
        return pais.obtenerNombre();
    }

    public void activar() throws LaTarjetaYaFueActivadaException {
        estadoTarjeta = estadoTarjeta.activar(pais); // implementame este porfis
        /*this.pais.modificarCantidadEjercito(2); esto lo hacemos en TarjetaDesactivada, en el metodo activar pasandole el pais*/
    }

    public void desactivar() throws LaTarjetaYaEstaDesactivadaException {
        estadoTarjeta = estadoTarjeta.desactivar();
    }

    public String obtenerSimbolo() {
        return simbolo.obtenerDescripcion();
    }

    public boolean coincideSimboloCon(Tarjeta unaTarjeta) {
        return this.obtenerSimbolo() == unaTarjeta.obtenerSimbolo();
    }

    public TipoCanje compararSimbolos(Tarjeta unaTarjeta, Tarjeta otraTarjeta) {
        if (this.coincideSimboloCon(unaTarjeta) && this.coincideSimboloCon(otraTarjeta) || !this.coincideSimboloCon(unaTarjeta) && !this.coincideSimboloCon(otraTarjeta))
            return new CanjeHabilitado();
        return new SinCanje();
    }
}
