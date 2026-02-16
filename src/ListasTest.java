import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

/**
 * Pruebas unitarias para las estructuras de datos.
 * Verificar el correcto funcionamiento de Listas y Pilas.
 */
public class ListasTest {

    // --- PRUEBA 1: Lista Simple ---
    @Test
    public void testSinglyLinkedList() {
        IList<Integer> lista = new SinglyLinkedList<>();
        
        // Insertar elementos al inicio (Simular comportamiento de Pila)
        lista.insertAtStart(10);
        lista.insertAtStart(20); 
        
        // Verificar que el tamaño sea correcto
        assertEquals(2, lista.size());
        
        // Verificar que el último insertado sea el primero (LIFO)
        assertEquals((Integer)20, lista.get(0)); 
        assertEquals((Integer)10, lista.get(1));
        
        // Eliminar el primer elemento y verificar el valor retornado
        Integer eliminado = lista.removeAtStart(); 
        
        // Asegurar que se eliminó el elemento correcto
        assertEquals((Integer)20, eliminado);
        
        // Verificar el nuevo tamaño y la nueva cabeza de la lista
        assertEquals(1, lista.size());
        assertEquals((Integer)10, lista.get(0));
    }

    // PRUEBA 2: Lista Doble 
    @Test
    public void testDoublyLinkedList() {
        IList<String> lista = new DoublyLinkedList<>();
        
        // Insertar al inicio
        lista.insertAtStart("A");
        lista.insertAtStart("B");
        
        // Insertar al final para probar los punteros traseros
        lista.insertAtEnd("C");   
        
        // Verificar integridad de la lista (B -> A -> C)
        assertEquals(3, lista.size());
        assertEquals("B", lista.get(0));
        assertEquals("C", lista.get(2));
        
        // Eliminar del inicio
        String eliminado = lista.removeAtStart();
        
        // Verificar que se eliminó la cabeza correcta
        assertEquals("B", eliminado);
        
        // Verificar que el resto de la lista sigue intacta
        assertEquals(2, lista.size());
        assertEquals("A", lista.get(0));
    }

    // PRUEBA 3: Stack con Listas 
    @Test
    public void testStackConListas() {
        // Instanciar el Stack usando el adaptador y la Lista Simple
        IStack<Integer> pila = new StackList<>(new SinglyLinkedList<>());
        
        // Apilar elementos
        pila.push(100);
        pila.push(200);
        
        // Verificar el tamaño de la pila usando size()
        assertEquals(2, pila.size());
        
        // Desapilar y verificar el orden LIFO 
        assertEquals((Integer)200, pila.pop());
        assertEquals((Integer)100, pila.pop());
        
        // Verificar que la pila quede vacía
        assertTrue(pila.isEmpty());
    }
}