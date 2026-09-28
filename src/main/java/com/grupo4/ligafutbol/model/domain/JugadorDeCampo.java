package com.grupo4.ligafutbol.model.domain;

public class JugadorDeCampo extends Jugador {
    private String posicion;

    public JugadorDeCampo() {
        super();
    }

    public JugadorDeCampo(String identificacion, String nombre, int numeroCamiseta, String posicion) {
        super(identificacion, nombre, numeroCamiseta);
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
    public String rolEnPartido() {
        return "Jugador de campo";
    }

    @Override
    public String toString() {
        return "Jugador de campo: " + getNombre()
                + " | ID: " + getIdentificacion()
                + " | Camiseta: " + getNumeroCamiseta()
                + " | Posición: " + posicion
                + " | Equipo: " + (getEquipo() != null ? getEquipo().getNombre() : "Sin equipo");
    }
}
