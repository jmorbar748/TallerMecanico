package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.List;

//No se si esta bien

public interface ITrabajos {
    List<Trabajo> get();
    List<Trabajo> get(Cliente cliente);
    List<Trabajo> get(Vehiculo vehiculo);
    void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion;
    Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion;
    Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion;
    Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion;
    Trabajo buscar(Trabajo trabajo);
    void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion;
}
