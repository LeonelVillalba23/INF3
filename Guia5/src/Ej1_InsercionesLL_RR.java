public class Ej1_InsercionesLL_RR {
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		int[] valores = {30, 20, 10, 40, 50, 60};

		System.out.println("=== Ejercicio 1: Inserciones LL/RR ===");
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
