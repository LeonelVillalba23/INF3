public class Ej3_EfectoPeinar {
	public static void main(String[] args) {
		System.out.println("=== Ejercicio 3: Efecto peinar ===");

		// Inserciones en AVL directamente
		AVLTree avl = new AVLTree();
		int[] seq = {5,10,15,20,25,30,35};
		System.out.println("Insertando en AVL (se muestran reequilibrios):");
		for (int v : seq) {
			System.out.println("\n-- Insertando " + v + " --");
			avl.insert(v);
			avl.prettyPrint();
		}

		System.out.println("\nSi se insertara en un BST puro (ABB), el árbol sería una lista enlazada degenera\n" +
				"y la altura sería O(n). Un AVL aplica rotaciones para mantener O(log n).\n");
	}

	public static void ejecutar() {
		main(new String[0]);
	}
}
