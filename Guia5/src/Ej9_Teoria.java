public class Ej9_Teoria {
	public static void main(String[] args) {
		System.out.println("=== Ejercicio 9: Costos y altura (resumen) ===\n");
		System.out.println("a) Altura del AVL es O(log n) porque las rotaciones limitan el factor de equilibrio en cada nodo,\n" +
				"lo que impide que la altura crezca linealmente. Hay una cota H(n) <= c*log(n).");
		System.out.println("b) Por la misma razón, búsquedas/insert/eliminar mantienen O(log n) en tiempo porque recorren a lo sumo la altura del árbol.");
		System.out.println("c) Comparación: ABB sin balance puede degenerar a O(n) en peor caso; rojinegros mantienen altura O(log n) pero con constantes distintas.");
	}

	public static void ejecutar() {
		main(new String[0]);
	}
}
