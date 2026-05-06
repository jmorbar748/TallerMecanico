package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Mecanico;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.List;

public class VistaTexto implements Vista {

    private final GestorEventos gestorEventos;

    public VistaTexto() {
        Evento[] eventos = Evento.values();
        this.gestorEventos = new GestorEventos(eventos);
    }

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            try {
                gestorEventos.notificar(evento);
            } catch (Exception e) {
                System.out.printf("ERROR: %s%n", e.getMessage());
            }
        } while (evento != Evento.SALIR);
    }

    @Override
    public void terminar() {
        System.out.println("¡¡¡Hasta luego!!!");
    }

    @Override
    public Cliente leerCliente() {
        String nombre = Consola.leerCadena("Introduce el nombre: ");
        String dni = Consola.leerCadena("Introduce el DNI: ");
        String telefono = Consola.leerCadena("Introduce el teléfono: ");
        return new Cliente(nombre, dni, telefono);
    }

    @Override
    public Cliente leerClienteDni() {
        return Cliente.get(Consola.leerCadena("Introduce el DNI: "));
    }

    @Override
    public String leerNuevoNombre() {
        return Consola.leerCadena("Introduce el nuevo nombre: ");
    }

    @Override
    public String leerNuevoTelefono() {
        return Consola.leerCadena("Introduce el nuevo teléfono: ");
    }

    @Override
    public Vehiculo leerVehiculo() {
        String marca = Consola.leerCadena("Introduce la marca: ");
        String modelo = Consola.leerCadena("Introduce el modelo: ");
        String matricula = Consola.leerCadena("Introduce la matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(Consola.leerCadena("Introduce la matrícula: "));
    }

    @Override
    public Trabajo leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio");
        return new Revision(cliente, vehiculo, fechaInicio);
    }

    @Override
    public Trabajo leerMecanico() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio");
        return new Mecanico(cliente, vehiculo, fechaInicio);
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return Trabajo.get(leerVehiculoMatricula());
    }

    @Override
    public int leerHoras() {
        return Consola.leerEntero("Introduce las horas a añadir: ");
    }

    @Override
    public float leerPrecioMaterial() {
        return Consola.leerReal("Introduce el precio del material a añadir: ");
    }

    @Override
    public LocalDate leerFechaCierre() {
        return Consola.leerFecha("Introduce la fecha de cierre");
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        if (exito) {
            System.out.printf("Operación %s realizada correctamente.%n", texto);
        } else {
            System.out.printf("ERROR al realizar la operación %s.%n", texto);
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        System.out.println(cliente);
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        System.out.println(vehiculo);
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {
        System.out.println(trabajo);
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo);
        }
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        for (Trabajo trabajo : trabajos) {
            System.out.println(trabajo);
        }
    }
}