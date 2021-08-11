package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.batallas_de_dados.Dados;
import edu.fiuba.algo3.modelo.paises.Pais;

public class Defensor implements Rol {
    int cantidadEjercitos;
    public Defensor(int cantidadEjercitos){
        this.cantidadEjercitos = cantidadEjercitos;
    }

    public Dados tirarDados(Pais pais) {
        return new Dados(Math.min(cantidadEjercitos, 3));
    }

}
