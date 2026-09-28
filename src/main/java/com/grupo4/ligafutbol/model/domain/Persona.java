package com.grupo4.ligafutbol.model.domain;

public abstract class Persona implements RolEnPartido {
    private String identificacion;
    private String nombre;

    protected Persona() {
    }

    protected Persona(String identificacion, String nombre) {
        setIdentificacion(identificacion);
        setNombre(nombre);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    @Override
    public String datosResumen() {
        return "Nombre: " + nombre + " | Identificación: " + identificacion;
    }

    @Override
    public String toString() {
        return nombre + " (" + identificacion + ")";
    }
}
