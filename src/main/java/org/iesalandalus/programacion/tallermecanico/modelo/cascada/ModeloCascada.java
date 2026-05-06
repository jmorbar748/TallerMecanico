package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//FALTA ACABAR

public class ModeloCascada implements Modelo {
    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        Objects.requireNonNull(fabricaFuenteDatos,"La factoria de la fuente de datos no");
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void comenzar() {System.out.println("Modelo comenzado.");}

    @Override
    public void terminar() { System.out.println("Modelo terminado"); }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Cliente cliente = clientes.buscar(trabajo.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(trabajo.getVehiculo());
        if (trabajo instanceof Revision) {
            trabajo = new Revision(cliente, vehiculo, trabajo.getFechaInicio());
        } else {
            trabajo = new Mecanico(cliente, vehiculo, trabajo.getFechaInicio());
        }
        trabajos.insertar(trabajo);
    }

    public Cliente buscar(Cliente cliente) {
        cliente = Objects.requireNonNull(clientes.buscar(cliente), "No existe un cliente igual.");
        return new Cliente(cliente);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        vehiculo = Objects.requireNonNull(vehiculos.buscar(vehiculo), "No existe un vehiculo igual.");
        return vehiculo;
    }

    public Trabajo buscar(Trabajo trabajo) {
        trabajo = Objects.requireNonNull(trabajos.buscar(trabajo), "No existe una revisión igual.");
        return Trabajo.copiar(trabajo);
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return new Cliente(clientes.modificar(cliente, nombre, telefono));
    }

    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion {
        Trabajo trabajoModificado = trabajos.anadirHoras(trabajo, horas);
        if (trabajoModificado != null) {
            return Trabajo.copiar(trabajoModificado);
        }
        return null; // o return trabajo; según lo que esperen los tests
    }

    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        Trabajo trabajoModificado = trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
        if (trabajoModificado != null) {
            return Trabajo.copiar(trabajoModificado);
        }
        return null; // o return trabajo; según lo que esperen los tests
    }

    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Trabajo trabajoCerrado = trabajos.cerrar(trabajo, fechaFin);
        if (trabajoCerrado != null) {
            return Trabajo.copiar(trabajoCerrado);
        }
        return null; // o return trabajo; según lo que esperen los tests
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Trabajo> trabajosCliente = trabajos.get(cliente);
        for (Trabajo trabajo : trabajosCliente) {
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Trabajo> revisionesVehiculo = trabajos.get(vehiculo);
        for (Trabajo revision : revisionesVehiculo) {
            trabajos.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Trabajo revision) throws TallerMecanicoExcepcion {
        trabajos.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> copiaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            copiaClientes.add(new Cliente(cliente));
        }
        return copiaClientes;
    }

    public List<Vehiculo> getVehiculos() { return vehiculos.get(); }

    public List<Trabajo> getTrabajos() {
        List<Trabajo> copiaTrabajos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()){
            copiaTrabajos.add(Trabajo.copiar(trabajo));
        }
        return copiaTrabajos;
    }

    public List<Trabajo> getTrabajos(Cliente cliente) {
        List<Trabajo> trabajosCliente = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)){
            trabajosCliente.add(Trabajo.copiar(trabajo));
        }
        return trabajosCliente;
    }

    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        List<Trabajo> trabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)){
            trabajosVehiculo.add(Trabajo.copiar(trabajo));
        }
        return trabajosVehiculo;
    }

}
