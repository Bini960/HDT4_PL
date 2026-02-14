import java.util.Vector;

/**
 * Implementación de Pila usando Vector.
 * @param <T> Tipo de dato a almacenar.
 */
public class StackVector<T> extends AbstractStack<T> {

    private Vector<T> internalVector; // Almacen interno

    public StackVector() {
        internalVector = new Vector<>();
    }

    // Agrega el elemento al vector
    @Override
    public void push(T value) {
        internalVector.add(value);
        count++;
    }

    // Elimina y devuelve el ultimo elemento
    @Override
    public T pop() {
        if (isEmpty()) return null;
        
        // Vector tiene un metodo util para obtener el ultimo
        T value = internalVector.lastElement();
        
        internalVector.remove(internalVector.size() - 1);
        
        count--;
        return value;
    }

    // Muestra el tope de la pila
    @Override
    public T peek() {
        if (isEmpty()) return null;
        return internalVector.lastElement();
    }
}