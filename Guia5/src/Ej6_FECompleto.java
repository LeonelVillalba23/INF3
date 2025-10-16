public class Ej6_FECompleto {
	public static void main(String[] args) {
		System.out.println("=== Ejercicio 6: Factor de equilibrio completo ===");
		AVLTree t = new AVLTree();
		int[] seq = {10,100,20,80,40,70};
		for (int v : seq) {
			System.out.println("\n-- Insertando " + v + " --");
			t.insert(v);
			t.prettyPrint();
		}

		System.out.println("\nNodos finales (valor, altura, FE):");
		t.printNodeDetailsInOrder();
		System.out.println("Total rotaciones: " + t.getRotationCount());
	}

	public static void ejecutar() {
		main(new String[0]);
	}
}
