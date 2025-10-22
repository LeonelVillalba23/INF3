public class MaxHeap {
    private int[] heap;
    private int size;

    public MaxHeap() {
        this(10);
    }

    public MaxHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int valor) {
        ensureCapacity();
        heap[size] = valor;
        int i = size;
        size++;

        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[parent] >= heap[i]) break;
            swap(parent, i);
            i = parent;
        }
    }

    public int poll() {
        if (isEmpty()) return Integer.MIN_VALUE;
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        percolateDown(0);
        return max;
    }

    private void percolateDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            if (left < size && heap[left] > heap[largest]) largest = left;
            if (right < size && heap[right] > heap[largest]) largest = right;

            if (largest == i) break;
            swap(i, largest);
            i = largest;
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
}
