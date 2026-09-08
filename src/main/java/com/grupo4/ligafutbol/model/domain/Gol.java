package com.grupo4.ligafutbol.model.domain;

public class Gol {
    private Equipo equipo;
    private Jugador jugador;
    private int minuto;

    public Gol() {
    }

    public Gol(Equipo equipo, Jugador jugador, int minuto) {
        setEquipo(equipo);
        setJugador(jugador);
        setMinuto(minuto);
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        if (equipo == null) {
            throw new IllegalArgumentException("El equipo del gol no puede ser nulo.");
        }
        this.equipo = equipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        if (jugador == null) {
            throw new IllegalArgumentException("El jugador que anotó no puede ser nulo.");
        }
        this.jugador = jugador;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        if (minuto < 1 || minuto > 120) {
            throw new IllegalArgumentException("El minuto del gol debe estar entre 1 y 120.");
        }
        this.minuto = minuto;
    }

    @Override
    public String toString() {
        return jugador.getNombre() + " - " + equipo.getNombre() + " - " + minuto + "'";
    }
}
