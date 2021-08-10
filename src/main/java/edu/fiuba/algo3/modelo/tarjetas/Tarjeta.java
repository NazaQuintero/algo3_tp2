package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.canjes.CanjeHabilitado;
import edu.fiuba.algo3.modelo.canjes.SinCanje;
import edu.fiuba.algo3.modelo.canjes.TipoCanje;
import edu.fiuba.algo3.modelo.paises.Pais;
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

    public void activar() throws LaTarjetaYaFueActivadaException {
        estadoTarjeta = estadoTarjeta.activar(pais);
    }

    public void desactivar() throws LaTarjetaYaEstaDesactivadaException {
        estadoTarjeta = estadoTarjeta.desactivar();
    }

    public String obtenerSimbolo() {
        return this.simbolo.obtenerDescripcion();
    }

    public boolean coincideSimboloCon(Tarjeta unaTarjeta) {
        if (unaTarjeta.obtenerSimbolo().equals("Comodin")) return true;
        return this.obtenerSimbolo().equals(unaTarjeta.obtenerSimbolo());
    }

    public TipoCanje compararSimbolos(Tarjeta unaTarjeta, Tarjeta otraTarjeta) {
        if (this.coincideSimboloCon(unaTarjeta) && this.coincideSimboloCon(otraTarjeta) || !this.coincideSimboloCon(unaTarjeta) && !this.coincideSimboloCon(otraTarjeta))
            return new CanjeHabilitado();
        return new SinCanje();
    }

}
