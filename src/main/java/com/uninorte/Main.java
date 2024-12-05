package com.uninorte;


public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de gestión de bases de datos.");

        // Ejemplo de uso básico
        System.out.println("Agregando registros...");
        String id1 = BaseDeDatos.AgregarRegistro(Boolean.valueOf(false), Integer.valueOf(0), "Hola ");
        String id2 = BaseDeDatos.AgregarRegistro(Boolean.valueOf(true), Integer.valueOf(1), " mundo");
        String id3 = BaseDeDatos.AgregarRegistro(Boolean.valueOf(true), Double.valueOf(1.5));

        System.out.println("Registros agregados:");
        System.out.println(BaseDeDatos.ImprimirTodo());

        System.out.println("Consultando registros...");
        System.out.println(BaseDeDatos.ConsultarReg(id1));
        System.out.println(BaseDeDatos.ConsultarReg(id2));
        System.out.println(BaseDeDatos.ConsultarReg(id3));

        System.out.println("Editando registros...");
        BaseDeDatos.EditarReg(id1, Boolean.valueOf(true), Integer.valueOf(0), "Hola de nuevo ");
        BaseDeDatos.EditarReg(id2, Boolean.valueOf(false), Integer.valueOf(1), "mundo");
        BaseDeDatos.EditarReg(id3, Boolean.valueOf(false), Double.valueOf(1.5));

        System.out.println("Registros después de la edición:");
        System.out.println(BaseDeDatos.ImprimirTodo());

        System.out.println("Borrando un registro...");
        BaseDeDatos.BorrarReg(id2);
        System.out.println(BaseDeDatos.ImprimirTodo());

        System.out.println("Número de tablas: " + BaseDeDatos.ObtenerNumeroTablas());
        System.out.println("Número de registros en la tabla 1: " + BaseDeDatos.ObtenerNumRegistrosEnTabla(1));
        System.out.println("Número de registros en la tabla 2: " + BaseDeDatos.ObtenerNumRegistrosEnTabla(2));

        System.out.println("Borrando todos los registros...");
        BaseDeDatos.BorrarTodo();
        System.out.println("Base de datos después de borrar todo:");
        System.out.println(BaseDeDatos.ImprimirTodo());
    }
}