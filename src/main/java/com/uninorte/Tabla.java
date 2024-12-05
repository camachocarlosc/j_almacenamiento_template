package com.uninorte;

import java.io.*;
import java.util.*;

public class Tabla implements Serializable {
    private int id;
    private List<String> registros;
    private List<Class<?>> estructura;
    private int contadorRegistros;

    public Tabla(int id, Object... valores) {
        this.id = id;
        this.registros = new ArrayList<>();
        this.estructura = new ArrayList<>();
        for (Object valor : valores) {
            this.estructura.add(valor.getClass());
        }
        this.contadorRegistros = 0;
    }

    public String agregarRegistro(Object... valores) {
        if (!estructuraCompatible(valores)) {
            throw new IllegalArgumentException("Estructura de valores incompatible.");
        }
        String idRegistro = String.format("%03d%03d", id, ++contadorRegistros);
        registros.add(idRegistro + " " + Arrays.toString(valores).replace("[", "").replace("]", "").replace(",", " |").trim());
        return idRegistro;
    }

    public void editarRegistro(String idRegistro, Object... valores) {
        if (!estructuraCompatible(valores)) {
            throw new IllegalArgumentException("Estructura de valores incompatible.");
        }
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).startsWith(idRegistro)) {
                registros.set(i, idRegistro + " " + Arrays.toString(valores).replace("[", "").replace("]", "").replace(",", " |").trim());
                return;
            }
        }
    }

    public String consultarRegistro(String idRegistro) {
        for (String registro : registros) {
            if (registro.startsWith(idRegistro)) {
                return registro;
            }
        }
        return null;
    }

    public void borrarRegistro(String idRegistro) {
        registros.removeIf(registro -> registro.startsWith(idRegistro));
    }

    public boolean estructuraCompatible(Object... valores) {
        if (valores.length != estructura.size()) return false;
        for (int i = 0; i < valores.length; i++) {
            if (!estructura.get(i).isInstance(valores[i])) return false;
        }
        return true;
    }

    public int obtenerNumRegistros() {
        return registros.size();
    }

    public String imprimirRegistros() {
        StringBuilder resultado = new StringBuilder();
        for (String registro : registros) {
            resultado.append("ID: ").append(registro.trim()).append(" --- ");
        }
        if (resultado.length() > 0) {
            resultado.setLength(resultado.length() - 5);
        }
        return resultado.toString().trim();
    }
}
