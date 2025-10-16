public class Ej2_RotDoble_LR_RL {
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		int[] valores = {30, 10, 20, 40, 35, 37};

		System.out.println("=== Ejercicio 2: Rotaciones Dobles LR/RL ===");
		for (int v : valores) {
			System.out.println("\n-- Insertando " + v + " --");
			tree.insert(v);
			tree.prettyPrint();
			System.out.println("In-order: ");
			tree.printInOrder();
			System.out.println("Detalles de nodos:");
			tree.printNodeDetailsInOrder();
		}

		System.out.println("Total de rotaciones aplicadas: " + tree.getRotationCount());
	}

	public static void ejecutar() {
		main(new String[0]);
	}
}
