public class InsercionesYFE_LL_RR {
    public static void ejecutar() {
        AVLTree tree = new AVLTree();

        int[] valores = {30, 20, 10, 40, 50, 60};

        System.out.println("=== Inserciones y Factor de Equilibrio (LL y RR) ===");

        for (int val : valores) {
            System.out.println("\nInsertando: " + val);
            tree.insert(val);
            tree.printInOrder(); // Muestra el recorrido en orden
            System.out.println("-------------------------------");
        }
    }
}
