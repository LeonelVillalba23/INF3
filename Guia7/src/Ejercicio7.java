public class Ejercicio7 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 7: Implementar MaxHeap ===");
        int[] datos = {10, 3, 15, 8, 6, 12};
        MaxHeap heap = new MaxHeap();
        System.out.print("Insertando: ");
        for (int x : datos) {
            System.out.print(x + " ");
            heap.add(x);
        }
        System.out.println("\n\nExtrayendo en orden (mayor a menor):");
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
