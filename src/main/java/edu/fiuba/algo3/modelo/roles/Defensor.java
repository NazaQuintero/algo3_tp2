package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.Pais;

public class Defensor implements Rol {
    int cantidadEjercitos = 0;
    public Defensor(int cantidadEjercitos){
        this.cantidadEjercitos = cantidadEjercitos;
    }

    public Dados tirarDados(Pais pais) {
        return new Dados(Math.min(cantidadEjercitos, 3));
    }

}
