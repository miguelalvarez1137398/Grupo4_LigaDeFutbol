package com.grupo4.ligafutbol.service;

import com.grupo4.ligafutbol.model.domain.Arbitro;
import com.grupo4.ligafutbol.model.domain.Equipo;
import com.grupo4.ligafutbol.model.domain.Gol;
import com.grupo4.ligafutbol.model.domain.Jugador;
import com.grupo4.ligafutbol.model.domain.JugadorDeCampo;
import com.grupo4.ligafutbol.model.domain.Partido;
import com.grupo4.ligafutbol.model.domain.Portero;
import com.grupo4.ligafutbol.model.structures.ListaSimple;

public class LigaService {
    private final ListaSimple<Equipo> equipos = new ListaSimple<>();
    private final ListaSimple<Arbitro> arbitros = new ListaSimple<>();
    private final ListaSimple<Partido> partidos = new ListaSimple<>();

    public void registrarEquipo(Equipo equipo) {
        if (equipo == null) throw new IllegalArgumentException("El equipo no puede ser nulo.");
        equipos.insertarFinal(equipo);
    }

    public void registrarArbitro(Arbitro arbitro) {
        if (arbitro == null) throw new IllegalArgumentException("El árbitro no puede ser nulo.");
        arbitros.insertarFinal(arbitro);
    }

    public void registrarPartido(Partido partido) {
        if (partido == null) throw new IllegalArgumentException("El partido no puede ser nulo.");
        partidos.insertarFinal(partido);
    }

    public int cantidadEquipos() { return equipos.tamaño(); }
    public Equipo buscarEquipoPorIndice(int indice) { return equipos.buscarPorIndice(indice); }
    public int cantidadArbitros() { return arbitros.tamaño(); }
    public Arbitro buscarArbitroPorIndice(int indice) { return arbitros.buscarPorIndice(indice); }
    public int cantidadPartidos() { return partidos.tamaño(); }
    public Partido buscarPartidoPorIndice(int indice) { return partidos.buscarPorIndice(indice); }

    public void insertarJugadorInicio(Equipo equipo, Jugador jugador) {
        validar(equipo, jugador, "Equipo y jugador");
        if (equipo.existeNumeroCamiseta(jugador.getNumeroCamiseta())) {
            throw new IllegalArgumentException("Ese número de camiseta ya existe en el equipo.");
        }
        if (jugador.getEquipo() != null && jugador.getEquipo() != equipo) {
            throw new IllegalArgumentException("El jugador ya pertenece a otro equipo.");
        }
        jugador.setEquipo(equipo);
        equipo.getJugadores().insertarInicio(jugador);
    }

    // Relación Equipo (1) -> Jugador (muchos)
    public void agregarJugador(Equipo equipo, Jugador jugador) {
        validar(equipo, jugador, "Equipo y jugador");
        equipo.agregarJugador(jugador);
    }

    public int cantidadJugadores(Equipo equipo) {
        return equipo.getJugadores().tamaño();
    }

    public Jugador buscarJugadorPorIndice(Equipo equipo, int indice) {
        return equipo.getJugadores().buscarPorIndice(indice);
    }

    public Jugador buscarJugadorPorValor(Equipo equipo, Jugador jugador) {
        return equipo.getJugadores().buscarPorValor(jugador);
    }

    public void insertarJugadorEnPosicion(Equipo equipo, Jugador jugador, int posicion) {
        validar(equipo, jugador, "Equipo y jugador");
        if (equipo.existeNumeroCamiseta(jugador.getNumeroCamiseta())) {
            throw new IllegalArgumentException("Ese número de camiseta ya existe en el equipo.");
        }
        if (jugador.getEquipo() != null && jugador.getEquipo() != equipo) {
            throw new IllegalArgumentException("El jugador ya pertenece a otro equipo.");
        }
        jugador.setEquipo(equipo);
        equipo.getJugadores().insertarEnPosicion(jugador, posicion);
    }

    public boolean eliminarJugador(Equipo equipo, Jugador jugador) {
        boolean eliminado = equipo.getJugadores().eliminarPorValor(jugador);
        if (eliminado && jugador.getEquipo() == equipo) {
            jugador.setEquipo(null);
        }
        return eliminado;
    }

    public void listarJugadores(Equipo equipo) {
        equipo.getJugadores().recorrerEImprimir();
    }

    // Relación Partido (1) -> Gol (muchos)
    public void agregarGol(Partido partido, Gol gol) {
        validar(partido, gol, "Partido y gol");
        partido.agregarGol(gol);
    }

    public int cantidadGoles(Partido partido) {
        return partido.getGoles().tamaño();
    }

    public Gol buscarGolPorIndice(Partido partido, int indice) {
        return partido.getGoles().buscarPorIndice(indice);
    }

    public Gol buscarGolPorValor(Partido partido, Gol gol) {
        return partido.getGoles().buscarPorValor(gol);
    }

    public void insertarGolEnPosicion(Partido partido, Gol gol, int posicion) {
        validar(partido, gol, "Partido y gol");
        if (gol.getEquipo() != partido.getEquipoLocal() && gol.getEquipo() != partido.getEquipoVisitante()) {
            throw new IllegalArgumentException("El equipo del gol debe participar en el partido.");
        }
        partido.getGoles().insertarEnPosicion(gol, posicion);
    }

    public boolean eliminarGol(Partido partido, Gol gol) {
        return partido.getGoles().eliminarPorValor(gol);
    }

    public void listarGoles(Partido partido) {
        partido.getGoles().recorrerEImprimir();
    }

    public ListaSimple<Equipo> getEquipos() {
        return equipos;
    }

    public ListaSimple<Arbitro> getArbitros() {
        return arbitros;
    }

    public ListaSimple<Partido> getPartidos() {
        return partidos;
    }

    public Jugador crearJugador(String tipo, String identificacion, String nombre,
                                int numeroCamiseta, String detalleExtra) {
        if (tipo == null) throw new IllegalArgumentException("El tipo de jugador no puede ser nulo.");

        if (tipo.equalsIgnoreCase("portero")) {
            return new Portero(identificacion, nombre, numeroCamiseta,
                    Integer.parseInt(detalleExtra));
        }
        if (tipo.equalsIgnoreCase("jugadordecampo")) {
            return new JugadorDeCampo(identificacion, nombre, numeroCamiseta, detalleExtra);
        }
        throw new IllegalArgumentException("Tipo de jugador no válido.");
    }

    private void validar(Object primero, Object segundo, String mensaje) {
        if (primero == null || segundo == null) {
            throw new IllegalArgumentException(mensaje + " no pueden ser nulos.");
        }
    }
}
