package com.grupo4.ligafutbol.model.domain;

public class Portero extends Jugador {
    private int golesRecibidos;

    public Portero() {
        super();
    }

    public Portero(String nombre, String identificacion, int numeroCamiseta, int golesRecibidos) {
        super(nombre, identificacion, numeroCamiseta);
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
    public String toString() {
        return "Portero: " + super.toString() + " - Goles recibidos: " + golesRecibidos;
    }
}
