import java.util.LinkedList;
import java.util.Queue;

// Simulación sencilla de turnos en un banco usando Queue
public class SimulacionTurnosCola {
    public static void main(String[] args) {
        Queue<String> fila = new LinkedList<>();
        // Llegan en orden
        fila.add("Ana");
        fila.add("Luis");
        fila.add("Marta");
        fila.add("Pedro");

        System.out.println("Cola antes de atender: " + fila);

        // Atendemos a los dos primeros
        String atendido1 = fila.remove();
        String atendido2 = fila.remove();

        System.out.println("Atendidos: " + atendido1 + ", " + atendido2);
        System.out.println("Cola después de atender dos clientes: " + fila);
    }
}
