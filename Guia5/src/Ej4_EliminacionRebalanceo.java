public class Ej4_EliminacionRebalanceo {
	public static void main(String[] args) {
		System.out.println("=== Ejercicio 4: Eliminación con rebalanceo ===");
		AVLTree tree = new AVLTree();
		int[] inicial = {50,30,70,20,40,60,80,65,75};
		for (int v : inicial) tree.insert(v);

		System.out.println("Árbol inicial:");
		tree.prettyPrint();

		System.out.println("\n-- Eliminando 20 --");
		tree.delete(20);
		tree.prettyPrint();

		System.out.println("\n-- Eliminando 70 --");
		tree.delete(70);
		tree.prettyPrint();
	}

	public static void ejecutar() {
		main(new String[0]);
	}
}
