package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import java.util.ArrayList;

public class Usuario {
    public int pedirCantidad() { // para mockear, despues cuando hagamos la interfaz de usuario vemos como se implementa xd
        return 4;
    }
    public ArrayList<Tarjeta> pedirTarjetasACanjear() { return null; }
}
