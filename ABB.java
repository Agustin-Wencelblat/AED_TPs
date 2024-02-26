package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo raiz;
    private int size;

    private class Nodo {
        T valor;
        Nodo izquierdo;
        Nodo derecho;
        Nodo padre;

        public Nodo(T valor, Nodo padre){
            this.valor = valor;
            this.izquierdo = null;
            this.derecho = null;
            this.padre = padre;
        }

    }

    public ABB() {
        raiz = null;
        size = 0;
    }

    public int cardinal() {
        return size;
    }

    public T minimo(){
        Nodo nodito = raiz;
        while (nodito.izquierdo != null){
            nodito = nodito.izquierdo;
        }
        return nodito.valor;
    }

    public T maximo(){
                Nodo nodito = raiz;
        while (nodito.derecho != null){
            nodito = nodito.derecho;
        }
        return nodito.valor;
    }

    public void insertar(T elem){
        if (raiz == null) {
            raiz = new Nodo(elem, null);
            size++;
        } 
        else {
            Nodo actual = raiz;
            Nodo padre = null;
            while (actual != null) {
                padre = actual;
                if (elem.compareTo(actual.valor) < 0) {
                    actual = actual.izquierdo;
                } 
                else if (elem.compareTo(actual.valor) > 0) {
                    actual = actual.derecho;
                } 
                else {
                    return; 
                }
            }
            if (elem.compareTo(padre.valor) < 0) {
                padre.izquierdo = new Nodo(elem, padre);
            } else {
                padre.derecho = new Nodo(elem, padre);
            }
            size++;
        }
    }

    public boolean pertenece(T elem){
        Nodo actual = raiz;
        while (actual != null) {
            if (elem.compareTo(actual.valor) < 0) {
                actual = actual.izquierdo;
            } else if (elem.compareTo(actual.valor) > 0) {
                actual = actual.derecho;
            } else {
                return true;
            }
        }
        return false;
    }

    public void eliminar(T elem) {
        Nodo nodoAEliminar = buscarNodo(raiz, elem);
    
        if (nodoAEliminar != null) {
            eliminarNodo(nodoAEliminar);
            size--;
        }
    }
    
    public Nodo buscarNodo(Nodo nodo, T elem) {
        while (nodo != null) {
            int comparacion = elem.compareTo(nodo.valor);
            if (comparacion == 0) {
                return nodo;
            } else if (comparacion < 0) {
                nodo = nodo.izquierdo;
            } else {
                nodo = nodo.derecho;
            }
        }
        return null;
    }
    
    public void eliminarNodo(Nodo nodo) {
        if (nodo.izquierdo == null) {
            reemplazarNodo(nodo, nodo.derecho);
        } else if (nodo.derecho == null) {
            reemplazarNodo(nodo, nodo.izquierdo);
        } else {
            Nodo sucesor = encontrarSucesor(nodo.derecho);
            nodo.valor = sucesor.valor;
            eliminarNodo(sucesor);
        }
    }

    public Nodo encontrarSucesor(Nodo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }
    
    public void reemplazarNodo(Nodo nodoViejo, Nodo nodoNuevo) {
        if (nodoViejo.padre == null) {
            raiz = nodoNuevo;
        } else if (nodoViejo == nodoViejo.padre.izquierdo) {
            nodoViejo.padre.izquierdo = nodoNuevo;
        } else {
            nodoViejo.padre.derecho = nodoNuevo;
        }
    
        if (nodoNuevo != null) {
            nodoNuevo.padre = nodoViejo.padre;
        }
    }

    public String toString() {
        if (raiz == null) {
            return "{}";
        }
    
        StringBuilder sb = new StringBuilder("{");
        imprimirInorden(raiz, sb);
        sb.setLength(sb.length() - 1); // Elimina la última coma
        sb.append("}");
        return sb.toString();
    }
    
    public void imprimirInorden(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            imprimirInorden(nodo.izquierdo, sb);
            sb.append(nodo.valor).append(",");
            imprimirInorden(nodo.derecho, sb);
        }
    }
    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        private Stack<Nodo> pila;

        public ABB_Iterador() {
            _actual = raiz;
            pila = new Stack<>();
        }

        public boolean haySiguiente() {
            return _actual != null || !pila.isEmpty();
        }
    
        public T siguiente() {
            while (_actual != null) {
                pila.push(_actual);
                _actual = _actual.izquierdo;
            }

            Nodo siguienteNodo = pila.pop();
            _actual = siguienteNodo.derecho;

            return siguienteNodo.valor;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
