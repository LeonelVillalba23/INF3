/**
 * Implementación de una cola de impresora usando un arreglo circular.
 * Esta implementación muestra cómo funciona una cola internamente.
 */
public class SimulacionImpresoraCola {
    private String[] documentos; // Arreglo para almacenar los documentos
    private int frente; // Índice del frente de la cola
    private int final_; // Índice del final de la cola
    private int tamano; // Tamaño actual de la cola
    private int capacidad; // Capacidad máxima de la cola

    /**
     * Constructor que inicializa la cola con una capacidad específica.
     * 
     * @param capacidad Número máximo de documentos que puede contener la cola
     */
    public SimulacionImpresoraCola(int capacidad) {
        this.capacidad = capacidad;
        documentos = new String[capacidad];
        frente = 0;
        final_ = -1;
        tamano = 0;
    }

    /**
     * Agrega un documento al final de la cola.
     * 
     * @param documento Nombre o descripción del documento a imprimir
     * @return true si se pudo agregar, false si la cola está llena
     */
    public boolean enqueue(String documento) {
        if (tamano == capacidad) {
            return false; // Cola llena
        }

        final_ = (final_ + 1) % capacidad; // Avanza circularmente
        documentos[final_] = documento;
        tamano++;
        return true;
    }

    /**
     * Retira y devuelve el documento del frente de la cola.
     * 
     * @return El documento a imprimir, o null si la cola está vacía
     */
    public String dequeue() {
        if (isEmpty()) {
            return null;
        }

        String documento = documentos[frente];
        documentos[frente] = null; // Limpia la referencia
        frente = (frente + 1) % capacidad; // Avanza circularmente
        tamano--;
        return documento;
    }

    /**
     * Muestra el próximo documento a imprimir sin retirarlo.
     * 
     * @return El próximo documento, o null si la cola está vacía
     */
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return documentos[frente];
    }

    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si no hay documentos, false en caso contrario
     */
    public boolean isEmpty() {
        return tamano == 0;
    }

    /**
     * Verifica si la cola está llena.
     * 
     * @return true si no se pueden agregar más documentos, false en caso contrario
     */
    public boolean isFull() {
        return tamano == capacidad;
    }

    /**
     * Obtiene el número actual de documentos en la cola.
     * 
     * @return Cantidad de documentos en la cola
     */
    public int getTamano() {
        return tamano;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Cola vacía";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[ ");

        int count = 0;
        int index = frente;
        while (count < tamano) {
            if (count > 0) {
                sb.append(", ");
            }
            sb.append(documentos[index]);
            index = (index + 1) % capacidad;
            count++;
        }

        sb.append(" ]");
        return sb.toString();
    }
}
