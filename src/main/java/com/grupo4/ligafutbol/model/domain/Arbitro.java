package com.grupo4.ligafutbol.model.domain;

public class Arbitro extends Persona {
    private String categoria;

    public Arbitro() {
        super();
    }

    public Arbitro(String nombre, String identificacion, String categoria) {
        super(nombre, identificacion);
        setCategoria(categoria);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
        this.categoria = categoria.trim();
    }

    @Override
    public String toString() {
        return "Árbitro: " + super.toString() + " - Categoría: " + categoria;
    }
}
