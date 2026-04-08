package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Revisiones {
    private final List<Revision> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public ArrayList<Revision> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> revisionesCliente = new ArrayList<>();
        for (Revision r : coleccionRevisiones) {
            if (r.getCliente().equals(cliente)) {
                revisionesCliente.add(r);
            }
        }
        return revisionesCliente;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = new ArrayList<>();
        for (Revision r : coleccionRevisiones) {
            if (r.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(r);
            }
        }
        return revisionesVehiculo;
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede insertar una revisión nula.");
        }
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        Revision revisionExistente = buscar(revision);
        if (revisionExistente != null) {
            if (!revisionExistente.estaCerrada()) {
                throw new TallerMecanicoExcepcion("Ya existe una revisión igual.");
            } else {
                coleccionRevisiones.remove(revisionExistente);
            }
        }
        coleccionRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion {
        for (Revision r : coleccionRevisiones) {
            if (!r.estaCerrada()) {
                if (r.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                }
                if (r.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            }
            if (r.estaCerrada() && !r.getFechaFin().isBefore(fechaRevision)) {
                if (r.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                }
                if (r.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }

    private Revision getRevision(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No puedo operar sobre una revisión nula.");
        }
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        return revisionExistente;
    }

    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        Revision revision1 = getRevision(revision);
        revision1.anadirHoras(horas);
        return revision1;
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        Revision revision1 = getRevision(revision);
        revision1.anadirPrecioMaterial(precioMaterial);
        return revision1;
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Revision revision1 = getRevision(revision);
        revision1.cerrar(fechaFin);
        return revision1;
    }

    public Revision buscar(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("No se puede buscar una revisión nula.");
        }
        for (Revision r : coleccionRevisiones) {
            if (r.equals(revision)) {
                return r;
            }
        }
        return null;
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede borrar una revisión nula.");
        }
        Revision revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        coleccionRevisiones.remove(revisionExistente);
    }
}

