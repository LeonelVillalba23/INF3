import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Comprueba si una palabra o frase es palíndromo usando una pila y una cola.
 */
public class PalindromoPilaCola {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingrese una palabra o frase: ");

        String linea = br.readLine();

        if (linea == null) return;
        
        // Versión simple: quitar espacios y pasar a minúsculas
        String s = linea.replaceAll("\\s+", "").toLowerCase();
        int n = s.length();
        ImplementacionPila pila = new ImplementacionPila(n);
        ColaArreglo cola = new ColaArreglo(n);

        // Llenar pila y cola
        for (char c : s.toCharArray()) {
            pila.push((int) c);
            cola.enqueue((int) c);
        }

        // Comparar desapilando y desencolando
        boolean esPal = true;
        while (!pila.isEmpty() && !cola.isEmpty()) {
            if (pila.pop() != cola.dequeue()) {
                esPal = false;
                break;
            }
        }

        System.out.println("Resultado: " + (esPal ? "es palíndromo" : "no es palíndromo"));
    }
}
