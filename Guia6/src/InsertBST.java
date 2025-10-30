// InsertBST.java
public class InsertBST<K extends Comparable<K>, V> {
    // inserta como ABB y devuelve el nodo creado (color RED, hijos/padre = NIL)
    public RBNode<K,V> insertBST(RBTree<K,V> tree, K key, V val) {
        RBNode<K,V> NIL = tree.getNIL();
        RBNode<K,V> z = new RBNode<>(key, val, NIL);
        RBNode<K,V> y = NIL;
        RBNode<K,V> x = tree.getRoot();

        while (x != NIL) {
            y = x;
            if (z.key.compareTo(x.key) < 0) x = x.left;
            else x = x.right;
        }
        z.parent = y;
        if (y == NIL) tree.setRoot(z);
        else if (z.key.compareTo(y.key) < 0) y.left = z;
        else y.right = z;
        // z.left,z.right ya apuntan a NIL por constructor
        return z;
    }
}
