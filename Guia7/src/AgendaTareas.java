import java.util.ArrayList;

public class AgendaTareas {
    private ArrayList<Tarea> heap = new ArrayList<>();

    public void agregar(Tarea t) {
        heap.add(t);
        percolateUp(heap.size() - 1);
        System.out.println("Tarea agregada: " + t);
    }

    public Tarea verProxima() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    public Tarea completar() {
        if (heap.isEmpty()) return null;
        Tarea min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        percolateDown(0);
        return min;
    }

    public void mostrarPendientes() {
        System.out.println("\nTareas pendientes (orden interno del heap):");
        for (Tarea t : heap) System.out.println(" - " + t);
    }

    private void percolateUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(parent).prioridad <= heap.get(i).prioridad) break;
            swap(parent, i);
            i = parent;
        }
    }

    private void percolateDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;
            if (left < heap.size() && heap.get(left).prioridad < heap.get(smallest).prioridad)
                smallest = left;
            if (right < heap.size() && heap.get(right).prioridad < heap.get(smallest).prioridad)
                smallest = right;
            if (smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int a, int b) {
        Tarea temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    public boolean estaVacia() {
        return heap.isEmpty();
    }
}
