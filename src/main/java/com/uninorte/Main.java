package com.uninorte;


public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de gestión de bases de datos.");

        // Ejemplo de uso básico
        BaseDeDatos.AgregarRegistro(Boolean.valueOf(false), Integer.valueOf(0), "Hola");
        BaseDeDatos.AgregarRegistro(Boolean.valueOf(true), Double.valueOf(1.5));
        System.out.println(BaseDeDatos.ImprimirTodo());

        BaseDeDatos.BorrarTodo();
        System.out.println("Base de datos borrada.");
    }
}