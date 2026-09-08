package src;

import src.model.domain.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PruebaCreacionObjetos {
    public static void main(String[] args) {

        Jugador jugador = new Jugador("1010", "Carlos Ruiz", 9, "Delantero", 15, null);
        Arbitro arbitro = new Arbitro("2020", "Mario Torres", "FIFA Profesional");
  
        Equipo equipo = new Equipo("Millonarios FC");
        equipo.agregarJugador(jugador);

        List<RolEnPartido> actoresPartido = new ArrayList<>();
        actoresPartido.add(jugador);
        actoresPartido.add(arbitro);

        System.out.println("\nEjecutando polimorfismo en métodos de rol");
        for (RolEnPartido actor : actoresPartido) {
            System.out.println(actor.datosResumen() + " -> " + actor.rolEnPartido());
        }
        
        Partido partido = new Partido(LocalDate.now(), "2-1", equipo, new Equipo("Santa Fe"));
        Gol gol = new Gol(45, "Penal", jugador);
        System.out.println("\nPartido registrado entre local y visitante con gol del jugador: " 
                + partido.getEquipoLocal().getNombre() + " vs " + partido.getEquipoVisitante().getNombre()
                + " (Gol al minuto " + gol.getMinuto() + ")");
    }
}