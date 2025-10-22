public class Ejercicio5 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 5: Construcción desde un arreglo (Heapify) ===");
        int[] datos = {20, 5, 15, 3, 11};
        System.out.print("Arreglo original: ");
        for (int d : datos) System.out.print(d + " ");
        System.out.println();
        MinHeap heap = new MinHeap(datos);
        System.out.println("\nHeap final en forma de árbol:");
        heap.printTree();
    }
}
