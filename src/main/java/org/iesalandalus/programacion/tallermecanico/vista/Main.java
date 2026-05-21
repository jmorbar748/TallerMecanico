package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.Controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;

public class Main {

    public static void main(String[] args) {
        Modelo modelo = FabricaModelo.CASCADA.crear(FabricaFuenteDatos.MEMORIA);
        Vista vista = FabricaVista.TEXTO.crear();
        Controlador controlador = new Controlador(modelo, vista);
        controlador.comenzar();
        controlador.terminar();
    }
}