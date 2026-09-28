# Grupo 4 - Liga de Fútbol

Proyecto de aula para aplicar `ListaSimple<T>` a dos relaciones uno-a-muchos:

- `Equipo (1) -> Jugador (muchos)`
- `Partido (1) -> Gol (muchos)`

## Estructura

- `src/main/java/com/grupo4/ligafutbol/model/domain/`: entidades del dominio.
- `src/main/java/com/grupo4/ligafutbol/model/structures/`: `Nodo<T>` y `ListaSimple<T>`.
- `src/main/java/com/grupo4/ligafutbol/service/`: operaciones del dominio y de las listas.
- `src/main/java/com/grupo4/ligafutbol/view/`: menú de consola.
- `Main.java`: punto de entrada.

No requiere librerías externas. Usa únicamente clases estándar de Java (`Scanner` y `Objects`).
