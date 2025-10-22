public class Ejercicio9 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 9: Seguimiento del estado interno ===");
        MinHeap heap = new MinHeap();
        int[] datos = {8, 3, 10, 1, 6};
        System.out.println("Insertando elementos...");
        for (int v : datos) heap.add(v);
        System.out.println("\nEliminando algunos elementos...");
        heap.poll();
        heap.poll();
    }
}
