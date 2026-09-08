package com.grupo4.ligafutbol.model.structures;

public class Pila<T> {
    private Nodo<T> cima;
    private int tamaño;

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamaño++;
    }

    public T pop() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamaño--;
        return dato;
    }

    public T peek() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return cima.getDato();
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int tamaño() {
        return tamaño;
    }

    private static class Nodo<T> {
        private T dato;
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
