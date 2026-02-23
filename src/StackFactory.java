public class StackFactory<T> {

    // Crear y devolver una instancia de IStack recibiendo dos parámetros (Según diseño UML)
    public IStack<T> createStack(String type, String listType) {
        
        // Normalizar el texto de entrada principal a mayúsculas
        String stackType = type.toUpperCase();

        switch (stackType) {
            case "ARRAYLIST":
                // Retornar una pila basada en ArrayList 
                return new StackArrayList<>();

            case "VECTOR":
                // Retornar una pila basada en Vector 
                return new StackVector<>();

            case "LISTA":
                // Aplicar el patrón Factory nuevamente para decidir la implementación de la lista
                // Normalizar el texto del tipo de lista (prevenir errores si el valor es nulo)
                String tipoLista = (listType != null) ? listType.toUpperCase() : "SIMPLE";
                
                if (tipoLista.equals("DOBLE") || tipoLista.equals("DOUBLY")) {
                    // Retornar una pila que usa internamente una Lista Doble
                    return new StackList<>(new DoublyLinkedList<>());
                } else {
                    // Retornar una pila que usa internamente una Lista Simple por defecto
                    return new StackList<>(new SinglyLinkedList<>());
                }

            default:
                // Retornar null si el tipo principal no es reconocido
                return null;
        }
    }
}