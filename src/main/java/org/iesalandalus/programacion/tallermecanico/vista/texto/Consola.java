package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Consola {

    static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {}

    static void mostrarCabecera(String mensaje) {
        System.out.printf("%n%s%n", mensaje);
        String formatoStr = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(formatoStr, 0).replace("0", "-"));
        System.out.printf("-".repeat(mensaje.length()).concat("%n%n"));
    }

    static void mostrarMenu() {
        mostrarCabecera("Gestión de un taller mecánico");
        for (Evento evento : Evento.values()) {
            System.out.println(evento);
        }
    }

    static Evento elegirOpcion() {
        Evento evento = null;
        do {
            try {
                evento = Evento.get(leerEntero("\nElige una opción: "));
            } catch (IllegalArgumentException e) {
                System.out.printf("ERROR: %s%n", e.getMessage());
            }
        } while (evento == null);
        return evento;
    }

    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    static LocalDate leerFecha(String mensaje) {
        LocalDate fecha;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        mensaje = String.format("%s (%s): ", mensaje, CADENA_FORMATO_FECHA);
        try {
            fecha = LocalDate.parse(leerCadena(mensaje), formatoFecha);
        } catch (DateTimeParseException e) {
            fecha = null;
        }
        return fecha;
    }
}
