package src;

import java.util.*;

/**
 * Hash con chaining y rehash cuando loadFactor > 0.75
 */
public class MapaPacientes {
    private static class Node {
        String key; // dni
        Paciente value;
        Node next;

        Node(String k, Paciente v, Node n) {
            key = k;
            value = v;
            next = n;
        }
    }

    private Node[] buckets;
    private int size;

    public MapaPacientes() {
        this(16);
    }

    public MapaPacientes(int initialCapacity) {
        buckets = new Node[initialCapacity];
        size = 0;
    }

    private int indexFor(String key) {
        int h = key == null ? 0 : (key.hashCode() & 0x7fffffff);
        return h % buckets.length;
    }

    public void put(String dni, Paciente p) {
        if (dni == null || p == null)
            throw new IllegalArgumentException("Clave o paciente nulo");
        int idx = indexFor(dni);
        for (Node n = buckets[idx]; n != null; n = n.next) {
            if (n.key.equals(dni)) {
                n.value = p;
                return;
            }
        }
        buckets[idx] = new Node(dni, p, buckets[idx]);
        size++;
        if ((double) size / buckets.length > 0.75)
            rehash(buckets.length * 2);
    }

    public Paciente get(String dni) {
        if (dni == null)
            return null;
        int idx = indexFor(dni);
        for (Node n = buckets[idx]; n != null; n = n.next)
            if (n.key.equals(dni))
                return n.value;
        return null;
    }

    public boolean remove(String dni) {
        if (dni == null)
            return false;
        int idx = indexFor(dni);
        Node prev = null;
        for (Node n = buckets[idx]; n != null; prev = n, n = n.next) {
            if (n.key.equals(dni)) {
                if (prev == null)
                    buckets[idx] = n.next;
                else
                    prev.next = n.next;
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(String dni) {
        return get(dni) != null;
    }

    public int size() {
        return size;
    }

    public Iterable<String> keys() {
        List<String> ks = new ArrayList<>();
        for (Node b : buckets) {
            for (Node n = b; n != null; n = n.next)
                ks.add(n.key);
        }
        return ks;
    }

    private void rehash(int newCapacity) {
        Node[] old = buckets;
        buckets = new Node[newCapacity];
        size = 0;
        for (Node b : old) {
            for (Node n = b; n != null; n = n.next)
                put(n.key, n.value);
        }
    }

    // Método de depuración para imprimir buckets y colisiones
    public void debugPrintBuckets() {
        System.out.println("Tabla de hash (capacidad=" + buckets.length + ", size=" + size + ")");
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("[Bucket " + i + "] -> ");
            Node n = buckets[i];
            if (n == null) {
                System.out.println("vacío");
                continue;
            }
            while (n != null) {
                System.out.print("(" + n.key + ", '" + n.value.nombre + "')");
                n = n.next;
                if (n != null)
                    System.out.print(" -> ");
            }
            System.out.println();
        }
    }
}
