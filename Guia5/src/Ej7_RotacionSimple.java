public class Ej7_RotacionSimple {
	public static void main(String[] args) {
		System.out.println("=== Ejercicio 7: Rotación simple izquierda (RR) ===");
		AVLTree t = new AVLTree();
		int[] seq = {10,20,30}; // provoca RR en 10
		for (int v : seq) {
			System.out.println("\n-- Insertando " + v + " --");
			t.insert(v);
			t.prettyPrint();
		}
		System.out.println("Antes/Después mostrado arriba; la rotación aplicada fue RR (rotación izquierda sobre 10).");
	}

	public static void ejecutar() {
		main(new String[0]);
	}
}
