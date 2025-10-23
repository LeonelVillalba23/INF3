class ImplementacionPila {
    private int capacidad; // Capacidad máxima de la pila
    private int[] datos;
    private int cima;

    /**
     * Constructor: crea una pila con la capacidad indicada.
     * Si la capacidad pasada es <= 0, se usa 10 por defecto.
     *
     * @param capacidad número máximo de elementos que puede contener la pila
     */
    public ImplementacionPila(int capacidad) {
        if (capacidad <= 0)
            capacidad = 10;
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.cima = -1; // pila vacía
    }

    /**
     * push(int dato): agrega un elemento en la cima de la pila.
     * Lanza RuntimeException si la pila está llena.
     */
    public void push(int dato) {
        if (isFull())
            throw new RuntimeException("Pila llena");
        datos[++cima] = dato;
    }

    /**
     * pop(): elimina y devuelve el elemento de la cima.
     * Lanza RuntimeException si la pila está vacía.
     *
     * @return valor desapilado
     */
    public int pop() {
        if (isEmpty())
            throw new RuntimeException("Pila vacía");
        return datos[cima--];
    }

    /**
     * top(): devuelve el elemento en la cima sin modificar la pila.
     * Lanza RuntimeException si la pila está vacía.
     *
     * @return valor en la cima
     */
    public int top() {
        if (isEmpty())
            throw new RuntimeException("Pila vacía");
        return datos[cima];
    }

    /**
     * isEmpty(): indica si la pila está vacía.
     *
     * @return true si no hay elementos
     */
    public boolean isEmpty() {
        return cima == -1;
    }

    /**
     * isFull(): indica si la pila está llena.
     *
     * @return true si la pila alcanzó su capacidad máxima
     */
    public boolean isFull() {
        return cima == capacidad - 1;
    }

    /**
     * size(): devuelve la cantidad de elementos actualmente en la pila.
     *
     * @return número de elementos
     */
    public int size() {
        return cima + 1;
    }

    /**
     * toString(): representación en texto de la pila (de fondo a cima).
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "[]";//indica que la pila esta vacia
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= cima; i++) {
            sb.append(datos[i]);
            if (i < cima)
                sb.append(", ");
        }

        return sb.toString();
    }
}