package com.grupo4.ligafutbol.model.domain;

public class Arbitro extends Persona {
    private String categoria;

    public Arbitro() {
        super();
    }

    public Arbitro(String identificacion, String nombre, String categoria) {
        super(identificacion, nombre);
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
    public String rolEnPartido() {
        return "Árbitro";
    }

    @Override
    public String toString() {
        return "Árbitro: " + getNombre()
                + " | ID: " + getIdentificacion()
                + " | Categoría: " + categoria;
    }
}
