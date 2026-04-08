package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30;
    private static final float PRECIO_DIA = 10;
    private static final float PRECIO_MATERIAL = 1.5F;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yy");

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Vehiculo vehiculo;
    private Cliente cliente;
    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        this.horas = 0;
        this.precioMaterial = 0;
        this.fechaFin = null;
    }

    public Revision(Revision revision) {
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        fechaFin = revision.fechaFin;
        fechaInicio = revision.fechaInicio;
        horas = revision.horas;
        precioMaterial = revision.precioMaterial;
        vehiculo = revision.vehiculo;
        cliente = new Cliente(revision.cliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        this.cliente = cliente;

    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin,"La fecha de fin no puede ser nulo.");
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion {
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        this.horas += horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(fechaFin,"La fecha de fin no puede ser nula.");
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");
        }
        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        if (!estaCerrada()) {
            return 0;
        }
        return (getDias() * PRECIO_DIA) + (horas * PRECIO_HORA) + (precioMaterial * PRECIO_MATERIAL);
    }

    public int getDias() {
        if (!estaCerrada()) {
            return 0;
        }
        return fechaInicio.until(fechaFin).getDays();
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return horas == revision.horas && Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(vehiculo, revision.vehiculo) && Objects.equals(cliente, revision.cliente);
    }

    @Override
    public int hashCode () {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }

    public String toString() {
        if(!estaCerrada()) {
            return String.format("%s - %s: (%s - ), %d horas, %.2f € en material",
                    this.cliente.toString(), this.vehiculo.toString(),
                    fechaInicio.format(Revision.FORMATO_FECHA), getHoras(), getPrecioMaterial());
        }
        return String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total",
                this.cliente.toString(), this.vehiculo.toString(),
                fechaInicio.format(Revision.FORMATO_FECHA),
                fechaFin.format(Revision.FORMATO_FECHA), getHoras(),
                getPrecioMaterial(), getPrecio());
    }
}

