public class SimulacionBancoSimple {
    public static void simular() {
        // Crear una cola para los turnos
        ColaArreglo turnos = new ColaArreglo(10);

        // Agregamos los clientes en orden
        System.out.println("\n=== Simulación de Turnos en Banco ===");

        // Lista de clientes
        String[] clientes = { "Ana", "Luis", "Marta", "Pedro" };
        System.out.println("Llegan los clientes en orden: " + String.join(", ", clientes));

        // Encolamos los clientes (guardamos la primera letra de cada nombre)
        for (String cliente : clientes) {
            turnos.enqueue((int) cliente.charAt(0));
        }

        // Mostrar estado inicial
        System.out.println("\nCola antes de atender: ");
        System.out.println(mostrarClientes(turnos));

        // Atender a los dos primeros clientes
        System.out.println("\nAtendiendo clientes...");
        char cliente1 = (char) turnos.dequeue();
        char cliente2 = (char) turnos.dequeue();
        System.out.println("Se atendieron a los clientes que empiezan con: " + cliente1 + ", " + cliente2);

        // Mostrar estado final
        System.out.println("\nCola después de atender: ");
        System.out.println(mostrarClientes(turnos));
    }

    // Método auxiliar para mostrar los clientes en la cola
    private static String mostrarClientes(ColaArreglo cola) {
        StringBuilder sb = new StringBuilder("[");
        int size = cola.size();
        ColaArreglo temp = new ColaArreglo(size);

        // Copiar y mostrar elementos
        for (int i = 0; i < size; i++) {
            if (i > 0)
                sb.append(", ");
            int valor = cola.dequeue();
            sb.append((char) valor);
            temp.enqueue(valor);
        }

        // Restaurar la cola original
        for (int i = 0; i < size; i++) {
            cola.enqueue(temp.dequeue());
        }

        sb.append("]");
        return sb.toString();
    }
}