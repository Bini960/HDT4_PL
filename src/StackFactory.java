/**
 * Se encarga de instanciar la Pila correcta según la elección del usuario.
 * @param <T> Tipo de dato a almacenar.
 */
public class StackFactory<T> {

    // Crea y devuelve una instancia de IStack segun el tipo solicitado
    public IStack<T> createStack(String type) {
        
        // Normaliza el texto de entrada
        String stackType = type.toUpperCase();

        switch (stackType) {
            case "ARRAYLIST":
                // Retorna una pila basada en ArrayList 
                return new StackArrayList<>();

            case "VECTOR":
                // Retorna una pila basada en Vector 
                return new StackVector<>();

            case "SINGLY":
                // Retorna una pila que usa internamente una Lista Simple
                return new StackList<>(new SinglyLinkedList<>());

            case "DOUBLY":
                // Retorna una pila que usa internamente una Lista Doble
                return new StackList<>(new DoublyLinkedList<>());

            default:
                // Retorna null si el tipo no es reconocido
                return null;
        }
    }
}