import com.grupo4.ligafutbol.model.domain.*;

public class PruebaCreacionObjetos {
    public static void main(String[] args) {
        Jugador jugador = new Jugador("1010", "Carlos Ruiz", 9);
        Arbitro arbitro = new Arbitro("2020", "Mario Torres", "FIFA Profesional");

        System.out.println(jugador.datosResumen() + " -> " + jugador.rolEnPartido());
        System.out.println(arbitro.datosResumen() + " -> " + arbitro.rolEnPartido());

        Equipo equipo = new Equipo("Millonarios", "Bogotá");
        equipo.agregarJugador(jugador);

        Partido partido = new Partido(equipo, new Equipo("Santa Fe", "Bogotá"), arbitro);
        Gol gol = new Gol(equipo, jugador, 45);
        partido.agregarGol(gol);

        if (!"Carlos Ruiz".equals(jugador.getNombre())) {
            throw new AssertionError("El nombre del jugador no coincide.");
        }
        if (!"1010".equals(jugador.getIdentificacion())) {
            throw new AssertionError("La identificación del jugador no coincide.");
        }
        if (equipo.getJugadores().tamaño() != 1) {
            throw new AssertionError("La lista de jugadores no se actualizó.");
        }
        if (partido.getGoles().tamaño() != 1) {
            throw new AssertionError("La lista de goles no se actualizó.");
        }

        System.out.println("PruebaCreacionObjetos: OK");
    }
}
