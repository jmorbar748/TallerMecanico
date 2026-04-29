package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;

import java.time.LocalDate;

public class Mecanico extends Trabajo {

    public static final float FACTOR_HORA = 30F;
    public static final float FACTOR_PRECIO_MATERIAL = 1.5f;
    public float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
        precioMaterial = 0;
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        precioMaterial = mecanico.precioMaterial;
    }

    public float getPrecioMaterial() {return precioMaterial; }

    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        }
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    @Override
    public float getPrecioEspecifico() {
        return (estaCerrado()) ? FACTOR_HORA * getHoras() + FACTOR_PRECIO_MATERIAL * getPrecioMaterial() : 0;
    }

    @Override
    public String toString() {
        String cadena;
        if (!estaCerrado()) {
            cadena = String.format("Mecánico -> %s - %s (%s - ): %d horas, %.2f € en material", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getHoras(), getPrecioMaterial());
        } else {
            cadena = String.format("Mecánico -> %s - %s (%s - %s): %d horas, %.2f € en material, %.2f € total", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getFechaFin().format(FORMATO_FECHA), getHoras(), getPrecioMaterial(), getPrecio());
        }
        return cadena;
    }

}
