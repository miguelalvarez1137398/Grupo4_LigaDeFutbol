package com.grupo4.ligafutbol.model.structures;

public class ListaSimple<T> {
    private Nodo<T> cabeza;
    private int tamaño;

    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamaño++;
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.getDato().equals(dato)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return true;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null && !actual.getSiguiente().getDato().equals(dato)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() == null) {
            return false;
        }

        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        tamaño--;
        return true;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public int tamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.getDato()).append(" -> ");
            actual = actual.getSiguiente();
        }
        sb.append("null");
        return sb.toString();
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
