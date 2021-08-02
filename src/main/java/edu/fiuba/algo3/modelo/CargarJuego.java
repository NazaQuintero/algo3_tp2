package edu.fiuba.algo3.modelo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import com.google.gson.*;
import edu.fiuba.algo3.modelo.continentes.*;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDePaisesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeTarjetasNoEncontradoException;
import edu.fiuba.algo3.modelo.tarjetas.*;

public class CargarJuego {

    public static void cargarPaisesAlJuego(Juego juego, String archivoPaises) throws ArchivoDePaisesNoEncontradoException {

        HashMap<Pais, ArrayList<String>> limitrofes = new HashMap<>();
//        HashMap<String, Pais> paises = new HashMap<>();
        String json;

        try {
            InputStream is = CargarJuego.class.getClassLoader().getResourceAsStream(archivoPaises);
            json = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e) {throw new ArchivoDePaisesNoEncontradoException();}

        GsonBuilder gsonBuilder = new GsonBuilder();

        // Deserializer. Guarda cada Pais en "paises" y el nombre de los limitrofes en "limitrofes"
        JsonDeserializer<Pais> deserializer = (jsonElement, type, jsonDeserializationContext) -> {

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Crea el objeto pais sin limitrofes
            String nombrePais = jsonObject.get("Pais").getAsString();
            //paises.put(nombrePais, pais);
            Pais pais = MultitonPaises.obtenerInstanciaDe(nombrePais);

            limitrofes.put(pais, new ArrayList<>());

            String nombreContinente = jsonObject.get("Continente").getAsString();
            Continente continente = MultitonContinentes.obtenerInstanciaDe(nombreContinente);

            continente.agregarPais(pais);

            String nombresLimitrofes = jsonObject.get("Limita con").getAsString();
            String[] arrayNombreLimitrofes = nombresLimitrofes.split(",");

            for (String limitrofe : arrayNombreLimitrofes) {
                limitrofes.get(pais).add(limitrofe);
            }
            return pais;
        };

        gsonBuilder.registerTypeAdapter(Pais.class, deserializer);
        Gson gson = gsonBuilder.create();

        Pais[] _paises = gson.fromJson(json, Pais[].class);

        // Agrega a cada pais sus limitrofes
        for (Pais pais: _paises) {
            ArrayList<String> nombreLimitrofes = limitrofes.get(pais);
            for (String limitrofe: nombreLimitrofes) {
                pais.limitaCon(MultitonPaises.obtenerInstanciaDe(limitrofe));
            }
//            juego.agregarPais(pais);
        }
    }

    public static void cargarTarjetas(Juego juego , String archivoTarjetas) throws ArchivoDeTarjetasNoEncontradoException {
        String json;
        try {
            InputStream is = CargarJuego.class.getClassLoader().getResourceAsStream(archivoTarjetas);
            json = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e) {throw new ArchivoDeTarjetasNoEncontradoException();}
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Tarjeta> deserializer = (jsonElement, type, jsonDeserializationContext) -> {

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String nombrePais = jsonObject.get("Pais").getAsString();

            return new Tarjeta(juego.obtenerPais(nombrePais), nuevoSimbolo(jsonObject.get("Simbolo").getAsString()));
        };

        gsonBuilder.registerTypeAdapter(Tarjeta.class, deserializer);
        Gson gson = gsonBuilder.create();

        Tarjeta[] tarjetas = gson.fromJson(json, Tarjeta[].class);

        for (Tarjeta t: tarjetas) juego.agregarTarjeta(t);
    }

    private static Simbolo nuevoSimbolo(String simbolo) {

        switch (simbolo) {
            case "Globo": return new Globo();
            case "Barco": return new Barco();
            case "Cañon": return new Canion();
        }
        return null;
    }

}
