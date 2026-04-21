package org.iesalandalus.programacion.tallermecanico.Controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador {

    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        Objects.requireNonNull(modelo, "ERROR: El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "ERROR: La vista no puede ser nula.");
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        modelo.insertar(cliente);
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        modelo.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        modelo.insertar(revision);
    }

    public Cliente buscar(Cliente cliente) {
        return modelo.buscar(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return modelo.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        return modelo.buscar(revision);
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return modelo.modificar(cliente, nombre, telefono);
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        return modelo.anadirHoras(revision, horas);
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        return modelo.anadirPrecioMaterial(revision, precioMaterial);
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return modelo.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        modelo.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        modelo.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        modelo.borrar(revision);
    }

    public List<Cliente> getClientes() { return modelo.getClientes(); }

    public List<Vehiculo> getVehiculos() { return modelo.getVehiculos(); }

    public List<Revision> getRevisiones() { return modelo.getRevisiones(); }

    public List<Revision> getRevisiones(Cliente cliente) { return modelo.getRevisiones(cliente); }

    public List<Revision> getRevisiones(Vehiculo vehiculo) { return modelo.getRevisiones(vehiculo); }

}
