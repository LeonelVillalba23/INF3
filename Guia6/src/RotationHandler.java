// RotationHandler.java
public class RotationHandler<K extends Comparable<K>, V> {
    private final RotateLeft<K,V> rotL = new RotateLeft<>();
    private final RotateRight<K,V> rotR = new RotateRight<>();

    // maneja LL o LR (lado izquierdo)
    public void handleLeftSide(RBTree<K,V> tree, RBNode<K,V> z) {
        RBNode<K,V> NIL = tree.getNIL();
        RBNode<K,V> p = z.parent;
        RBNode<K,V> g = p.parent;

        if (z == p.right) {
            // LR: rotLeft(p) convierte a LL
            rotL.rotateLeft(tree, p);
            // después de rotación, z pasa a ser p (el antiguo)
            // para seguridad recomputamos p,g desde z
            p = z.parent;
            g = p.parent;
        }
        // LL: rotateRight(g) y recolorear p negro, g rojo
        rotR.rotateRight(tree, g);
        p.color = RBNode.Color.BLACK;
        g.color = RBNode.Color.RED;
    }

    // maneja RR o RL (lado derecho)
    public void handleRightSide(RBTree<K,V> tree, RBNode<K,V> z) {
        RBNode<K,V> NIL = tree.getNIL();
        RBNode<K,V> p = z.parent;
        RBNode<K,V> g = p.parent;

        if (z == p.left) {
            // RL: rotRight(p) convierte a RR
            rotR.rotateRight(tree, p);
            p = z.parent;
            g = p.parent;
        }
        // RR: rotateLeft(g) y recolorear
        rotL.rotateLeft(tree, g);
        p.color = RBNode.Color.BLACK;
        g.color = RBNode.Color.RED;
    }
}
