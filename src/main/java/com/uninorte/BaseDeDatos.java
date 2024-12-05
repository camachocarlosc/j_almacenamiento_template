package com.uninorte;

import java.io.*;
import java.util.*;

public class BaseDeDatos {

    private static Map<Integer, Tabla> tablas = new HashMap<>();
    private static int contadorTablas = 0;

    static {
        cargarTablas();
    }

    public static String AgregarRegistro(Object... valores) {
        int idTabla = obtenerIdTablaPorEstructura(valores);
        if (idTabla == -1) {
            idTabla = ++contadorTablas;
            tablas.put(idTabla, new Tabla(idTabla, valores));
        }
        Tabla tabla = tablas.get(idTabla);
        String idRegistro = tabla.agregarRegistro(valores);
        guardarTablas();
        return idRegistro;
    }

    public static int ObtenerNumeroTablas() {
        return tablas.size();
    }

    public static int ObtenerNumRegistrosEnTabla(int idTabla) {
        if (!tablas.containsKey(idTabla)) return -1;
        return tablas.get(idTabla).obtenerNumRegistros();
    }

    public static void EditarReg(String idRegistro, Object... valores) {
        try {
            int idTabla = Integer.parseInt(idRegistro.substring(0, 3));
            if (tablas.containsKey(idTabla)) {
                tablas.get(idTabla).editarRegistro(idRegistro, valores);
                guardarTablas();
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: ID de registro no válido.");
        }
    }

    public static String ConsultarReg(String idRegistro) {
        try {
            int idTabla = Integer.parseInt(idRegistro.substring(0, 3));
            if (tablas.containsKey(idTabla)) {
                return tablas.get(idTabla).consultarRegistro(idRegistro);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: ID de registro no válido.");
        }
        return null;
    }

    public static void BorrarReg(String idRegistro) {
        try {
            int idTabla = Integer.parseInt(idRegistro.substring(0, 3));
            if (tablas.containsKey(idTabla)) {
                tablas.get(idTabla).borrarRegistro(idRegistro);
                guardarTablas();
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: ID de registro no válido.");
        }
    }

    public static String ImprimirTodo() {
        StringBuilder resultado = new StringBuilder();
        for (Tabla tabla : tablas.values()) {
            resultado.append(tabla.imprimirRegistros()).append(" ----- ");
        }
        if (resultado.length() > 0) {
            resultado.setLength(resultado.length() - 6);
        }
        return resultado.toString().trim();
    }

    public static void BorrarTodo() {
        tablas.clear();
        contadorTablas = 0;
        guardarTablas();
    }

    private static int obtenerIdTablaPorEstructura(Object... valores) {
        for (Map.Entry<Integer, Tabla> entry : tablas.entrySet()) {
            if (entry.getValue().estructuraCompatible(valores)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private static void cargarTablas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tablas.dat"))) {
            tablas = (Map<Integer, Tabla>) ois.readObject();
            contadorTablas = tablas.size();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se pudieron cargar las tablas: " + e.getMessage());
        }
    }

    private static void guardarTablas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tablas.dat"))) {
            oos.writeObject(tablas);
        } catch (IOException e) {
            System.err.println("No se pudieron guardar las tablas: " + e.getMessage());
        }
    }
}
