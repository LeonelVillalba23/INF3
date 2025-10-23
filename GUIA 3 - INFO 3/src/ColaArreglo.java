public class ColaArreglo {
    private int[] datos;
    private int frente;
    private int tamano;

    public ColaArreglo(int capacidad) {
        if (capacidad <= 0)
            capacidad = 10;
        datos = new int[capacidad];
        frente = 0;
        tamano = 0;
    }

    /**
     * enqueue(int dato): añade un elemento al final de la cola.
     * Lanza RuntimeException si la cola está llena.
     */
    public void enqueue(int dato) {
        if (isFull())
            throw new RuntimeException("Cola llena");
        int idx = (frente + tamano) % datos.length;
        datos[idx] = dato;
        tamano++;
    }

    /**
     * dequeue(): elimina y devuelve el elemento del frente.
     * Lanza RuntimeException si la cola está vacía.
     */
    public int dequeue() {
        if (isEmpty())
            throw new RuntimeException("Cola vacía");
        int val = datos[frente];
        frente = (frente + 1) % datos.length;
        tamano--;
        return val;
    }

    /**
     * top(): devuelve el elemento del frente sin quitarlo.
     */
    public int top() {
        if (isEmpty())
            throw new RuntimeException("Cola vacía");
        return datos[frente];
    }

    public boolean isEmpty() {
        return tamano == 0;
    }

    public boolean isFull() {
        return tamano == datos.length;
    }

    public int size() {
        return tamano;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";// indica que la cola esta vacia
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tamano; i++) {
            if (i > 0)
                sb.append(", ");
            sb.append(datos[(frente + i) % datos.length]);
        }

        return sb.toString();
    }
}
