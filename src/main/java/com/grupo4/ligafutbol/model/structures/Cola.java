package com.grupo4.ligafutbol.model.structures;

public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamaño;

    public void enqueue(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamaño++;
    }

    public T dequeue() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía.");
        }

        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        tamaño--;
        return dato;
    }

    public T peek() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return frente.getDato();
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }

    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
        }

        public T getDato() {
            return dato;
        }

        public Nodo<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<T> siguiente) {
            this.siguiente = siguiente;
        }
    }
}
