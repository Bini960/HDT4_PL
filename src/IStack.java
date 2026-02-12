/**
 * Interfaz que define el comportamiento de una Pila.
 * @param <T> Tipo de dato que guardará la pila (Generico).
 */
public interface IStack<T> {

    // Agrega un elemento al tope de la pila
    void push(T value);

    // Elimina y devuelve el elemento del tope de la pila
    // Puede devolver null si está vacía
    T pop();

    // Devuelve el elemento del tope SIN eliminarlo (solo para ver)
    T peek();

    // Verifica si la pila no tiene elementos
    // Devuelve true si está vacía, false si tiene algo
    boolean isEmpty();

    // Devuelve la cantidad de elementos almacenados
    int size();

}