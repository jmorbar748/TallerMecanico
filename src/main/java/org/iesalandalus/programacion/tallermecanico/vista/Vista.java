package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.Controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.List;
import java.util.Objects;

public class Vista {

    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "ERROR: El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
        controlador.terminar();
    }

    public void terminar() {System.out.println("¡¡¡Hasta luego Lucassss!!!");}

    private void ejecutar(Opcion opcion) {
        try {
            switch (opcion) {
                case INSERTAR_CLIENTE -> insertarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case INSERTAR_REVISION -> insertarRevision();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BUSCAR_VEHICULO -> buscarVehiculo();
                case BUSCAR_REVISION -> buscarRevision();
                case MODIFICAR_CLIENTE -> modificarCliente();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrarRevision();
                case BORRAR_CLIENTE -> borrarCliente();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case BORRAR_REVISION -> borrarRevision();
                case LISTAR_CLIENTES -> listarClientes();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case LISTAR_REVISIONES -> listarRevisiones();
                case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
                case SALIR -> salir();
            }
        } catch(Exception e) {
            System.out.printf("ERROR: %s%n", e.getMessage());
        }
    }

    private void insertarCliente () throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Cliente");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Cliente insertado correctamente.");
    }

    private void insertarVehiculo () throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Vehiculo");
        controlador.insertar(Consola.leerVehiculo());
        System.out.println("Vehiculo insertado correctamente.");
    }

    private void insertarRevision () throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Revision");
        controlador.insertar(Consola.leerRevision());
        System.out.println("Revisión insertada correctamente.");
    }

    private void buscarCliente () {
        Consola.mostrarCabecera("Buscar cliente");
        Cliente cliente = controlador.buscar(Consola.leerClienteDni());
        System.out.println((cliente != null) ? cliente : "No existe ningún cliente con dicho DNI.");
    }

    private void buscarVehiculo () {
        Consola.mostrarCabecera("Buscar Vehiculo");
        Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
        System.out.println((vehiculo != null) ? vehiculo : "No existe ningún vehículo con dicha matrícula.");
    }

    private void buscarRevision () {
        Consola.mostrarCabecera("Buscar Revision");
        Revision revision = controlador.buscar(Consola.leerRevision());
        System.out.println((revision != null) ? "Revision: " + revision : "No existe ninguna revision para ese cliente.");
    }

    private void modificarCliente () throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Modificar Cliente");
        controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        System.out.println("El cliente se ha modificado correctamente.");
    }

    private void anadirHoras () throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir Horas Revision");
        controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
        System.out.println("Horas añadidas correctamente.");
    }

    private void anadirPrecioMaterial () throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Añadir Precio Material Revisión");
        controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        System.out.println("Precio material añadido correctamente.");
    }

    private void cerrarRevision () throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Cerrar Revision");
        controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
        System.out.println("Revisión cerrada correctamente.");
    }

    private void borrarCliente () throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar Cliente");
        controlador.borrar(Consola.leerClienteDni());
        System.out.println("Cliente borrado correctamente.");
    }

    private void borrarVehiculo () throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar Vehiculo");
        controlador.borrar(Consola.leerVehiculoMatricula());
        System.out.println("Vehiculo borrado correctamente.");
    }

    private void borrarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Borrar Revision");
        controlador.borrar(Consola.leerRevision());
        System.out.println("Revisión borrada correctamente.");
    }

    private void listarClientes() {
        Consola.mostrarCabecera("Listar clientes");
        List<Cliente> clientes = controlador.getClientes();
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("No hay clientes que mostrar.");
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("Listar Vehículos");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if (!vehiculos.isEmpty()) {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No hay vehículos que mostrar.");
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera( "Listar Revisiones");
        List<Revision> revisiones = controlador.getRevisiones();
        if (!revisiones.isEmpty()) {
            for (Revision revision : revisiones) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones que mostrar.");
        }
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("Listar Revisiones Cliente");
        List<Revision> revisionesCliente = controlador.getRevisiones(Consola.leerClienteDni());
        if (!revisionesCliente.isEmpty()) {
            for (Revision revision : revisionesCliente) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones que mostrar para dicho cliente.");
        }
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("Listar Revisiones Vehiculo");
        List<Revision> revisionesVehiculo = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        if (!revisionesVehiculo.isEmpty()) {
            for (Revision revision : revisionesVehiculo) {
                System.out.println(revision);
            }
        } else {
            System.out.println("No hay revisiones que mostrar para dicho vehículo.");
        }
    }

    private void salir() {
        //No hacemos nada

    }
}