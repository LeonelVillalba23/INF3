public class Ejercicio2 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 2: Implementar percolateUp ===");
        MinHeap heap = new MinHeap();
        int[] valores = {20, 5, 15, 3, 11};
        for (int v : valores) heap.add(v);
        System.out.println("\nExtrayendo todos para comprobar orden:");
        while (!heap.isEmpty()) {
            System.out.println("Sacando: " + heap.poll());
        }
    }
}
