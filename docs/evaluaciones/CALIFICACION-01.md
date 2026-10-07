# Retroalimentación — Laboratorio 1: Codificación del diseño OO

**Grupo:** Grupo4 · **Proyecto:** Liga de Fútbol
**Fecha límite:** 2026-09-08 23:59 · **Versión revisada:** commit `a2ff82b`

> **Ojo:** después de la fecha límite se subió un cambio que borró el laboratorio de `main` (se eliminó la carpeta `src/model/domain/`). Ese cambio no se tuvo en cuenta para la nota, pero hoy el laboratorio ya no está en `main`. Revísenlo antes del próximo corte.

## Nota

| Criterio | Peso | Nota (0-5) |
|---|---|---|
| El código sigue el diagrama UML | 60% | 3.0 |
| Pruebas: creación de objetos en el programa | 20% | 4.5 |
| Buenas prácticas de programación | 20% | 3.5 |
| **Nota del laboratorio** | | **3.40** |

La nota se calcula así: 60% diseño UML + 20% pruebas + 20% buenas prácticas.

## 1. El código sigue el diagrama UML (3.0)
**Lo que hicieron bien:**
- Están todas las clases: la interfaz `RolEnPartido`, la clase abstracta `Persona`, sus hijas `Jugador` y `Arbitro`, y además `Equipo`, `Partido` y `Gol`.
- `Persona` es abstracta e implementa `RolEnPartido`. Cada hija escribe su propia versión de `rolEnPartido()`.
- `Jugador` guarda un objeto `Equipo` (no un texto con el nombre del equipo). `Partido` tiene un equipo local y un equipo visitante por separado.
- Todos los atributos son privados y tienen getters y setters.

**Lo que pueden mejorar:**
- Los atributos de `Persona` no se llaman como en el diagrama. Debían ser `identificacion` y `nombre`, pero usaron `nombre` y `documento`, y no hay `getIdentificacion()`.
- Al llamar a `super(...)` desde `Jugador` y `Arbitro` pasaron los datos en el orden contrario. Por eso `getNombre()` devuelve la identificación en vez del nombre. Al correr el programa sale "Jugador: 1010" en lugar de "Jugador: Carlos Ruiz".
- `datosResumen()` debía escribirse una sola vez en `Persona`, porque es igual para todos. En cambio lo repitieron en cada hija.
- Falta que el constructor de `Persona` revise que la identificación no venga vacía y lance `IllegalArgumentException` si es así.
- `Equipo` tiene dos métodos `agregarJugador` que hacen casi lo mismo. No es grave, pero sobra uno y el diagrama no lo pedía.

## 2. Pruebas: creación de objetos (4.5)
**Lo que hicieron bien:**
- `PruebaCreacionObjetos` compila y corre sin errores.
- Crean un `Jugador` y un `Arbitro`, agregan el jugador a un `Equipo` y también prueban `Partido` y `Gol`.
- Guardan los dos objetos en una lista de `RolEnPartido` y llaman a sus métodos sin usar `instanceof`. Cada uno responde a su manera.

**Lo que pueden mejorar:**
- Por el error del orden en `super(...)`, en consola sale la identificación donde debería salir el nombre. Así la prueba se entiende menos.

## 3. Buenas prácticas (3.5)
**Lo que hicieron bien:**
- Hicieron varios commits en distintos momentos, con mensajes en español que explican bien qué agregaron en cada paso.

**Lo que pueden mejorar:**
- No siguieron la estructura de carpetas acordada en clase: el dominio quedó repartido entre `src/model/domain/` y otra carpeta paralela (`src/main/java/com/grupo4/ligafutbol/`).
- Todo el trabajo se subió directo a `main`, sin usar ramas.
- Casi todos los commits son de una sola persona, y el último se hizo desde una cuenta compartida de la sala. No se ve el aporte de los demás integrantes.
- La clase `main` está en minúscula. En Java los nombres de clase empiezan con mayúscula (por ejemplo `Main`).

## ¿El programa funciona?
Sí, compila y corre sin errores. El único problema visible es que se muestra el número de identificación donde debería aparecer el nombre del jugador y del árbitro.

## Para el próximo laboratorio
- Recuperen el laboratorio en `main` y dejen todo el dominio en una sola carpeta: `src/model/domain/` (paquete `model.domain`).
- Revisen el orden de los datos al llamar a `super(...)`. Usen los mismos nombres del diagrama para no confundirse.
- Escriban en `Persona` los métodos que son iguales para todos (como `datosResumen()`) y dejen en las hijas solo lo que cambia.
- Agreguen en los constructores las validaciones que pide el diagrama.
- Trabajen con ramas y que cada integrante haga commits desde su propia cuenta, no desde la cuenta de la sala.
