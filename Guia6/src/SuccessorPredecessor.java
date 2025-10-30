// SuccessorPredecessor.java
public class SuccessorPredecessor<K extends Comparable<K>, V> {
    public RBNode<K,V> minimum(RBTree<K,V> tree, RBNode<K,V> node) {
        RBNode<K,V> x = node;
        while (x.left != tree.getNIL()) x = x.left;
        return x;
    }

    public RBNode<K,V> maximum(RBTree<K,V> tree, RBNode<K,V> node) {
        RBNode<K,V> x = node;
        while (x.right != tree.getNIL()) x = x.right;
        return x;
    }

    public RBNode<K,V> successor(RBTree<K,V> tree, RBNode<K,V> node) {
        RBNode<K,V> NIL = tree.getNIL();
        if (node == NIL) return NIL;
        if (node.right != NIL) return minimum(tree, node.right);
        RBNode<K,V> y = node.parent;
        RBNode<K,V> x = node;
        while (y != NIL && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public RBNode<K,V> predecessor(RBTree<K,V> tree, RBNode<K,V> node) {
        RBNode<K,V> NIL = tree.getNIL();
        if (node == NIL) return NIL;
        if (node.left != NIL) return maximum(tree, node.left);
        RBNode<K,V> y = node.parent;
        RBNode<K,V> x = node;
        while (y != NIL && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }
}
