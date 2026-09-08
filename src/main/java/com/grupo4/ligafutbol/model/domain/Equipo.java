package com.grupo4.ligafutbol.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String ciudad;
    private List<Jugador> jugadores;

    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    public Equipo(String nombre, String ciudad) {
        this();
        setNombre(nombre);
        setCiudad(ciudad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad del equipo no puede estar vacía.");
        }
        this.ciudad = ciudad.trim();
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void agregarJugador(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador no puede ser nulo.");
        }

        if (existeNumeroCamiseta(jugador.getNumeroCamiseta())) {
            throw new IllegalArgumentException("Ese número de camiseta ya existe en el equipo.");
        }

        if (jugador.getEquipo() != null && jugador.getEquipo() != this) {
            throw new IllegalArgumentException("El jugador ya pertenece a otro equipo.");
        }

        jugador.setEquipo(this);
        jugadores.add(jugador);
    }

    public boolean existeNumeroCamiseta(int numeroCamiseta) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNumeroCamiseta() == numeroCamiseta) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre + " - " + ciudad;
    }
}
