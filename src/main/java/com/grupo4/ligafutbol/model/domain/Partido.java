package com.grupo4.ligafutbol.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Arbitro arbitro;
    private List<Gol> goles;

    public Partido() {
        this.goles = new ArrayList<>();
    }

    public Partido(Equipo equipoLocal, Equipo equipoVisitante, Arbitro arbitro) {
        this();
        setEquipoLocal(equipoLocal);
        setEquipoVisitante(equipoVisitante);
        setArbitro(arbitro);
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        if (equipoLocal == null) {
            throw new IllegalArgumentException("El equipo local no puede ser nulo.");
        }
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        if (equipoVisitante == null) {
            throw new IllegalArgumentException("El equipo visitante no puede ser nulo.");
        }
        if (equipoVisitante.equals(equipoLocal)) {
            throw new IllegalArgumentException("El equipo visitante debe ser distinto al local.");
        }
        this.equipoVisitante = equipoVisitante;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        if (arbitro == null) {
            throw new IllegalArgumentException("El árbitro no puede ser nulo.");
        }
        this.arbitro = arbitro;
    }

    public List<Gol> getGoles() {
        return goles;
    }

    public void agregarGol(Gol gol) {
        if (gol == null) {
            throw new IllegalArgumentException("El gol no puede ser nulo.");
        }
        goles.add(gol);
    }

    @Override
    public String toString() {
        return equipoLocal.getNombre() + " vs " + equipoVisitante.getNombre() + " - Árbitro: " + arbitro.getNombre();
    }
}
