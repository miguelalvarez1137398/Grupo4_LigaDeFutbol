package com.grupo4.ligafutbol.model.domain;

public class Persona {
    private String nombre;
    private String identificacion;

    public Persona() {
    }

    public Persona(String nombre, String identificacion) {
        setNombre(nombre);
        setIdentificacion(identificacion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede estar vacía.");
        }
        this.identificacion = identificacion.trim();
    }

    @Override
    public String toString() {
        return nombre + " (" + identificacion + ")";
    }
}
