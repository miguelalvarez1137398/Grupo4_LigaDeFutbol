package com.grupo4.ligafutbol.model.domain;

public class Jugador extends Persona {
    private int numeroCamiseta;
    protected Equipo equipo;

    public Jugador() {
        super();
    }

    public Jugador(String nombre, String identificacion, int numeroCamiseta) {
        super(nombre, identificacion);
        setNumeroCamiseta(numeroCamiseta);
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(int numeroCamiseta) {
        if (numeroCamiseta < 1 || numeroCamiseta > 99) {
            throw new IllegalArgumentException("El número de camiseta debe estar entre 1 y 99.");
        }
        this.numeroCamiseta = numeroCamiseta;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return super.toString() + " - Camiseta " + numeroCamiseta + " - Equipo: " + (equipo != null ? equipo.getNombre() : "Sin equipo");
    }
}
