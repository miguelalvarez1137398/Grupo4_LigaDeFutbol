package com.grupo4.ligafutbol.model.structures;

public class ListaSimple<T> {
    private Nodo<T> cabeza;
    private int tamaño;

    public void insertarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        tamaño++;
    }

    public void insertarFinal(T dato) {
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

    public void insertarEnPosicion(T dato, int indice) {
        if (indice < 0 || indice > tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        if (indice == 0) {
            insertarInicio(dato);
            return;
        }
        if (indice == tamaño) {
            insertarFinal(dato);
            return;
        }

        Nodo<T> anterior = cabeza;
        for (int i = 1; i < indice; i++) {
            anterior = anterior.getSiguiente();
        }

        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevo);
        tamaño++;
    }

    public T buscarPorIndice(int indice) {
        validarIndice(indice);
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public T buscarPorValor(T dato) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (dato == null ? actual.getDato() == null : dato.equals(actual.getDato())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void recorrerEImprimir() {
        Nodo<T> actual = cabeza;
        if (actual == null) {
            System.out.println("(lista vacía)");
            return;
        }
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public boolean eliminarInicio() {
        if (cabeza == null) {
            return false;
        }
        cabeza = cabeza.getSiguiente();
        tamaño--;
        return true;
    }

    public boolean eliminarFinal() {
        if (cabeza == null) {
            return false;
        }
        if (cabeza.getSiguiente() == null) {
            cabeza = null;
            tamaño--;
            return true;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente().getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(null);
        tamaño--;
        return true;
    }

    public boolean eliminarPorValor(T dato) {
        if (cabeza == null) {
            return false;
        }
        if (dato == null ? cabeza.getDato() == null : dato.equals(cabeza.getDato())) {
            return eliminarInicio();
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            T siguienteDato = actual.getSiguiente().getDato();
            if (dato == null ? siguienteDato == null : dato.equals(siguienteDato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public int tamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            resultado.append(actual.getDato()).append(" -> ");
            actual = actual.getSiguiente();
        }
        resultado.append("null");
        return resultado.toString();
    }
}
