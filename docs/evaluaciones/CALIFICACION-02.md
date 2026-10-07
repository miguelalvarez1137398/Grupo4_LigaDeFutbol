# Retroalimentación — Laboratorio Lista Simple (Momento 2, Parte 2: implementación)

**Grupo:** Grupo4 · **Proyecto:** Liga de Fútbol

Buen trabajo: la lista simple quedó bien integrada en su proyecto.

## Nota

| Criterio | Peso | Nota (0-5) |
|---|---|---|
| Relaciones uno-a-muchos | 20 % | 5.0 |
| `ListaSimple<T>` integrada al `Service` | 30 % | 4.5 |
| Menú en consola funcional | 15 % | 3.5 |
| Reemplazo del arreglo previo, sin código muerto | 20 % | 5.0 |
| Buenas prácticas (commits y nombres) | 15 % | 1.5 |
| **Nota de la implementación** | | **4.10** |

La nota se calcula así: 20% relaciones + 30% integración al `Service` + 15% menú + 20% reemplazo + 15% buenas prácticas (equivale a 82/100 en la implementación).

## 1. Relaciones uno-a-muchos (5.0)
**Lo que hicieron bien:**
- Eligieron `Equipo` -> `Jugador` y `Partido` -> `Gol`, dos relaciones reales de la liga y justo las sugeridas.

## 2. `ListaSimple<T>` integrada al `Service` (4.5)
**Lo que hicieron bien:**
- `Equipo` guarda sus jugadores y `Partido` sus goles en una `ListaSimple`.
- `LigaService` usa `insertarInicio`, `insertarFinal`, `insertarEnPosicion`, `buscarPorIndice`, `buscarPorValor` y `eliminarPorValor`.
- La `View` no menciona `ListaSimple` directamente.
- Reutilizaron la `ListaSimple` y el `Nodo` en `model/structures`, y las entidades en `model/domain`: siguieron la estructura de carpetas acordada.

**Lo que pueden mejorar:**
- Algunas reglas (revisar el número de camiseta, asignar el equipo al jugador) se repiten en la vista, en el `Service` y en `Equipo`. Deberían vivir solo en el `Service`.
- El `Service` entrega las listas completas con `getEquipos()`, `getArbitros()` y `getPartidos()`. Esto deja a la vista tocar la lista sin pasar por el `Service`.

## 3. Menú en consola (3.5)
**Lo que hicieron bien:**
- Hay un menú para jugadores de un equipo y otro para goles de un partido: insertar (inicio, final, posición), buscar, listar y eliminar.
- Probamos agregar, listar y eliminar en ambos y funcionan.

**Lo que pueden mejorar:**
- "Buscar por valor" en jugadores siempre responde "No encontrado", aunque el jugador exista. Se crea un jugador de prueba con otro nombre y camiseta, y `Jugador` no define cuándo dos jugadores son iguales (falta `equals`).
- Para eliminar, el menú pide un índice y no un valor. Funciona, pero no corresponde con lo que dice la opción.
- El menú de la rúbrica se llamaba `MenuListasView`. Ustedes lo dejaron dentro de `ConsoleView`, lo cual es aceptable.

## 4. Reemplazo del arreglo previo (5.0)
**Lo que hicieron bien:**
- No quedó ningún arreglo ni `ArrayList` sin usar, ni imports o métodos sobrantes. La lista simple reemplazó por completo la estructura anterior.

## 5. Buenas prácticas (1.5)
**Lo que hicieron bien:**
- Los nombres de clases, métodos y variables siguen las convenciones de Java.

**Lo que pueden mejorar:**
- Todo el trabajo de este laboratorio llegó en un único commit, "Proyecto actualizado", directamente sobre `main`. La rúbrica pide commits frecuentes y descriptivos, y trabajo en una rama aparte.
- No hay ramas de trabajo ni historial que muestre el avance paso a paso.

## ¿El programa funciona?
Sí. Compila sin errores, `PruebaCreacionObjetos` corre y termina bien, y el menú permite agregar, listar y eliminar jugadores y goles. Solo falla la búsqueda por valor de jugadores.

## Para el próximo laboratorio
- Hagan commits pequeños y con mensajes claros (por ejemplo, "feat: lista de goles en Partido") y trabajen en una rama que luego se une a `main`.
- Dejen la validación (camiseta repetida, equipo del jugador) solo en el `Service` y no la repitan en la vista.
- Agreguen `equals` a `Jugador` para que la búsqueda por valor funcione.
- Prueben todas las opciones del menú antes de entregar.
