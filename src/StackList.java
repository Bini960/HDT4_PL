/**
 * Adapta la interfaz IList para comportarse como IStack.
 * @param <T> Tipo de dato a almacenar.
 */
public class StackList<T> extends AbstractStack<T> {

    private IList<T> internalList; // Puede ser Singly o Doubly

    // Constructor: Recibe la lista especifica que se usara
    public StackList(IList<T> listImplementation) {
        this.internalList = listImplementation;
    }

    // Empuja un elemento a la pila (Inserta al inicio de la lista)
    @Override
    public void push(T value) {
        internalList.insertAtStart(value);
        count++;
    }

    // Saca un elemento de la pila (Elimina del inicio de la lista)
    @Override
    public T pop() {
        if (isEmpty()) return null;
        
        T value = internalList.removeAtStart();
        count--;
        return value;
    }

    // Mira el tope de la pila (Obtiene el primer elemento)
    @Override
    public T peek() {
        if (isEmpty()) return null;
        // El tope de la pila es el inicio de la lista (Index 0)
        return internalList.get(0);
    }
}