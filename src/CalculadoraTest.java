import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CalculadoraTest {

    // Instancias para probar
    private Calculadora calc = Calculadora.getInstance();
    private StackFactory<Integer> factory = new StackFactory<>();

    // TEST 1: Probando el Singleton (única instancia)
    @Test
    public void testSingleton() {
        Calculadora calc1 = Calculadora.getInstance();
        Calculadora calc2 = Calculadora.getInstance();
        
        // Verifica que ambas variables apunten exactamente al mismo objeto en memoria
        assertTrue("El Singleton debería devolver la misma instancia", calc1 == calc2);
    }

    // Probando Conversión Infix a Postfix
    @Test
    public void testInfixToPostfix() {
        try {
            // Caso simple
            assertEquals("12+", calc.infixToPostfix("1+2"));
            
            // Caso con multiplicación antes que suma
            assertEquals("123*+", calc.infixToPostfix("1+2*3"));
            
            // Caso con paréntesis
            assertEquals("12+3*", calc.infixToPostfix("(1+2)*3"));
            
        } catch (Exception e) {
            fail("No debería fallar la conversión: " + e.getMessage());
        }
    }

    // TEST 3: Probando Evaluación de Resultados 
    @Test
    public void testEvaluatePostfix() {
        try {
            // Usamos una pila de ArrayList para la prueba
            IStack<Integer> stack = new StackArrayList<>();
            
            // 1 + 2 = 3
            assertEquals(3, calc.evaluatePostfix("12+", stack));
            
            // (1 + 2) * 9 = 27
            assertEquals(27, calc.evaluatePostfix("12+9*", stack));
            
        } catch (Exception e) {
            fail("El cálculo debería ser correcto: " + e.getMessage());
        }
    }

    // TEST 4: Probando el Factory 
    @Test
    public void testFactory() {
        // Verifica que si pido VECTOR, me de un objeto tipo StackVector
        IStack<Integer> stackVector = factory.createStack("VECTOR");
        assertTrue("Debería ser instancia de StackVector", stackVector instanceof StackVector);

        // Verifica que si pido ARRAYLIST, me de un objeto tipo StackArrayList
        IStack<Integer> stackArray = factory.createStack("ARRAYLIST");
        assertTrue("Debería ser instancia de StackArrayList", stackArray instanceof StackArrayList);
    }

    // TEST 5: Probando Excepciones (División por Cero) 
    @Test
    public void testDivisionPorCero() {
        try {
            IStack<Integer> stack = new StackVector<>();
            calc.evaluatePostfix("50/", stack); // 5 / 0
            
            // Si llega aquí falló la prueba porque NO lanzó error
            fail("Debería haber lanzado una excepción de División por Cero");
            
        } catch (CalculadoraException.DivisionPorCeroException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Lanzó un error incorrecto: " + e.getClass().getSimpleName());
        }
    }
    
    // TEST 6: Probando Operaciones de Pila 
    @Test
    public void testStackOperations() {
        IStack<Integer> pila = new StackArrayList<>();
        
        assertTrue(pila.isEmpty()); // Debe crearse vacía
        
        pila.push(10);
        pila.push(20);
        
        assertEquals(2, pila.size()); // Debe tener 2 elementos
        assertEquals((Integer)20, pila.peek()); // El tope debe ser 20
        assertEquals((Integer)20, pila.pop());  // Sacamos el 20
        assertEquals((Integer)10, pila.peek()); // Ahora el tope es 10
    }
}