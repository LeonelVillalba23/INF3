public class Ej8_RotacionDobleLR {
	public static void main(String[] args) {
		System.out.println("=== Ejercicio 8: Rotación doble LR ===");
		AVLTree t = new AVLTree();
		int[] seq = {30,10,20}; // provoca LR en 30
		for (int v : seq) {
			System.out.println("\n-- Insertando " + v + " --");
			t.insert(v);
			t.prettyPrint();
		}

		System.out.println("LR se ejecuta como: rotación izquierda en hijo (10->20) y rotación derecha en 30.");
	}

	public static void ejecutar() {
		main(new String[0]);
	}
}
