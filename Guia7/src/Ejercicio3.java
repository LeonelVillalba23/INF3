public class Ejercicio3 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 3: Implementar percolateDown ===");
        MinHeap heap = new MinHeap();
        int[] valores = {20, 5, 15, 3, 11};
        for (int v : valores) heap.add(v);
        System.out.println("\nHeap inicial:");
        heap.printArray();
        while (!heap.isEmpty()) {
            int min = heap.poll();
            System.out.println("→ Se extrajo: " + min);
        }
    }
}
