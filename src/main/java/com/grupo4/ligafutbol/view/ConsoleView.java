package com.grupo4.ligafutbol.view;

import com.grupo4.ligafutbol.model.domain.Arbitro;
import com.grupo4.ligafutbol.model.domain.Equipo;
import com.grupo4.ligafutbol.model.domain.Gol;
import com.grupo4.ligafutbol.model.domain.Jugador;
import com.grupo4.ligafutbol.model.domain.JugadorDeCampo;
import com.grupo4.ligafutbol.model.domain.Partido;
import com.grupo4.ligafutbol.model.domain.Portero;
import com.grupo4.ligafutbol.service.LigaService;

import java.util.Scanner;

public class ConsoleView {
    private final LigaService ligaService;
    private final Scanner scanner;

    public ConsoleView() {
        this.ligaService = new LigaService();
        this.scanner = new Scanner(System.in);
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        Equipo equipo1 = new Equipo("Millonarios", "Bogotá");
        Equipo equipo2 = new Equipo("Santa Fe", "Bogotá");
        Arbitro arbitro = new Arbitro("Luis Pérez", "A001", "Nacional");

        Jugador jugador1 = new JugadorDeCampo("Mateo García", "J001", 10, "Delantero");
        Jugador jugador2 = new JugadorDeCampo("Santiago López", "J002", 7, "Volante");
        Jugador portero = new Portero("Andrés Torres", "P001", 1, 15);

        equipo1.agregarJugador(jugador1);
        equipo1.agregarJugador(jugador2);
        equipo1.agregarJugador(portero);

        equipo2.agregarJugador(new JugadorDeCampo("Camilo Ruiz", "J003", 9, "Delantero"));
        equipo2.agregarJugador(new JugadorDeCampo("Nicolás Silva", "J004", 11, "Extremo"));
        equipo2.agregarJugador(new Portero("Daniel Mora", "P002", 12, 18));

        ligaService.registrarEquipo(equipo1);
        ligaService.registrarEquipo(equipo2);
        ligaService.registrarArbitro(arbitro);

        Partido partido = new Partido(equipo1, equipo2, arbitro);
        partido.agregarGol(new Gol(equipo1, jugador1, 25));
        partido.agregarGol(new Gol(equipo2, equipo2.getJugadores().get(0), 58));
        ligaService.registrarPartido(partido);
    }

    public void iniciar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarEquipo();
                    break;
                case 2:
                    registrarJugador();
                    break;
                case 3:
                    registrarArbitro();
                    break;
                case 4:
                    registrarPartido();
                    break;
                case 5:
                    listarEquipos();
                    break;
                case 6:
                    listarPartidos();
                    break;
                case 7:
                    System.out.println("Gracias por usar la liga de fútbol.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 7);
    }

    private void mostrarMenu() {
        System.out.println("\n===== LIGA DE FÚTBOL =====");
        System.out.println("1. Registrar equipo");
        System.out.println("2. Registrar jugador");
        System.out.println("3. Registrar árbitro");
        System.out.println("4. Registrar partido");
        System.out.println("5. Listar equipos");
        System.out.println("6. Listar partidos");
        System.out.println("7. Salir");
    }

    private void registrarEquipo() {
        System.out.println("\n--- Registrar equipo ---");
        String nombre = leerTexto("Nombre del equipo: ");
        String ciudad = leerTexto("Ciudad: ");

        Equipo equipo = new Equipo(nombre, ciudad);
        ligaService.registrarEquipo(equipo);
        System.out.println("Equipo registrado correctamente.");
    }

    private void registrarJugador() {
        System.out.println("\n--- Registrar jugador ---");

        String tipo = leerTexto("Tipo de jugador (portero / jugadordecampo): ");
        String nombre = leerTexto("Nombre: ");
        String identificacion = leerTexto("Identificación: ");
        int numeroCamiseta = leerEntero("Número de camiseta: ");

        String detalleExtra = "";
        if (tipo.equalsIgnoreCase("portero")) {
            detalleExtra = String.valueOf(leerEntero("Goles recibidos: "));
        } else if (tipo.equalsIgnoreCase("jugadordecampo")) {
            detalleExtra = leerTexto("Posición: ");
        }

        String equipoActual = leerTexto("Nombre del equipo: ");
        Equipo equipo = buscarEquipoPorNombre(equipoActual);

        if (equipo == null) {
            System.out.println("No se encontró el equipo. Primero registre el equipo.");
            return;
        }

        Jugador jugador = ligaService.crearJugador(tipo, nombre, identificacion, numeroCamiseta, detalleExtra);
        ligaService.registrarJugadorEnEquipo(equipo, jugador);
        System.out.println("Jugador registrado correctamente.");
    }

    private void registrarArbitro() {
        System.out.println("\n--- Registrar árbitro ---");
        String nombre = leerTexto("Nombre: ");
        String identificacion = leerTexto("Identificación: ");
        String categoria = leerTexto("Categoría: ");

        Arbitro arbitro = new Arbitro(nombre, identificacion, categoria);
        ligaService.registrarArbitro(arbitro);
        System.out.println("Árbitro registrado correctamente.");
    }

    private void registrarPartido() {
        System.out.println("\n--- Registrar partido ---");
        String equipoLocal = leerTexto("Equipo local: ");
        String equipoVisitante = leerTexto("Equipo visitante: ");
        String arbitroNombre = leerTexto("Nombre del árbitro: ");

        Equipo local = buscarEquipoPorNombre(equipoLocal);
        Equipo visitante = buscarEquipoPorNombre(equipoVisitante);
        Arbitro arbitro = buscarArbitroPorNombre(arbitroNombre);

        if (local == null || visitante == null || arbitro == null) {
            System.out.println("Debe existir un equipo local, un equipo visitante y un árbitro válidos.");
            return;
        }

        Partido partido = new Partido(local, visitante, arbitro);

        int golesLocal = leerEntero("Número de goles del equipo local: ");
        int golesVisitante = leerEntero("Número de goles del equipo visitante: ");

        for (int i = 0; i < golesLocal; i++) {
            Jugador jugador = local.getJugadores().get(i % local.getJugadores().size());
            partido.agregarGol(new Gol(local, jugador, 10 + i * 15));
        }

        for (int i = 0; i < golesVisitante; i++) {
            Jugador jugador = visitante.getJugadores().get(i % visitante.getJugadores().size());
            partido.agregarGol(new Gol(visitante, jugador, 20 + i * 17));
        }

        ligaService.registrarPartido(partido);
        System.out.println("Partido registrado correctamente.");
    }

    private void listarEquipos() {
        System.out.println("\n--- Equipos ---");
        ligaService.mostrarEquipos();
    }

    private void listarPartidos() {
        System.out.println("\n--- Partidos ---");
        ligaService.mostrarPartidos();
    }

    private Equipo buscarEquipoPorNombre(String nombre) {
        for (Equipo equipo : ligaService.getEquipos()) {
            if (equipo.getNombre().equalsIgnoreCase(nombre)) {
                return equipo;
            }
        }
        return null;
    }

    private Arbitro buscarArbitroPorNombre(String nombre) {
        for (Arbitro arbitro : ligaService.getArbitros()) {
            if (arbitro.getNombre().equalsIgnoreCase(nombre)) {
                return arbitro;
            }
        }
        return null;
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Debe ser un número entero.");
            scanner.next();
            System.out.print(mensaje);
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}
