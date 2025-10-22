public class MinHeap {
    private int[] heap;
    private int size;

    public MinHeap() {
        this(10);
    }

    public MinHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // Constructor heapify bottom-up
    public MinHeap(int[] datos) {
        heap = new int[datos.length];
        size = datos.length;
        System.arraycopy(datos, 0, heap, 0, datos.length);

        System.out.println("\n=== Proceso de heapify (bottom-up) ===");
        for (int i = (size / 2) - 1; i >= 0; i--) {
            System.out.println("Reajustando desde índice " + i + " (valor " + heap[i] + "):");
            percolateDown(i);
            printArray();
        }
        System.out.println("Heap final construido:");
        printArray();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return heap[0];
    }

    // Insertar con percolate up (muestra estado)
    public void add(int valor) {
        ensureCapacity();
        heap[size] = valor;
        int i = size;
        size++;

        System.out.println("\nInsertando " + valor + "...");
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[parent] <= heap[i]) break;
            System.out.println("  Intercambio (up): " + heap[parent] + " ↔ " + heap[i]);
            swap(parent, i);
            i = parent;
            printArray();
        }

        System.out.println("Insertado " + valor + ", estado interno:");
        printArray();
    }

    public int poll() {
        if (isEmpty()) {
            System.out.println("Heap vacío, no se puede extraer.");
            return Integer.MIN_VALUE;
        }

        int min = heap[0];
        System.out.println("\nExtrayendo el mínimo: " + min);

        heap[0] = heap[size - 1];
        size--;

        System.out.println("  Reemplazando raíz con último elemento:");
        printArray();

        percolateDown(0);

        System.out.println("Eliminado " + min + ", estado interno:");
        printArray();

        return min;
    }

    // percolateDown con impresión de intercambios
    private void percolateDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && heap[left] < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;

            if (smallest == i) break;

            System.out.println("  Intercambio (down): " + heap[i] + " ↔ " + heap[smallest]);
            swap(i, smallest);
            i = smallest;
            printArray();
        }
    }

    private void ensureCapacity() {
        if (size >= heap.length) {
            int[] nuevo = new int[heap.length * 2];
            System.arraycopy(heap, 0, nuevo, 0, heap.length);
            heap = nuevo;
        }
    }

    private void swap(int a, int b) {
        int tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    public void printArray() {
        System.out.print("  [");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Muestra el heap por niveles (forma de árbol)
    public void printTree() {
        if (isEmpty()) {
            System.out.println("(heap vacío)");
            return;
        }

        int nivel = 0;
        int cantidadEnNivel = 1;
        int i = 0;

        while (i < size) {
            for (int j = 0; j < cantidadEnNivel && i < size; j++, i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
            nivel++;
            cantidadEnNivel *= 2;
        }
    }
}
