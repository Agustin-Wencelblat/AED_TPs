package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T elem) {
            valor = elem;
            siguiente = null;
            anterior = null;
        }
    }

    public ListaEnlazada() {
        longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevoNodo = new Nodo(elem);
        if (longitud == 0) {
            primero = nuevoNodo;
        }
        else if (longitud == 1){
            ultimo = primero;
            primero = nuevoNodo;
            primero.siguiente = ultimo;
            ultimo.anterior = primero;
        }
        else {
            nuevoNodo.siguiente = primero;
            primero.anterior = nuevoNodo;
            primero = nuevoNodo;
        }
        longitud++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevoNodo = new Nodo(elem);
        if (longitud == 0) {
            primero = nuevoNodo;
        }
        else if (longitud == 1){
            ultimo = nuevoNodo;
            primero.siguiente = ultimo;
            ultimo.anterior = primero;
        }
        else {
            nuevoNodo.anterior = ultimo;
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
        }
        longitud++;
    }

    public T obtener(int i) {
        Nodo actual = primero;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        Nodo ant = primero;
        Nodo actual = primero;
        int k = 0;
        while (k<i){
            ant = actual;
            actual = actual.siguiente;
            k++;
        }
        if (i == 0){
            primero = actual.siguiente;
        }
        else{
            ant.siguiente = actual.siguiente;
        }
        longitud--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        if (indice == 0){
            primero.valor = elem;
        }
        else{
        for (int j = 0; j < indice; j++) {
            actual = actual.siguiente;
        }
        actual.valor = elem;;
        }
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        Nodo actual = primero;
        while (actual != null) {
            copia.agregarAtras(actual.valor);
            actual = actual.siguiente;
        }
        return copia;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Iterador<T> it = lista.iterador();

        Nodo prim = new Nodo(it.siguiente());
        Nodo actual = prim;

        for (int j = 1; j <lista.longitud; j++){
            Nodo nodito = new Nodo(it.siguiente()); 
            actual.siguiente = nodito;
            nodito.anterior = actual;
            actual = nodito;
                    
        }
        primero = prim;
        ultimo = actual;
        longitud = lista.longitud;    
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo actual = primero;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.siguiente != null) {
                sb.append(", ");
            }
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }

    private class ListaIterador implements Iterador<T> {
        private int pointer;
        private Nodo actual;

        ListaIterador() {
            pointer = 0;
            actual = primero;
        }

        public boolean haySiguiente() {
	        return (pointer != longitud);
        }
        
        public boolean hayAnterior() {
	        return (pointer != 0);
        }

        public T siguiente() {
            T val = actual.valor; 
            pointer++;
            actual = actual.siguiente;
            return (val);
        }    
        

        public T anterior() {
            T val = null;
            if (actual == null){
                if (longitud ==1){
                   val = primero.valor;
                   actual = primero.anterior;
                }
                else{
                    val=ultimo.valor;
                    actual = ultimo.anterior;
                }
            }
            else{
                val = actual.anterior.valor;
                actual = actual.anterior; 
            }
            pointer --;
            return (val);
        
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
