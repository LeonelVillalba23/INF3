public class Ejercicio1 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 1: MinHeap básico ===");
        MinHeap heap = new MinHeap();
        int[] datos = {20, 5, 15, 3, 11};
        System.out.print("Insertando: ");
        for (int v : datos) {
            System.out.print(v + " ");
            heap.add(v);
        }
        System.out.println("\nExtracción en orden (poll):");
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
