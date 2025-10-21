/**
 * Implementación mínima para el ejercicio 1: Nodo y NIL sentinel.
 * - RBNode<K,V> con key, val, color, left, right, parent.
 * - RBTree<K,V> con final RBNode<K,V> NIL (negro) y root = NIL.
 * - Constructor de nodo que setea left/right/parent = NIL.
 *
 * La clase también expone un método estático `ejecutar()` para que el
 * menú pueda invocarla y ver una demo básica.
 */

public class Nodo {

    public enum Color { RED, BLACK }

    public static class RBNode<K extends Comparable<K>, V> {
        public K key;
        public V val;
        public Color color;
        public RBNode<K, V> left;
        public RBNode<K, V> right;
        public RBNode<K, V> parent;

        public RBNode(K key, V val, Color color, RBNode<K, V> nil) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.left = nil;
            this.right = nil;
            this.parent = nil;
        }

        public RBNode() {
            this.key = null;
            this.val = null;
            this.color = Color.BLACK;
            this.left = this;
            this.right = this;
            this.parent = this;
        }

        @Override
        public String toString() {
            if (this.left == this && this.right == this && this.parent == this && this.key == null)
                return "NIL";
            return "RBNode{" + "key=" + key + ", color=" + color + '}';
        }
    }

    public static class RBTree<K extends Comparable<K>, V> {
        public final RBNode<K, V> NIL;
        public RBNode<K, V> root;

        public RBTree() {
            this.NIL = new RBNode<>();
            this.root = this.NIL;
        }

        public RBNode<K, V> createNode(K key, V val, Color color) {
            return new RBNode<>(key, val, color, this.NIL);
        }
    }

    public static void ejecutar() {
        System.out.println("--- Ejercicio 1: Nodo y NIL sentinel (demo) ---");
        RBTree<Integer, String> t = new RBTree<>();
        System.out.println("Árbol creado. root == NIL? " + (t.root == t.NIL));
        System.out.println("NIL: " + t.NIL);

        RBNode<Integer, String> n = t.createNode(10, "diez", Color.RED);
        System.out.println("Nodo creado: " + n);
        System.out.println("n.left == NIL? " + (n.left == t.NIL));
        System.out.println("n.right == NIL? " + (n.right == t.NIL));
        System.out.println("n.parent == NIL? " + (n.parent == t.NIL));

        t.root = n;
        n.parent = t.NIL;
        System.out.println("Después de setear root = n, root == NIL? " + (t.root == t.NIL));
        System.out.println("root: " + t.root + ", root.parent == NIL? " + (t.root.parent == t.NIL));
    }

}


        // crear un nodo y verificar que sus punteros iniciales apunten a NIL

