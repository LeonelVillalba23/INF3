package src;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Planner con heap indexado para operar en O(log n) push/pop/reprogramar.
 */
public class Planner {
    private final List<Recordatorio> heap = new ArrayList<>();
    private final Map<String, Integer> indexMap = new HashMap<>();

    public Planner() {
    }

    public int size() {
        return heap.size();
    }

    public void programar(Recordatorio r) {
        if (r == null || r.id == null)
            throw new IllegalArgumentException("Recordatorio inválido");
        if (indexMap.containsKey(r.id))
            throw new IllegalArgumentException("ID ya existe: " + r.id);
        heap.add(r);
        int i = heap.size() - 1;
        indexMap.put(r.id, i);
        siftUp(i);
    }

    public Recordatorio proximo() {
        if (heap.isEmpty())
            return null;
        Recordatorio res = heap.get(0);
        int last = heap.size() - 1;
        if (last == 0) {
            heap.remove(0);
            indexMap.remove(res.id);
            return res;
        }
        Recordatorio tail = heap.remove(last);
        heap.set(0, tail);
        indexMap.put(tail.id, 0);
        indexMap.remove(res.id);
        siftDown(0);
        return res;
    }

    public boolean reprogramar(String id, LocalDateTime nuevaFecha) {
        Integer idx = indexMap.get(id);
        if (idx == null)
            return false;
        Recordatorio r = heap.get(idx);
        LocalDateTime old = r.fecha;
        r.fecha = nuevaFecha;
        if (nuevaFecha.isBefore(old))
            siftUp(idx);
        else
            siftDown(idx);
        return true;
    }

    public Recordatorio peek() {
        if (heap.isEmpty())
            return null;
        return heap.get(0);
    }

    public List<Recordatorio> listAll() {
        return new ArrayList<>(heap);
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (!isBefore(heap.get(i).fecha, heap.get(parent).fecha))
                break;
            swap(i, parent);
            i = parent;
        }
    }

    private void siftDown(int i) {
        int n = heap.size();
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;
            if (left < n && isBefore(heap.get(left).fecha, heap.get(smallest).fecha))
                smallest = left;
            if (right < n && isBefore(heap.get(right).fecha, heap.get(smallest).fecha))
                smallest = right;
            if (smallest == i)
                break;
            swap(i, smallest);
            i = smallest;
        }
    }

    private boolean isBefore(LocalDateTime a, LocalDateTime b) {
        return a.isBefore(b);
    }

    private void swap(int i, int j) {
        Recordatorio ti = heap.get(i);
        Recordatorio tj = heap.get(j);
        heap.set(i, tj);
        heap.set(j, ti);
        indexMap.put(ti.id, j);
        indexMap.put(tj.id, i);
    }
}
