package src.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
    }

    public void agregarJugador(String identificacion, String nombre, int numeroCamiseta, String posicion, int golesTotales) {
        Jugador jugador = new Jugador(identificacion, nombre, numeroCamiseta, posicion, golesTotales, this);
        this.jugadores.add(jugador);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
}