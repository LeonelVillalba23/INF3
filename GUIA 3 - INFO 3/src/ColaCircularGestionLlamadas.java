/*
 * Cola circular simple para gestionar llamadas. Si la cola está llena,
 * al encolar una nueva llamada se sobrescribe la más antigua.
 */
public class ColaCircularGestionLlamadas {
    private String[] datos;
    private int frente;
    private int rear;
    private int count;

    public ColaCircularGestionLlamadas(int capacidad) {
        datos = new String[capacidad];
        frente = 0;
        rear = -1;
        count = 0;
    }

    // Encola sobrescribiendo la más antigua si está llena
    public void enqueueOverwrite(String llamada) {
        if (count == datos.length) {
            // sobrescribir: avanzar frente y rear
            frente = (frente + 1) % datos.length;
            rear = (rear + 1) % datos.length;
            datos[rear] = llamada;
        } else {
            rear = (rear + 1) % datos.length;
            datos[rear] = llamada;
            count++;
        }
    }

    public String dequeue() {
        if (count == 0) return null;
        String val = datos[frente];
        datos[frente] = null;
        frente = (frente + 1) % datos.length;
        count--;
        return val;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == datos.length;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < count; i++) {
            if (i > 0) sb.append(", ");
            sb.append(datos[(frente + i) % datos.length]);
        }
        sb.append("]");
        return sb.toString();
    }

}