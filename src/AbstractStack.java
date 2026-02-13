//Se encarga de manejar el contador de elementos (count).
public abstract class AbstractStack<T> implements IStack<T> {

    // Variable protegida para llevar el conteo de elementos
    // Es protected para que las clases hijas puedan modificarla
    protected int count;

    // Constructor: Inicializa el contador en cero
    public AbstractStack() {
        this.count = 0;
    }

    // Metodo para saber si la pila esta vacia
    // Reutiliza la variable count para no depender de la estructura interna
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    // Metodo para obtener el tamaño de la pila
    @Override
    public int size() {
        return count;
    }

    // Como esta clase es abstracta, obliga a las hijas implementar los metodos push, pop y peek 
}