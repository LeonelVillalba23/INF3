// CaseClassifier.java
public class CaseClassifier<K extends Comparable<K>, V> {
    public enum CaseType { TIO_ROJO, LL, RR, LR, RL, NONE }

    public CaseType classify(RBTree<K,V> tree, RBNode<K,V> z) {
        RBNode<K,V> NIL = tree.getNIL();
        if (z == NIL || z.parent == NIL || z.parent.parent == NIL) return CaseType.NONE;
        RBNode<K,V> p = z.parent;
        RBNode<K,V> g = p.parent;
        RBNode<K,V> uncle = (p == g.left) ? g.right : g.left;

        if (uncle != NIL && uncle.color == RBNode.Color.RED) return CaseType.TIO_ROJO;
        if (p == g.left) {
            if (z == p.left) return CaseType.LL;
            else return CaseType.LR;
        } else {
            if (z == p.right) return CaseType.RR;
            else return CaseType.RL;
        }
    }
}
