import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InvertirCadenaPila {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingrese una cadena: ");
        String linea = br.readLine();

        if (linea == null) {
            System.out.println("No se ingresó ninguna cadena.");
            return;
        }

        ImplementacionPila pila = new ImplementacionPila(linea.length());

        // Apilar caracteres
        for (int i = 0; i < linea.length(); i++) {
            pila.push((int) linea.charAt(i));
        }

        // Desapilar para invertir
        StringBuilder sb = new StringBuilder();
        while (!pila.isEmpty()) {
            sb.append((char) pila.pop());
        }
        System.out.println("Invertida: " + sb.toString());
    }
}
