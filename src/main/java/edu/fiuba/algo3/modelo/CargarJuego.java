package edu.fiuba.algo3.modelo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import com.google.gson.*;
import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.continentes.*;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeContinentesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDePaisesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeTarjetasNoEncontradoException;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.*;

public class CargarJuego {

    private static final String ARCHIVO_CONTINENTES = "continentes.json";
    private static final String ARCHIVO_PAISES = "paises.json";
    private static final String ARCHIVO_TARJETAS = "tarjetas.json";

    public static void cargarContinentes() throws ArchivoDeContinentesNoEncontradoException {
        String json;

        Gson gson = new Gson();

        try {
            InputStream is = App.class.getClassLoader().getResourceAsStream(ARCHIVO_CONTINENTES);
            json = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ArchivoDeContinentesNoEncontradoException();
        }

        Continente[] _continentes  = gson.fromJson(json, Continente[].class);
        for(Continente continente: _continentes) {
            continente.setEjercitosNulos();
            MultitonPaises.cargarPaises(continente.getPaises());
        }
        MultitonContinentes.cargarContinentes(_continentes);

    }

    public static void cargarPaisesLimitrofes() throws ArchivoDePaisesNoEncontradoException {
        String json;

        try {
            InputStream is = App.class.getClassLoader().getResourceAsStream(ARCHIVO_PAISES);
            json = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new ArchivoDePaisesNoEncontradoException();
        }

        JsonDeserializer<Pais> deserializer = (jsonElement, type, jsonDeserializationContext) -> {

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String nombrePais = jsonObject.get("Pais").getAsString();

            Pais pais = MultitonPaises.obtenerInstanciaDe(nombrePais);

            String nombresLimitrofes = jsonObject.get("limitrofes").getAsString();
            String[] arrayNombreLimitrofes = nombresLimitrofes.split(",");

            for (String limitrofe : arrayNombreLimitrofes)
                pais.limitaCon(MultitonPaises.obtenerInstanciaDe(limitrofe));

            return pais;
        };

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Pais.class, deserializer);

        Gson gson = gsonBuilder.create();
        Pais[] paises = gson.fromJson(json, Pais[].class);
        //MultitonPaises.cargarPaises(new ArrayList<>(Arrays.asList(paises)));
    }

    public static void cargarTarjetas(Juego juego) throws ArchivoDeTarjetasNoEncontradoException {
        String json;
        try {
            InputStream is = CargarJuego.class.getClassLoader().getResourceAsStream(ARCHIVO_TARJETAS);
            json = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e) {throw new ArchivoDeTarjetasNoEncontradoException();}
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Tarjeta> deserializer = (jsonElement, type, jsonDeserializationContext) -> {

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String nombrePais = jsonObject.get("Pais").getAsString();

            return new Tarjeta(MultitonPaises.obtenerInstanciaDe(nombrePais), nuevoSimbolo(jsonObject.get("Simbolo").getAsString()));
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
