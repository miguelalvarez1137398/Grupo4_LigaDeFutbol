package com.grupo4.ligafutbol.model.domain;

public class JugadorDeCampo extends Jugador {
    private String posicion;

    public JugadorDeCampo() {
        super();
    }

    public JugadorDeCampo(String nombre, String identificacion, int numeroCamiseta, String posicion) {
        super(nombre, identificacion, numeroCamiseta);
        setPosicion(posicion);
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        if (posicion == null || posicion.trim().isEmpty()) {
            throw new IllegalArgumentException("La posición no puede estar vacía.");
        }
        this.posicion = posicion.trim();
    }

    @Override
    public String toString() {
        return "Jugador de campo: " + super.toString() + " - Posición: " + posicion;
    }
}
