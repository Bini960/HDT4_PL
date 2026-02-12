/**
 * Interfaz que define el comportamiento de una Lista.
 * Usada por StackList para el almacenamiento.
 * @param <T> Tipo de dato a almacenar.
 */
public interface IList<T> {

    // Inserta un elemento al inicio de la lista (Equivalente al push de la Pila)
    void insertAtStart(T value);

    // Inserta un elemento al final de la lista (Necesario para listas dobles o colas)
    void insertAtEnd(T value);

    // Elimina y devuelve el primer elemento de la lista Equivalente al pop de la Pila)
    T removeAtStart();

    // Elimina y devuelve el último elemento
    T removeAtEnd();

    // Devuelve el elemento en una posición específica (índice)
    // Útil para recorrer la lista o pruebas
    T get(int index);

    // Verifica si la lista está vacía
    boolean isEmpty();

    // Devuelve el número de elementos en la lista
    int size();

}