//Convertir expresiones infix a postfix y evaluarlas.

public class Calculadora {

    private static Calculadora instance;

    // Constructor privado para patrón Singleton
    private Calculadora() {
    }

    // Obtener la única instancia disponible
    public static Calculadora getInstance() {
        if (instance == null) {
            instance = new Calculadora();
        }
        return instance;
    }

    //Traduce notación Infix (humana) a Postfix (máquina).
    public String infixToPostfix(String infix) throws CalculadoraException {
        StringBuilder postfix = new StringBuilder();
        IStack<Character> stack = new StackVector<>(); // Pila temporal para operadores

        // Recorrer la expresión carácter por carácter
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // Ignorar espacios en blanco
            if (c == ' ') continue;

            // Caso 1: Es un número
            if (Character.isDigit(c)) {
                postfix.append(c);
            } 
            // Caso 2: Paréntesis de apertura
            else if (c == '(') {
                stack.push(c);
            } 
            // Caso 3: Paréntesis de cierre
            else if (c == ')') {
                // Sacar elementos hasta encontrar el paréntesis de apertura
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop(); // Eliminar el '('
            } 
            // Caso 4: Operadores (+, -, *, /)
            else if (isOperator(c)) {
                // Mover operadores de mayor prioridad de la pila a la salida
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            } 
            // Caso 5: Carácter desconocido
            else {
                // Lanzar excepción específica de caracter inválido
                throw new CalculadoraException.CaracterInvalidoException(String.valueOf(c));
            }
        }

        // Vaciar la pila de operadores restantes
        while (!stack.isEmpty()) {
            if(stack.peek() == '(') {
                throw new CalculadoraException("Error: Paréntesis desbalanceados"); 
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    /**
     * Evaluar la expresión Postfix.
     * Realizar el cálculo matemático final usando la Pila seleccionada.
     */
    public int evaluatePostfix(String postfix, IStack<Integer> stack) throws CalculadoraException {
        
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            // Si es dígito, convertir a int y apilar
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } 
            // Si es operador, desapilar dos números y operar
            else if (isOperator(c)) {
                // Verificar que existan al menos dos números en la pila
                if (stack.size() < 2) {
                    throw new CalculadoraException.OperandosInsuficientesException();
                }
                
                int valB = stack.pop(); // Operando derecho
                int valA = stack.pop(); // Operando izquierdo

                switch (c) {
                    case '+': stack.push(valA + valB); break;
                    case '-': stack.push(valA - valB); break;
                    case '*': stack.push(valA * valB); break;
                    case '/': 
                        // Verificar división por cero antes de operar
                        if (valB == 0) throw new CalculadoraException.DivisionPorCeroException();
                        stack.push(valA / valB); 
                        break;
                    case '^': stack.push((int) Math.pow(valA, valB)); break;
                }
            }
        }
        
        // Verificar que al final solo quede el resultado
        if (stack.size() != 1) {
             throw new CalculadoraException.OperandosInsuficientesException();
        }

        return stack.pop();
    }

    // Definir jerarquía de operaciones
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

    // Validar si el caracter es un operador matemático
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
}