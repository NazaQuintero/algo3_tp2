package edu.fiuba.algo3.modelo.tarjetas;
import java.util.ArrayList;
import edu.fiuba.algo3.modelo.canjes.Canje;

public class CanjeHabilitado implements TipoCanje {

    @Override
    public TipoCanje compararTarjetas(ArrayList<Tarjeta> tarjetas) {
        return tarjetas.get(0).compararSimbolos(tarjetas.get(1), tarjetas.get(2));
    }

    @Override
    public Canje canjear(Canje canje) {
       return canje.siguienteCanje();
    }
}
