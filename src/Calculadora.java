/**
 * Clase Calculadora (Singleton).
 * Contiene la lógica para convertir expresiones y evaluarlas.
 * No guarda estado (memoria) de las operaciones, solo procesa.
 */
public class Calculadora {

    // La unica instancia que existira de esta clase
    private static Calculadora instance;

    // Constructor privado para evitar instanciacion externa
    private Calculadora() {
    }

    // Obtener la unica instancia 
    public static Calculadora getInstance() {
        if (instance == null) {
            instance = new Calculadora();
        }
        return instance;
    }

    /**
     * Convertir una expresion Infix (A + B) a Postfix (A B +).
     * @param infix La expresion original.
     * @return La expresion en postfix.
     */
    public String infixToPostfix(String infix) throws Exception {
        StringBuilder postfix = new StringBuilder();
        IStack<Character> stack = new StackVector<>(); // Usar Vector temporalmente para ordenamiento

        // Recorrer la cadena caracter por caracter
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // Si es numero o letra, agregar directo a la salida
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } 
            // Si es parentesis de apertura, guardar en la pila
            else if (c == '(') {
                stack.push(c);
            } 
            // Si es parentesis de cierre, extraer todo hasta encontrar el de apertura
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop(); // Eliminar el '(' de la pila
            } 
            // Si es un operador, manejar precedencia
            else if (isOperator(c)) {
                // Extraer operadores de la pila con mayor o igual precedencia
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Vaciar los operadores restantes en la pila
        while (!stack.isEmpty()) {
            if(stack.peek() == '(') throw new Exception("Error: Parentesis sin cerrar");
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    /**
     * Evaluar una expresion Postfix y devolver el resultado.
     * @param postfix La expresion en formato postfix.
     * @param stack La pila seleccionada por el usuario (Factory).
     * @return El resultado de la operacion.
     */
    public int evaluatePostfix(String postfix, IStack<Integer> stack) throws Exception {
        
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            // Si es digito, convertir y guardar en la pila
            if (Character.isDigit(c)) {
                stack.push(c - '0'); // Convertir char a int
            } 
            // Si es operador, extraer dos operandos y calcular
            else if (isOperator(c)) {
                if (stack.size() < 2) {
                    throw new Exception("Faltan operandos para el operador " + c);
                }
                
                int valB = stack.pop(); // Operando derecho
                int valA = stack.pop(); // Operando izquierdo

                switch (c) {
                    case '+': stack.push(valA + valB); break;
                    case '-': stack.push(valA - valB); break;
                    case '*': stack.push(valA * valB); break;
                    case '/': 
                        if (valB == 0) throw new Exception("Division por cero");
                        stack.push(valA / valB); 
                        break;
                    case '^': stack.push((int) Math.pow(valA, valB)); break;
                }
            }
        }
        
        if (stack.size() != 1) {
             throw new Exception("Expresion mal formada");
        }

        return stack.pop();
    }

    // Definir jerarquia de operadores
    private int precedence(char c) {
        switch (c) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    // Verificar si es operador valido
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
}