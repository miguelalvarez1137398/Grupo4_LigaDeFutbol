package com.grupo4.ligafutbol.view;

import com.grupo4.ligafutbol.model.domain.Arbitro;
import com.grupo4.ligafutbol.model.domain.Equipo;
import com.grupo4.ligafutbol.model.domain.Gol;
import com.grupo4.ligafutbol.model.domain.Jugador;
import com.grupo4.ligafutbol.model.domain.Partido;
import com.grupo4.ligafutbol.service.LigaService;

import java.util.Scanner;

public class ConsoleView {
    private final LigaService service;
    private final Scanner scanner;

    public ConsoleView() {
        service = new LigaService();
        scanner = new Scanner(System.in);
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        Equipo millonarios = new Equipo("Millonarios", "Bogotá");
        Equipo santaFe = new Equipo("Santa Fe", "Bogotá");
        Arbitro arbitro = new Arbitro("A001", "Luis Pérez", "Nacional");

        Jugador jugador1 = service.crearJugador("jugadordecampo", "J001", "Mateo García", 10, "Delantero");
        Jugador jugador2 = service.crearJugador("jugadordecampo", "J002", "Santiago López", 7, "Volante");
        Jugador portero = service.crearJugador("portero", "P001", "Andrés Torres", 1, "15");

        service.agregarJugador(millonarios, jugador1);
        service.agregarJugador(millonarios, jugador2);
        service.agregarJugador(millonarios, portero);

        service.agregarJugador(santaFe,
                service.crearJugador("jugadordecampo", "J003", "Camilo Ruiz", 9, "Delantero"));
        service.agregarJugador(santaFe,
                service.crearJugador("portero", "P002", "Daniel Mora", 1, "18"));

        service.registrarEquipo(millonarios);
        service.registrarEquipo(santaFe);
        service.registrarArbitro(arbitro);

        Partido partido = new Partido(millonarios, santaFe, arbitro);
        service.agregarGol(partido, new Gol(millonarios, jugador1, 25));
        service.agregarGol(partido, new Gol(santaFe, service.buscarJugadorPorIndice(santaFe, 0), 58));
        service.registrarPartido(partido);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1: registrarEquipo(); break;
                    case 2: registrarJugador(); break;
                    case 3: registrarArbitro(); break;
                    case 4: registrarPartido(); break;
                    case 5: listarEquipos(); break;
                    case 6: listarPartidos(); break;
                    case 7: menuListas(); break;
                    case 0: System.out.println("Programa finalizado."); break;
                    default: System.out.println("Opción inválida."); break;
                }
            } catch (RuntimeException e) {
                System.out.println("No se pudo realizar la operación: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n========== LIGA DE FÚTBOL ==========");
        System.out.println("1. Registrar equipo");
        System.out.println("2. Registrar jugador");
        System.out.println("3. Registrar árbitro");
        System.out.println("4. Registrar partido");
        System.out.println("5. Listar equipos");
        System.out.println("6. Listar partidos");
        System.out.println("7. Operaciones de ListaSimple<T>");
        System.out.println("0. Salir");
    }

    private void registrarEquipo() {
        String nombre = leerTexto("Nombre del equipo: ");
        String ciudad = leerTexto("Ciudad: ");
        service.registrarEquipo(new Equipo(nombre, ciudad));
        System.out.println("Equipo registrado.");
    }

    private void registrarJugador() {
        Equipo equipo = seleccionarEquipo();
        String tipo = leerTexto("Tipo (portero/jugadordecampo): ");
        String identificacion = leerTexto("Identificación: ");
        String nombre = leerTexto("Nombre: ");
        int camiseta = leerEntero("Número de camiseta: ");
        String extra = tipo.equalsIgnoreCase("portero")
                ? String.valueOf(leerEntero("Goles recibidos: "))
                : leerTexto("Posición: ");

        Jugador jugador = service.crearJugador(tipo, identificacion, nombre, camiseta, extra);
        service.agregarJugador(equipo, jugador);
        System.out.println("Jugador registrado en " + equipo.getNombre() + ".");
    }

    private void registrarArbitro() {
        String identificacion = leerTexto("Identificación: ");
        String nombre = leerTexto("Nombre: ");
        String categoria = leerTexto("Categoría: ");
        service.registrarArbitro(new Arbitro(identificacion, nombre, categoria));
        System.out.println("Árbitro registrado.");
    }

    private void registrarPartido() {
        Equipo local = seleccionarEquipo();
        Equipo visitante = seleccionarEquipo();

        if (local == visitante) {
            throw new IllegalArgumentException("Los equipos deben ser diferentes.");
        }

        Arbitro arbitro = seleccionarArbitro();
        Partido partido = new Partido(local, visitante, arbitro);
        service.registrarPartido(partido);
        System.out.println("Partido registrado.");
    }

    private void listarEquipos() {
        System.out.println("\n--- EQUIPOS ---");
        for (int i = 0; i < service.cantidadEquipos(); i++) {
            Equipo equipo = service.buscarEquipoPorIndice(i);
            System.out.println(i + ". " + equipo);
            service.listarJugadores(equipo);
        }
    }

    private void listarPartidos() {
        System.out.println("\n--- PARTIDOS ---");
        for (int i = 0; i < service.cantidadPartidos(); i++) {
            Partido partido = service.buscarPartidoPorIndice(i);
            System.out.println(i + ". " + partido);
            service.listarGoles(partido);
        }
    }

    private void menuListas() {
        int opcion;
        do {
            System.out.println("\n========== LISTAS DE LA LIGA ==========");
            System.out.println("1. Equipo -> Jugadores");
            System.out.println("2. Partido -> Goles");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                if (opcion == 1) menuJugadores();
                else if (opcion == 2) menuGoles();
            } catch (RuntimeException e) {
                System.out.println("Operación no válida: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void menuJugadores() {
        Equipo equipo = seleccionarEquipo();
        int opcion;
        do {
            System.out.println("\n--- Lista de jugadores de " + equipo.getNombre() + " ---");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Insertar en posición");
            System.out.println("4. Buscar por índice");
            System.out.println("5. Buscar por valor");
            System.out.println("6. Listar");
            System.out.println("7. Eliminar por valor");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");

            try {
                switch (opcion) {
                    case 1:
                        insertarJugadorInicio(equipo, crearJugadorDesdeConsola());
                        System.out.println("Jugador agregado al inicio.");
                        break;
                    case 2:
                        service.agregarJugador(equipo, crearJugadorDesdeConsola());
                        System.out.println("Jugador agregado al final.");
                        break;
                    case 3:
                        // La operación específica se ofrece mediante el método de servicio.
                        Jugador nuevo = crearJugadorDesdeConsola();
                        int posicion = leerEntero("Posición (0 a " + service.cantidadJugadores(equipo) + "): ");
                        insertarJugadorEnPosicion(equipo, nuevo, posicion);
                        System.out.println("Jugador insertado en posición " + posicion + ".");
                        break;
                    case 4:
                        int indice = leerEntero("Índice: ");
                        System.out.println(service.buscarJugadorPorIndice(equipo, indice));
                        break;
                    case 5:
                        Jugador jugadorBuscar = crearJugadorReferencia();
                        Jugador encontrado = service.buscarJugadorPorValor(equipo, jugadorBuscar);
                        System.out.println(encontrado == null ? "No encontrado." : encontrado);
                        break;
                    case 6:
                        service.listarJugadores(equipo);
                        break;
                    case 7:
                        int indiceEliminar = leerEntero("Índice del jugador a eliminar: ");
                        Jugador eliminar = service.buscarJugadorPorIndice(equipo, indiceEliminar);
                        System.out.println(service.eliminarJugador(equipo, eliminar)
                                ? "Jugador eliminado." : "No se encontró.");
                        break;
                }
            } catch (RuntimeException e) {
                System.out.println("Operación no válida: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void menuGoles() {
        Partido partido = seleccionarPartido();
        int opcion;
        do {
            System.out.println("\n--- Lista de goles ---");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Insertar en posición");
            System.out.println("4. Buscar por índice");
            System.out.println("5. Buscar por valor");
            System.out.println("6. Listar");
            System.out.println("7. Eliminar por valor");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");

            try {
                switch (opcion) {
                    case 1:
                        insertarGolEnPosicion(partido, crearGol(partido), 0);
                        System.out.println("Gol insertado al inicio.");
                        break;
                    case 2:
                        service.agregarGol(partido, crearGol(partido));
                        System.out.println("Gol insertado al final.");
                        break;
                    case 3:
                        Gol gol = crearGol(partido);
                        int posicion = leerEntero("Posición (0 a " + service.cantidadGoles(partido) + "): ");
                        insertarGolEnPosicion(partido, gol, posicion);
                        System.out.println("Gol insertado en posición " + posicion + ".");
                        break;
                    case 4:
                        int indice = leerEntero("Índice: ");
                        System.out.println(service.buscarGolPorIndice(partido, indice));
                        break;
                    case 5:
                        System.out.println("Ingrese los datos del gol a buscar.");
                        Gol referencia = crearGol(partido);
                        Gol encontrado = service.buscarGolPorValor(partido, referencia);
                        System.out.println(encontrado == null ? "No encontrado." : encontrado);
                        break;
                    case 6:
                        service.listarGoles(partido);
                        break;
                    case 7:
                        int indiceEliminar = leerEntero("Índice del gol a eliminar: ");
                        Gol eliminar = service.buscarGolPorIndice(partido, indiceEliminar);
                        System.out.println(service.eliminarGol(partido, eliminar)
                                ? "Gol eliminado." : "No se encontró.");
                        break;
                }
            } catch (RuntimeException e) {
                System.out.println("Operación no válida: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private Jugador crearJugadorDesdeConsola() {
        String tipo = leerTexto("Tipo (portero/jugadordecampo): ");
        String id = leerTexto("Identificación: ");
        String nombre = leerTexto("Nombre: ");
        int camiseta = leerEntero("Número de camiseta: ");
        String extra = tipo.equalsIgnoreCase("portero")
                ? String.valueOf(leerEntero("Goles recibidos: "))
                : leerTexto("Posición: ");
        return service.crearJugador(tipo, id, nombre, camiseta, extra);
    }

    private Jugador crearJugadorReferencia() {
        String id = leerTexto("Identificación del jugador: ");
        return new Jugador(id, "Referencia", 99);
    }

    private Gol crearGol(Partido partido) {
        Equipo equipo = seleccionarEquipoDelPartido(partido);
        System.out.println("Jugadores del equipo:");
        service.listarJugadores(equipo);
        int indiceJugador = leerEntero("Índice del jugador: ");
        Jugador jugador = service.buscarJugadorPorIndice(equipo, indiceJugador);
        int minuto = leerEntero("Minuto del gol (1-120): ");
        return new Gol(equipo, jugador, minuto);
    }

    private void insertarJugadorInicio(Equipo equipo, Jugador jugador) {
        service.insertarJugadorInicio(equipo, jugador);
    }

    private void insertarJugadorEnPosicion(Equipo equipo, Jugador jugador, int posicion) {
        if (equipo.existeNumeroCamiseta(jugador.getNumeroCamiseta())) {
            throw new IllegalArgumentException("Ese número de camiseta ya existe.");
        }
        if (jugador.getEquipo() != null && jugador.getEquipo() != equipo) {
            throw new IllegalArgumentException("El jugador ya pertenece a otro equipo.");
        }
        jugador.setEquipo(equipo);
        service.insertarJugadorEnPosicion(equipo, jugador, posicion);
    }

    private void insertarGolEnPosicion(Partido partido, Gol gol, int posicion) {
        service.insertarGolEnPosicion(partido, gol, posicion);
    }

    private Equipo seleccionarEquipo() {
        if (service.cantidadEquipos() == 0) {
            throw new IllegalStateException("No hay equipos registrados.");
        }
        for (int i = 0; i < service.cantidadEquipos(); i++) {
            System.out.println(i + ". " + service.buscarEquipoPorIndice(i));
        }
        return service.buscarEquipoPorIndice(leerEntero("Índice del equipo: "));
    }

    private Equipo seleccionarEquipoDelPartido(Partido partido) {
        System.out.println("1. " + partido.getEquipoLocal().getNombre());
        System.out.println("2. " + partido.getEquipoVisitante().getNombre());
        return leerEntero("Equipo: ") == 1 ? partido.getEquipoLocal() : partido.getEquipoVisitante();
    }

    private Arbitro seleccionarArbitro() {
        if (service.cantidadArbitros() == 0) {
            throw new IllegalStateException("No hay árbitros registrados.");
        }
        for (int i = 0; i < service.cantidadArbitros(); i++) {
            System.out.println(i + ". " + service.buscarArbitroPorIndice(i));
        }
        return service.buscarArbitroPorIndice(leerEntero("Índice del árbitro: "));
    }

    private Partido seleccionarPartido() {
        if (service.cantidadPartidos() == 0) {
            throw new IllegalStateException("No hay partidos registrados.");
        }
        for (int i = 0; i < service.cantidadPartidos(); i++) {
            System.out.println(i + ". " + service.buscarPartidoPorIndice(i));
        }
        return service.buscarPartidoPorIndice(leerEntero("Índice del partido: "));
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Debe ingresar un número entero.");
            scanner.next();
            System.out.print(mensaje);
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}
