public class Ejercicio6 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 6: Implementar Heapsort ===");
        int[] arr = {9, 4, 7, 1, 6, 2};
        System.out.print("Arreglo original: ");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
        heapsort(arr);
        System.out.print("Arreglo ordenado (ascendente): ");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    public static void heapsort(int[] arr) {
        MinHeap heap = new MinHeap();
        for (int x : arr) heap.add(x);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.poll();
        }
    }
}
