public class Ejercicio4 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 4: Mostrar heap en forma de árbol ===");
        MinHeap heap = new MinHeap();
        int[] valores = {20, 5, 15, 3, 11};
        for (int v : valores) {
            heap.add(v);
            System.out.println("\nHeap en forma de árbol después de insertar " + v + ":");
            heap.printTree();
        }
        System.out.println("\nAhora eliminamos elementos para ver los cambios:\n");
        while (!heap.isEmpty()) {
            heap.poll();
            System.out.println("Heap actual en forma de árbol:");
            heap.printTree();
        }
    }
}
