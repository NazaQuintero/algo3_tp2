package edu.fiuba.algo3.modelo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.google.gson.*;
import edu.fiuba.algo3.modelo.continentes.*;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDePaisesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeTarjetasNoEncontradoException;
import edu.fiuba.algo3.modelo.tarjetas.*;

public class CargarJuego {

    public static void cargarPaisesAlTablero(Tablero tablero, String archivoPaises) throws IOException, ArchivoDePaisesNoEncontradoException {

        HashMap<Pais, ArrayList<String>> limitrofes = new HashMap<>();
        HashMap<String, Pais> paises = new HashMap<>();
        HashMap<String, Continente> continentes = new HashMap<>();

        InputStream is = CargarJuego.class.getClassLoader().getResourceAsStream(archivoPaises);
        if (is == null) throw new ArchivoDePaisesNoEncontradoException();

        String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Deserializer. Guarda cada Pais en "paises" y el nombre de los limitrofes en "limitrofes"
        JsonDeserializer<Pais> deserializer = (jsonElement, type, jsonDeserializationContext) -> {

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Crea el objeto pais sin limitrofes
            String nombrePais = jsonObject.get("Pais").getAsString();
            Pais pais = new Pais(nombrePais);
            paises.put(nombrePais, pais);
            limitrofes.put(pais, new ArrayList<>());

            String nombre_continente = jsonObject.get("Continente").getAsString();
            if (!continentes.containsKey(nombre_continente)){
                Continente continente = nuevoContinente(nombre_continente);
                continentes.put(nombre_continente, continente);
            }

            Continente continente = continentes.get(nombre_continente);
            continente.agregarPais(pais);
            tablero.agregarContinente(continente);

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
                pais.limitaCon(paises.get(limitrofe));
            }
            tablero.agregarPais(pais);
        }
    }

    public static void cargarTarjetas(ArrayList<Tarjeta> tarjetas, String archivoTarjetas, Tablero tablero) throws IOException, ArchivoDeTarjetasNoEncontradoException {

        InputStream is = CargarJuego.class.getClassLoader().getResourceAsStream(archivoTarjetas);
        if (is == null) throw new ArchivoDeTarjetasNoEncontradoException();

        String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Tarjeta> deserializer = (jsonElement, type, jsonDeserializationContext) -> {

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String nombrePais = jsonObject.get("Pais").getAsString();

            return new Tarjeta(tablero.obtenerPais(nombrePais), nuevoSimbolo(jsonObject.get("Simbolo").getAsString()));
        };

        gsonBuilder.registerTypeAdapter(Tarjeta.class, deserializer);
        Gson gson = gsonBuilder.create();

        Tarjeta[] _tarjetas = gson.fromJson(json, Tarjeta[].class);

        tarjetas.addAll(Arrays.asList(_tarjetas));
    }

    private static Simbolo nuevoSimbolo(String simbolo) {

        switch (simbolo) {
            case "Globo": return new Globo();
            case "Barco": return new Barco();
            case "Cañon": return new Canion();
        }
        return null;
    }

    private static Continente nuevoContinente(String nombreContinente) {
        switch (nombreContinente){
            case "Asia": return new Asia(nombreContinente);
            case "Africa": return new Africa(nombreContinente);
            case "Oceania": return new Oceania(nombreContinente);
            case "Europa": return new Europa(nombreContinente);
            case "America del Norte": return new AmericaDelNorte(nombreContinente);
            case "America del Sur": return new AmericaDelSur(nombreContinente);
            default: return new Asia(nombreContinente);
        }
    }

}
