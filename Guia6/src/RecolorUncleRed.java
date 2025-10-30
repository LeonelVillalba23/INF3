// RecolorUncleRed.java

public class RecolorUncleRed<K extends Comparable<K>, V> {

    // recolorea p y tío a negro, g a rojo; devuelve el nuevo z = g (subir)
    
    public RBNode<K,V> handle(RBTree<K,V> tree, RBNode<K,V> z) {
        RBNode<K,V> NIL = tree.getNIL();
        if (z == NIL || z.parent == NIL || z.parent.parent == NIL) return z;
        RBNode<K,V> p = z.parent;
        RBNode<K,V> g = p.parent;
        RBNode<K,V> uncle = (p == g.left) ? g.right : g.left;

        p.color = RBNode.Color.BLACK;
        if (uncle != NIL) uncle.color = RBNode.Color.BLACK;
        g.color = RBNode.Color.RED;

        return g; // continuar desde g
    }
}
