package org.iesalandalus.programacion.tallermecanico.Controlador;

import org.iesalandalus.programacion.tallermecanico.vista.eventos.ReceptorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

public interface IControlador extends ReceptorEventos {
    void comenzar();
    void terminar();
    void actualizar(Evento evento);
}
