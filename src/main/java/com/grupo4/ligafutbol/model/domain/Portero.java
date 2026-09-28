package com.grupo4.ligafutbol.model.domain;

public class Portero extends Jugador {
    private int golesRecibidos;

    public Portero() {
        super();
    }

    public Portero(String identificacion, String nombre, int numeroCamiseta, int golesRecibidos) {
        super(identificacion, nombre, numeroCamiseta);
        setGolesRecibidos(golesRecibidos);
    }

    public int getGolesRecibidos() {
        return golesRecibidos;
    }

    public void setGolesRecibidos(int golesRecibidos) {
        if (golesRecibidos < 0) {
            throw new IllegalArgumentException("Los goles recibidos no pueden ser negativos.");
        }
        this.golesRecibidos = golesRecibidos;
    }

    @Override
    public String rolEnPartido() {
        return "Portero";
    }

    @Override
    public String toString() {
        return "Portero: " + getNombre()
                + " | ID: " + getIdentificacion()
                + " | Camiseta: " + getNumeroCamiseta()
                + " | Goles recibidos: " + golesRecibidos
                + " | Equipo: " + (getEquipo() != null ? getEquipo().getNombre() : "Sin equipo");
    }
}
