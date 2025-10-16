import java.util.*;

public class Ej10_Tests {
	public static void main(String[] args) {
		System.out.println("=== Ejercicio 10: Secuencias estresantes y conteo de rotaciones ===");

		List<int[]> sequences = new ArrayList<>();
		int[] asc = new int[20]; for (int i=0;i<20;i++) asc[i]=i+1;
		int[] desc = new int[20]; for (int i=0;i<20;i++) desc[i]=20-i;
		int[] pseudo = {5,3,8,3,12,7,1,4,9,14,2,6,10,11,13,15,5,8,16,17};
		sequences.add(asc); sequences.add(desc); sequences.add(pseudo);

		int idx=1;
		for (int[] seq : sequences) {
			System.out.println("\n-- Secuencia " + idx++ + " --");
			AVLTree t = new AVLTree();
			for (int v : seq) {
				t.insert(v);
				// Aquí en tests reales verificaríamos: t.esAVL().isAVL == true, in-order ordenado, y alturas.
			}
			System.out.println("Total rotaciones: " + t.getRotationCount());
		}

	}

	public static void ejecutar() {
		main(new String[0]);
	}

}
