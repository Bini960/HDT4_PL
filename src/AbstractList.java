//Maneja el tamaño de la lista para no tener que recorrerla cada vez que pidan el size.
public abstract class AbstractList<T> implements IList<T> {

    // Contador de elementos de la lista 
    protected int count;

    // Constructor vacio
    public AbstractList() {
        this.count = 0;
    }

    // Verifica si la lista esta vacia mirando el contador
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    // Devuelve cuantos nodos tiene la lista actualmente
    @Override
    public int size() {
        return count;
    }

    // Los metodos de insertar y eliminar (insertAtStart, removeAtStart, etc.) se dejan pendientes para que la Lista Simple y la Doble los hagan a su manera.
}