// RBTree.java
import java.util.ArrayList;
import java.util.List;

public class RBTree<K extends Comparable<K>, V> {
    private final RBNode<K,V> NIL;
    private RBNode<K,V> root;

    public RBTree() {
        this.NIL = new RBNode<>();
        // asegurar que el sentinel apunte a sí mismo
        this.NIL.left = this.NIL.right = this.NIL.parent = this.NIL;
        this.NIL.color = RBNode.Color.BLACK;
        this.root = NIL;
    }

    public RBNode<K,V> getNIL() { return NIL; }
    public RBNode<K,V> getRoot() { return root; }
    public void setRoot(RBNode<K,V> r) { this.root = (r==null ? NIL : r); }

    /* ROTACIONES: left y right (actualizan padres/hijos y root si corresponde) */
    public void rotateLeft(RBNode<K,V> x) {
        if (x == NIL) return;
        RBNode<K,V> y = x.right;
        if (y == NIL) return;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) this.root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    public void rotateRight(RBNode<K,V> y) {
        if (y == NIL) return;
        RBNode<K,V> x = y.left;
        if (x == NIL) return;
        y.left = x.right;
        if (x.right != NIL) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == NIL) this.root = x;
        else if (y == y.parent.right) y.parent.right = x;
        else y.parent.left = x;
        x.right = y;
        y.parent = x;
    }

    /* util: buscar por clave (devuelve NIL si no existe) */
    public RBNode<K,V> search(K key) {
        RBNode<K,V> x = root;
        while (x != NIL) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return NIL;
    }

    /* util: in-order traversal keys (para debug) */
    public List<K> inorderKeys() {
        List<K> out = new ArrayList<>();
        inorderRec(root, out);
        return out;
    }
    private void inorderRec(RBNode<K,V> node, List<K> out) {
        if (node == NIL) return;
        inorderRec(node.left, out);
        out.add(node.key);
        inorderRec(node.right, out);
    }

    /* impresión in-order con colores */
    public void printInOrderWithColors() {
        printInOrderWithColors(root);
        System.out.println();
    }
    private void printInOrderWithColors(RBNode<K,V> node) {
        if (node == NIL) return;
        printInOrderWithColors(node.left);
        System.out.print(node + " ");
        printInOrderWithColors(node.right);
    }


public void printEstructura(RBNode<K, V> nodo, String prefijo, boolean esIzq) {
    if (nodo == NIL) return;
    System.out.println(prefijo + (esIzq ? "├── " : "└── ") + nodo.key + "(" + nodo.color + ")");
    printEstructura(nodo.left, prefijo + (esIzq ? "│   " : "    "), true);
    printEstructura(nodo.right, prefijo + (esIzq ? "│   " : "    "), false);
}

public void printEstructura() {
    printEstructura(root, "", false);
}


}

