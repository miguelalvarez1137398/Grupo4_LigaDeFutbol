# Liga de Fútbol - Proyecto Java

Proyecto de consola desarrollado en Java siguiendo la arquitectura de tres capas:

- View: interacción con el usuario
- Service: lógica de negocio
- Model: entidades del dominio

## Dominio

- Persona
- Jugador
- Portero
- JugadorDeCampo
- Arbitro
- Equipo
- Partido
- Gol

## Reglas principales

- Encapsulamiento con atributos privados.
- Getters y setters con validación.
- Herencia simple con `extends`.
- Atributos protegidos cuando son útiles para la subclase.
- Validación del número de camiseta por equipo.
- Validación del minuto de gol entre 1 y 120.

## Cómo ejecutar

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out com.grupo4.ligafutbol.Main
```

## Flujo de ramas sugerido

- `main`: versión estable
- `development`: integración
- `feature/persona-jugador`
- `feature/equipo`
- `feature/partido-gol`
- `feature/menu-console`

## Ejemplo de Git

```bash
git checkout -b development
git checkout -b feature/persona-jugador
git add .
git commit -m "Agrega jerarquia de Persona y Jugador"
git checkout development
git merge feature/persona-jugador
```
