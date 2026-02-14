/**
 * Implementación de una lista enlazada simple.
 * Los nodos solo tienen referencia al siguiente elemento.
 * @param <T> Tipo de dato a almacenar.
 */
public class SinglyLinkedList<T> extends AbstractList<T> {

    private Node<T> head; // Referencia al primer nodo

    public SinglyLinkedList() {
        head = null;
        count = 0; // Inicializa el contador heredado
    }

    // Agrega un elemento al inicio de la lista
    @Override
    public void insertAtStart(T value) {
        Node<T> newNode = new Node<>(value);
        
        // Conecta el nuevo nodo al inicio actual
        newNode.setNext(head);
        
        // Actualiza la cabeza de la lista
        head = newNode;
        
        count++; // Incrementa el tamaño
    }

    // Agrega un elemento al final de la lista
    @Override
    public void insertAtEnd(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            head = newNode;
        } else {
            // Recorre la lista hasta encontrar el ultimo nodo
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            // Conecta el ultimo nodo al nuevo
            current.setNext(newNode);
        }
        count++;
    }

    // Elimina y retorna el primer elemento
    @Override
    public T removeAtStart() {
        if (isEmpty()) return null;

        // Guarda el valor para retornarlo
        T value = head.getValue();
        
        // Mueve la cabeza al siguiente nodo (elimina referencia al primero)
        head = head.getNext();
        
        count--; // Decrementa el tamaño
        return value;
    }

    // Elimina el ultimo elemento
    @Override
    public T removeAtEnd() {
        if (isEmpty()) return null;

        if (count == 1) {
            return removeAtStart();
        }

        // Avanza hasta el penultimo nodo
        Node<T> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }

        // Obtiene el valor del ultimo nodo
        T value = current.getNext().getValue();
        
        // Elimina la referencia al ultimo nodo
        current.setNext(null);
        
        count--;
        return value;
    }

    // Obtiene el elemento en un indice especifico
    @Override
    public T get(int index) {
        if (index < 0 || index >= count) return null;

        Node<T> current = head;
        // Avanza hasta el indice deseado
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }
}