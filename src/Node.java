/**
 * Representa un elemento individual de la lista que guarda un dato
 * y conoce a los datos siguientes y anteriores.
 */
public class Node<T> {

    private T value;      // Dato almacenado en el nodo
    private Node<T> next; // Referencia al nodo siguiente
    private Node<T> prev; // Referencia al nodo anterior (Exclusivo para Doubly)

    // Constructor: Inicializa un nodo aislado
    public Node(T value) {
        this.value = value;
        // Inicializa las referencias en null para evitar conexiones basura
        this.next = null; 
        this.prev = null; 
    }


    // Retorna el dato guardado en el nodo
    public T getValue() {
        return value;
    }

    // Sobreescribe el dato del nodo 
    public void setValue(T value) {
        this.value = value;
    }

    // Retorna el nodo que esta adelante en la lista
    public Node<T> getNext() {
        return next;
    }

    // Conecta este nodo con el siguiente
    // Usado para crear la cadena hacia adelante (A a B)
    public void setNext(Node<T> next) {
        this.next = next;
    }

    // Retorna el nodo que esta detras en la lista
    public Node<T> getPrev() {
        return prev;
    }

    // Conecta este nodo con el anterior
    // Usado para crear la cadena hacia atras (B < A) en listas dobles
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}