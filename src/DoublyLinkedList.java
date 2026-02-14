/**
 * Implementación de una lista doblemente enlazada.
 * Los nodos tienen referencias al siguiente y al anterior.
 * Incluye búsqueda por mitades.
 * @param <T> Tipo de dato a almacenar.
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    private Node<T> head; // Referencia al primer nodo
    private Node<T> tail; // Referencia al ultimo nodo

    public DoublyLinkedList() {
        head = null;
        tail = null;
        count = 0; // Heredado de AbstractList
    }

    // Agrega un elemento al inicio de la lista
    @Override
    public void insertAtStart(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            // Asigna el nuevo nodo como cabeza y cola si la lista esta vacia
            head = newNode;
            tail = newNode;
        } else {
            // Conecta el nuevo nodo con el actual head
            newNode.setNext(head);
            head.setPrev(newNode); 
            // Actualiza la cabeza al nuevo nodo
            head = newNode;        
        }
        count++;
    }

    // Agrega un elemento al final de la lista (O(1))
    @Override
    public void insertAtEnd(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            // Conecta el final actual con el nuevo nodo
            tail.setNext(newNode);
            newNode.setPrev(tail);
            // Actualiza la cola al nuevo nodo
            tail = newNode;        
        }
        count++;
    }

    // Elimina y devuelve el primer elemento
    @Override
    public T removeAtStart() {
        if (isEmpty()) return null;

        T value = head.getValue();

        if (head == tail) { 
            // Limpia referencias si solo habia un elemento
            head = null;
            tail = null;
        } else {
            // Mueve la cabeza al siguiente nodo
            head = head.getNext(); 
            // Corta la referencia hacia atras del nuevo head
            head.setPrev(null);    
        }

        count--;
        return value;
    }

    // Elimina el ultimo elemento
    @Override
    public T removeAtEnd() {
        if (isEmpty()) return null;

        T value = tail.getValue();

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            // Mueve la cola al penultimo nodo
            tail = tail.getPrev(); 
            // Corta la referencia hacia adelante de la nueva cola
            tail.setNext(null);    
        }

        count--;
        return value;
    }

    // Obtiene el elemento en una posicion especifica 
    @Override
    public T get(int index) {
        // Verifica si el indice esta fuera de rango
        if (index < 0 || index >= count) return null;

        Node<T> current;

        // Decide si buscar desde el inicio o desde el final
        if (index < count / 2) {
            // Inicia la busqueda desde la cabeza (Primera mitad)
            current = head;
            for (int i = 0; i < index; i++) {
                // Avanza al siguiente nodo
                current = current.getNext();
            }
        } else {
            // Inicia la busqueda desde la cola (Segunda mitad)
            current = tail;
            // Calcula cuantos pasos retroceder
            int stepsBack = count - 1 - index;
            for (int i = 0; i < stepsBack; i++) {
                // Retrocede al nodo anterior
                current = current.getPrev();
            }
        }

        return current.getValue();
    }
}