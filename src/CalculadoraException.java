/**
 * Clase de Excepción Personalizada.
 * Agrupar los posibles errores lógicos de la calculadora.
 * Contiene subclases estáticas para errores específicos.
 */
public class CalculadoraException extends Exception {
    
    // Constructor Padre: Pasar el mensaje a la clase Exception de Java
    public CalculadoraException(String mensaje) {
        super(mensaje);
    }

    // --- Subclases para errores específicos ---

    // Error: Cuando se intenta dividir un número dentro de cero
    static class DivisionPorCeroException extends CalculadoraException {
        public DivisionPorCeroException() {
            super("Error: División por cero no permitida");
        }
    }

    // Error: Cuando faltan números para realizar una operación (ej: "5 +")
    static class OperandosInsuficientesException extends CalculadoraException {
        public OperandosInsuficientesException() {
            super("Error: Operandos insuficientes para realizar el cálculo");
        }
    }

    // Error: Cuando se encuentra un símbolo desconocido (ej: &, $, #)
    static class CaracterInvalidoException extends CalculadoraException {
        public CaracterInvalidoException(String caracter) {
            super("Error: Caracter inválido detectado -> '" + caracter + "'");
        }
    }
}