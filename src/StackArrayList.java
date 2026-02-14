import java.util.ArrayList;

/**
 * Implementación de Pila usando ArrayList.
 * Hereda de AbstractStack para manejar el conteo.
 * @param <T> Tipo de dato a almacenar.
 */
public class StackArrayList<T> extends AbstractStack<T> {

    private ArrayList<T> internalList; // Almacen interno

    public StackArrayList() {
        internalList = new ArrayList<>();
        // count se inicializa en 0 en el constructor del padre
    }

    // Agrega un elemento al final del ArrayList 
    @Override
    public void push(T value) {
        internalList.add(value);
        count++; // Actualiza el contador del padre
    }

    // Elimina y devuelve el ultimo elemento
    @Override
    public T pop() {
        if (isEmpty()) return null;
        
        // Obtiene el ultimo elemento
        T value = internalList.get(internalList.size() - 1);
        
        // Lo elimina de la lista
        internalList.remove(internalList.size() - 1);
        
        count--; // Actualiza el contador
        return value;
    }

    // Solo muestra el ultimo elemento sin borrarlo
    @Override
    public T peek() {
        if (isEmpty()) return null;
        return internalList.get(internalList.size() - 1);
    }
}