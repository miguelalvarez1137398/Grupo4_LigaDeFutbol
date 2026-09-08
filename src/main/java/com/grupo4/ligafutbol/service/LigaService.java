package com.grupo4.ligafutbol.service;

import com.grupo4.ligafutbol.model.domain.Arbitro;
import com.grupo4.ligafutbol.model.domain.Equipo;
import com.grupo4.ligafutbol.model.domain.Gol;
import com.grupo4.ligafutbol.model.domain.Jugador;
import com.grupo4.ligafutbol.model.domain.Partido;
import com.grupo4.ligafutbol.model.domain.Portero;
import com.grupo4.ligafutbol.model.domain.JugadorDeCampo;

import java.util.ArrayList;
import java.util.List;

public class LigaService {
    private final List<Equipo> equipos;
    private final List<Arbitro> arbitros;
    private final List<Partido> partidos;

    public LigaService() {
        this.equipos = new ArrayList<>();
        this.arbitros = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public void registrarEquipo(Equipo equipo) {
        if (equipo == null) {
            throw new IllegalArgumentException("El equipo no puede ser nulo.");
        }
        equipos.add(equipo);
    }

    public void registrarArbitro(Arbitro arbitro) {
        if (arbitro == null) {
            throw new IllegalArgumentException("El árbitro no puede ser nulo.");
        }
        arbitros.add(arbitro);
    }

    public void registrarJugadorEnEquipo(Equipo equipo, Jugador jugador) {
        if (equipo == null || jugador == null) {
            throw new IllegalArgumentException("Equipo y jugador no pueden ser nulos.");
        }
        equipo.agregarJugador(jugador);
    }

    public void registrarPartido(Partido partido) {
        if (partido == null) {
            throw new IllegalArgumentException("El partido no puede ser nulo.");
        }
        partidos.add(partido);
    }

    public void agregarGolAPartido(Partido partido, Gol gol) {
        if (partido == null || gol == null) {
            throw new IllegalArgumentException("Partido y gol no pueden ser nulos.");
        }
        partido.agregarGol(gol);
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public List<Arbitro> getArbitros() {
        return arbitros;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void mostrarEquipos() {
        for (Equipo equipo : equipos) {
            System.out.println("- " + equipo.getNombre() + " | " + equipo.getCiudad());
            for (Jugador jugador : equipo.getJugadores()) {
                System.out.println("   • " + jugador.getNombre() + " | camiseta " + jugador.getNumeroCamiseta());
            }
        }
    }

    public void mostrarPartidos() {
        for (Partido partido : partidos) {
            System.out.println(partido);
            for (Gol gol : partido.getGoles()) {
                System.out.println("   • " + gol);
            }
        }
    }

    public Jugador crearJugador(String tipo, String nombre, String identificacion, int numeroCamiseta, String detalleExtra) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de jugador no puede ser nulo.");
        }

        switch (tipo.toLowerCase()) {
            case "portero":
                return new Portero(nombre, identificacion, numeroCamiseta, Integer.parseInt(detalleExtra));
            case "jugadordecampo":
                return new JugadorDeCampo(nombre, identificacion, numeroCamiseta, detalleExtra);
            default:
                throw new IllegalArgumentException("Tipo de jugador no válido.");
        }
    }
}
