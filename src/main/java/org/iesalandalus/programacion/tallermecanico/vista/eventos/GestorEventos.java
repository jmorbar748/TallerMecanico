package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorEventos {

    private final Map<Evento, List<ReceptorEventos>> receptores;

    public GestorEventos(Evento... eventos) {
        receptores = new HashMap<>();
        for (Evento evento : eventos) {
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptor, Evento... eventos) {
        for (Evento evento : eventos) {
            List<ReceptorEventos> suscriptores = receptores.get(evento);
            if (suscriptores != null && !suscriptores.contains(receptor)) {
                suscriptores.add(receptor);
            }
        }
    }

    public void desuscribir(ReceptorEventos receptor, Evento... eventos) {
        for (Evento evento : eventos) {
            List<ReceptorEventos> suscriptores = receptores.get(evento);
            if (suscriptores != null) {
                suscriptores.remove(receptor);
            }
        }
    }

    public void notificar(Evento evento) {
        List<ReceptorEventos> suscriptores = receptores.get(evento);
        if (suscriptores != null) {
            for (ReceptorEventos receptor : suscriptores) {
                receptor.actualizar(evento);
            }
        }
    }
}
