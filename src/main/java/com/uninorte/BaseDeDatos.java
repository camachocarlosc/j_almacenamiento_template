package com.uninorte;

import java.util.*;

public class BaseDeDatos {

    private static Map<Integer, Tabla> tablas = new HashMap<>();
    private static int contadorTablas = 0;

    public static String AgregarRegistro(Object... valores) {
        int idTabla = obtenerIdTablaPorEstructura(valores);
        if (idTabla == -1) {
            idTabla = ++contadorTablas;
            tablas.put(idTabla, new Tabla(idTabla, valores));
        }
        Tabla tabla = tablas.get(idTabla);
        return tabla.agregarRegistro(valores);
    }

    public static int ObtenerNumeroTablas() {
        return tablas.size();
    }

    public static int ObtenerNumRegistrosEnTabla(int idTabla) {
        if (!tablas.containsKey(idTabla)) return -1;
        return tablas.get(idTabla).obtenerNumRegistros();
    }

    public static void EditarReg(String idRegistro, Object... valores) {
        int idTabla = Integer.parseInt(idRegistro.substring(0, 3));
        if (tablas.containsKey(idTabla)) {
            tablas.get(idTabla).editarRegistro(idRegistro, valores);
        }
    }

    public static String ImprimirTodo() {
        StringBuilder resultado = new StringBuilder();
        for (Tabla tabla : tablas.values()) {
            resultado.append(tabla.imprimirRegistros()).append(" ----- ");
        }
        return resultado.toString().trim();
    }

    public static void BorrarTodo() {
        tablas.clear();
        contadorTablas = 0;
    }

    private static int obtenerIdTablaPorEstructura(Object... valores) {
        for (Map.Entry<Integer, Tabla> entry : tablas.entrySet()) {
            if (entry.getValue().estructuraCompatible(valores)) {
                return entry.getKey();
            }
        }
        return -1;
    }
}