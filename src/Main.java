import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Controlar el flujo del programa, validar entradas del usuario y manejar errores.
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StackFactory<Integer> factory = new StackFactory<>();
        Calculadora calc = Calculadora.getInstance();

        System.out.println("=== Calculadora Postfix ===");
        
        String type = "";
        boolean opcionValida = false;

        while (!opcionValida) {
            System.out.println("\nSeleccione la implementación de Pila:");
            System.out.println("1. ArrayList");
            System.out.println("2. Vector");
            System.out.println("3. Lista (Simple)");
            System.out.println("4. Lista (Doble)");
            System.out.print("Ingrese el número (1-4) o el nombre: ");

            // Leer toda la línea y eliminar espacios en blanco al inicio/final
            String input = scanner.nextLine().trim();

            // Acepta el número O el nombre 
            if (input.equals("1") || input.equalsIgnoreCase("arraylist")) {
                type = "ARRAYLIST";
                opcionValida = true;
            } 
            else if (input.equals("2") || input.equalsIgnoreCase("vector")) {
                type = "VECTOR";
                opcionValida = true;
            } 
            else if (input.equals("3") || input.equalsIgnoreCase("lista simple") || input.equalsIgnoreCase("simple")) {
                type = "SINGLY";
                opcionValida = true;
            } 
            else if (input.equals("4") || input.equalsIgnoreCase("lista doble") || input.equalsIgnoreCase("doble")) {
                type = "DOUBLY";
                opcionValida = true;
            } 
            else {
                // Mensaje de error si no entiende la entrada
                System.out.println("Opción no reconocida, por favor, intente de nuevo.");
            }
        }

        System.out.println("Usando implementación: " + type);

        // Crear la pila usando el Factory
        IStack<Integer> stack = factory.createStack(type);

        // Leer el archivo datos.txt
        try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Saltar líneas vacías
                
                System.out.println("\nEcuación Infix: " + line);
                
                try {
                    // 1. Convertir a Postfix
                    String postfix = calc.infixToPostfix(line);
                    System.out.println("Postfix: " + postfix);
                    
                    // 2. Evaluar
                    int resultado = calc.evaluatePostfix(postfix, stack);
                    System.out.println("Resultado: " + resultado);

                } catch (CalculadoraException e) {
                    System.out.println("Matemático: " + e.getMessage());
                
                } catch (Exception e) {
                    System.out.println("Error Desconocido: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("No se encuentra el archivo 'datos.txt'.");
        }
        
        scanner.close();
    }
}