package src;

/**
 * Cola circular para representar la sala de espera.
 * Comportamiento: capacidad fija N; cuando llega un nuevo paciente y la sala
 * está llena, pisa al más antiguo (overflow control).
 */
public class SalaEspera {
    private final String[] buffer;
    private final int capacidad;
    private int front; // índice del siguiente a atender
    private int rear; // índice donde se insertará el próximo
    private int size;

    public SalaEspera(int capacidad) {
        if (capacidad <= 0)
            throw new IllegalArgumentException("Capacidad debe ser > 0");
        this.capacidad = capacidad;
        this.buffer = new String[capacidad];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // Llega un paciente (dni). Si la sala está llena, se sobrescribe el más
    // antiguo.
    public void llega(String dni) {
        if (dni == null)
            throw new IllegalArgumentException("dni no puede ser null");
        if (size == capacidad) {
            // sobrescribir el más antiguo: avanzamos front y colocamos en rear
            buffer[rear] = dni;
            front = (front + 1) % capacidad;
            rear = (rear + 1) % capacidad;
            // size permanece en capacidad
        } else {
            buffer[rear] = dni;
            rear = (rear + 1) % capacidad;
            size++;
        }
    }

    // Atiende (desencola) y devuelve el dni atendido. Retorna null si está vacía.
    public String atiende() {
        if (size == 0)
            return null;
        String dni = buffer[front];
        buffer[front] = null;
        front = (front + 1) % capacidad;
        size--;
        return dni;
    }

    // Devuelve el siguiente a atender sin quitarlo. Null si vacía.
    public String peek() {
        if (size == 0)
            return null;
        return buffer[front];
    }

    public int size() {
        return size;
    }

    public int capacidad() {
        return capacidad;
    }

    // Representación compacta del estado FRONT -> [...] <- REAR
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FRONT -> [");
        for (int i = 0; i < capacidad; i++) {
            if (i > 0)
                sb.append(", ");
            String v = buffer[(front + i) % capacidad];
            sb.append(v == null ? "_" : v);
        }
        sb.append("] <- REAR");
        sb.append("  (tamaño=").append(size).append(" / ").append(capacidad).append(")");
        return sb.toString();
    }
}
