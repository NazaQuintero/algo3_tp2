package edu.fiuba.algo3.modelo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import com.google.gson.*;
import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.continentes.*;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeContinentesYPaisesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDePaisesLimitrofesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeTarjetasNoEncontradoException;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.*;

public class CargarJuego {

    private static final String ARCHIVO_CONTINENTES_Y_PAISES = "continentesYPaises.json";
    private static final String ARCHIVO_PAISES_LIMITROFES = "paisesLimitrofes.json";
    private static final String ARCHIVO_TARJETAS = "tarjetas.json";

    public static void cargarContinentesYPaises() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        String json;
        Gson gson = new Gson();

        try {
            InputStream is = App.class.getClassLoader().getResourceAsStream(ARCHIVO_CONTINENTES_Y_PAISES);
            json = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e) {
            throw new ArchivoDeContinentesYPaisesNoEncontradoException();
        }

        Continente[] continentes  = gson.fromJson(json, Continente[].class);

        Arrays.stream(continentes).forEach(continente -> {
            continente.setEjercitosNulos();
            MultitonPaises.cargarPaises(continente.getPaises());
        });

        MultitonContinentes.cargarContinentes(continentes);
    }

    public static void cargarPaisesLimitrofes() throws ArchivoDePaisesLimitrofesNoEncontradoException {
        String json;

        try {
            InputStream is = App.class.getClassLoader().getResourceAsStream(ARCHIVO_PAISES_LIMITROFES);
            json = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException | NullPointerException e) {
            throw new ArchivoDePaisesLimitrofesNoEncontradoException();
        }

        JsonDeserializer<Pais> deserializer = (jsonElement, type, jsonDeserializationContext) -> {

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String nombrePais = jsonObject.get("Pais").getAsString();

            Pais pais = MultitonPaises.obtenerInstanciaDe(nombrePais);

            String nombresLimitrofes = jsonObject.get("limitrofes").getAsString();
            String[] arrayNombreLimitrofes = nombresLimitrofes.split(",");

            Arrays.stream(arrayNombreLimitrofes).forEach(nombreLimitrofe -> pais.limitaCon(MultitonPaises.
                    obtenerInstanciaDe(nombreLimitrofe)));

            return pais;
        };

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Pais.class, deserializer);

        Gson gson = gsonBuilder.create();
        gson.fromJson(json, Pais[].class);

    }

    public static void cargarTarjetas() throws ArchivoDeTarjetasNoEncontradoException {
        String json;

        try {
            InputStream is = CargarJuego.class.getClassLoader().getResourceAsStream(ARCHIVO_TARJETAS);
            json = new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException | NullPointerException e) {
            throw new ArchivoDeTarjetasNoEncontradoException();
        }
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Tarjeta> deserializer = (jsonElement, type, jsonDeserializationContext) -> {

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String nombrePais = jsonObject.get("Pais").getAsString();

            return new Tarjeta(MultitonPaises.obtenerInstanciaDe(nombrePais), nuevoSimbolo(jsonObject.get("Simbolo").getAsString()));
        };

        gsonBuilder.registerTypeAdapter(Tarjeta.class, deserializer);
        Gson gson = gsonBuilder.create();

        Tarjeta[] tarjetas = gson.fromJson(json, Tarjeta[].class);

        Arrays.stream(tarjetas).forEach(MultitonTarjetas::agregarTarjeta);
    }

    private static Simbolo nuevoSimbolo(String simbolo) {

        switch (simbolo) {
            case "Globo":
                return new Globo();
            case "Barco":
                return new Barco();
            case "Cañon":
                return new Canion();
        }
        return new Comodin();
    }

}
