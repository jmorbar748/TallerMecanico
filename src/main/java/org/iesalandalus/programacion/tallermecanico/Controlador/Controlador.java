package org.iesalandalus.programacion.tallermecanico.Controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador implements IControlador {

    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        Objects.requireNonNull(modelo, "ERROR: El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "ERROR: La vista no puede ser nula.");
        this.modelo = modelo;
        this.vista = vista;
        suscribirAEventos();
    }

    private void suscribirAEventos() {
        vista.getGestorEventos().suscribir(this, Evento.values());
    }

    @Override
    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    @Override
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    @Override
    public void actualizar(Evento evento) {
        try {
            switch (evento) {
                case INSERTAR_CLIENTE -> insertarCliente();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BORRAR_CLIENTE -> borrarCliente();
                case LISTAR_CLIENTES -> listarClientes();
                case MODIFICAR_CLIENTE -> modificarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case BUSCAR_VEHICULO -> buscarVehiculo();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case INSERTAR_REVISION -> insertarRevision();
                case INSERTAR_MECANICO -> insertarMecanico();
                case BUSCAR_TRABAJO -> buscarTrabajo();
                case BORRAR_TRABAJO -> borrarTrabajo();
                case LISTAR_TRABAJOS -> listarTrabajos();
                case LISTAR_TRABAJOS_CLIENTE -> listarTrabajosCliente();
                case LISTAR_TRABAJOS_VEHICULO -> listarTrabajosVehiculo();
                case ANADIR_HORAS_TRABAJO -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_TRABAJO -> anadirPrecioMaterial();
                case CERRAR_TRABAJO -> cerrarTrabajo();
                case SALIR -> salir();
            }
        } catch (Exception e) {
            vista.notificarResultado(evento, e.getMessage(), false);
        }
    }

    private void insertarCliente() throws TallerMecanicoExcepcion {
        modelo.insertar(vista.leerCliente());
        vista.notificarResultado(Evento.INSERTAR_CLIENTE, "Insertar cliente", true);
    }

    private void buscarCliente() {
        Cliente cliente = modelo.buscar(vista.leerClienteDni());
        if (cliente != null) {
            vista.mostrarCliente(cliente);
        } else {
            vista.notificarResultado(Evento.BUSCAR_CLIENTE, "Buscar cliente", false);
        }
    }

    private void borrarCliente() throws TallerMecanicoExcepcion {
        modelo.borrar(vista.leerClienteDni());
        vista.notificarResultado(Evento.BORRAR_CLIENTE, "Borrar cliente", true);
    }

    private void listarClientes() {
        List<Cliente> clientes = modelo.getClientes();
        if (!clientes.isEmpty()) {
            vista.mostrarClientes(clientes);
        } else {
            System.out.println("No hay clientes que mostrar.");
        }
    }

    private void modificarCliente() throws TallerMecanicoExcepcion {
        modelo.modificar(vista.leerClienteDni(), vista.leerNuevoNombre(), vista.leerNuevoTelefono());
        vista.notificarResultado(Evento.MODIFICAR_CLIENTE, "Modificar cliente", true);
    }

    private void insertarVehiculo() throws TallerMecanicoExcepcion {
        modelo.insertar(vista.leerVehiculo());
        vista.notificarResultado(Evento.INSERTAR_VEHICULO, "Insertar vehículo", true);
    }

    private void buscarVehiculo() {
        Vehiculo vehiculo = modelo.buscar(vista.leerVehiculoMatricula());
        if (vehiculo != null) {
            vista.mostrarVehiculo(vehiculo);
        } else {
            vista.notificarResultado(Evento.BUSCAR_VEHICULO, "Buscar vehículo", false);
        }
    }

    private void borrarVehiculo() throws TallerMecanicoExcepcion {
        modelo.borrar(vista.leerVehiculoMatricula());
        vista.notificarResultado(Evento.BORRAR_VEHICULO, "Borrar vehículo", true);
    }

    private void listarVehiculos() {
        List<Vehiculo> vehiculos = modelo.getVehiculos();
        if (!vehiculos.isEmpty()) {
            vista.mostrarVehiculos(vehiculos);
        } else {
            System.out.println("No hay vehículos que mostrar.");
        }
    }

    private void insertarRevision() throws TallerMecanicoExcepcion {
        modelo.insertar(vista.leerRevision());
        vista.notificarResultado(Evento.INSERTAR_REVISION, "Insertar revisión", true);
    }

    private void insertarMecanico() throws TallerMecanicoExcepcion {
        modelo.insertar(vista.leerMecanico());
        vista.notificarResultado(Evento.INSERTAR_MECANICO, "Insertar mecánico", true);
    }

    private void buscarTrabajo() {
        Trabajo trabajo = modelo.buscar(vista.leerTrabajoVehiculo());
        if (trabajo != null) {
            vista.mostrarTrabajo(trabajo);
        } else {
            vista.notificarResultado(Evento.BUSCAR_TRABAJO, "Buscar trabajo", false);
        }
    }

    private void borrarTrabajo() throws TallerMecanicoExcepcion {
        modelo.borrar(vista.leerTrabajoVehiculo());
        vista.notificarResultado(Evento.BORRAR_TRABAJO, "Borrar trabajo", true);
    }

    private void listarTrabajos() {
        List<Trabajo> trabajos = modelo.getTrabajos();
        if (!trabajos.isEmpty()) {
            vista.mostrarTrabajos(trabajos);
        } else {
            System.out.println("No hay trabajos que mostrar.");
        }
    }

    private void listarTrabajosCliente() {
        List<Trabajo> trabajos = modelo.getTrabajos(vista.leerClienteDni());
        if (!trabajos.isEmpty()) {
            vista.mostrarTrabajos(trabajos);
        } else {
            System.out.println("No hay trabajos que mostrar para dicho cliente.");
        }
    }

    private void listarTrabajosVehiculo() {
        List<Trabajo> trabajos = modelo.getTrabajos(vista.leerVehiculoMatricula());
        if (!trabajos.isEmpty()) {
            vista.mostrarTrabajos(trabajos);
        } else {
            System.out.println("No hay trabajos que mostrar para dicho vehículo.");
        }
    }

    private void anadirHoras() throws TallerMecanicoExcepcion {
        modelo.anadirHoras(vista.leerTrabajoVehiculo(), vista.leerHoras());
        vista.notificarResultado(Evento.ANADIR_HORAS_TRABAJO, "Añadir horas", true);
    }

    private void anadirPrecioMaterial() throws TallerMecanicoExcepcion {
        modelo.anadirPrecioMaterial(vista.leerTrabajoVehiculo(), vista.leerPrecioMaterial());
        vista.notificarResultado(Evento.ANADIR_PRECIO_MATERIAL_TRABAJO, "Añadir precio de material", true);
    }

    private void cerrarTrabajo() throws TallerMecanicoExcepcion {
        modelo.cerrar(vista.leerTrabajoVehiculo(), vista.leerFechaCierre());
        vista.notificarResultado(Evento.CERRAR_TRABAJO, "Cerrar trabajo", true);
    }

    private void salir() {
        // No hacemos nada
    }
}
