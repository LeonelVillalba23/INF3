// InvariantChecks.java
public class InvariantChecks<K extends Comparable<K>, V> {

    public boolean raizNegra(RBTree<K,V> tree) {
        RBNode<K,V> root = tree.getRoot();
        return root == tree.getNIL() || root.color == RBNode.Color.BLACK;
    }

    public boolean sinRojoRojo(RBTree<K,V> tree) {
        return checkNoRedRed(tree, tree.getRoot());
    }

    private boolean checkNoRedRed(RBTree<K,V> tree, RBNode<K,V> node) {
        RBNode<K,V> NIL = tree.getNIL();
        if (node == NIL) return true;
        if (node.color == RBNode.Color.RED) {
            if (node.left.color == RBNode.Color.RED || node.right.color == RBNode.Color.RED) return false;
        }
        return checkNoRedRed(tree, node.left) && checkNoRedRed(tree, node.right);
    }

    // devuelve black-height si consistente, sino -1
    public int alturaNegra(RBTree<K,V> tree) {
        return computeBlackHeight(tree, tree.getRoot());
    }

    private int computeBlackHeight(RBTree<K,V> tree, RBNode<K,V> node) {
        RBNode<K,V> NIL = tree.getNIL();
        if (node == NIL) return 1;
        int left = computeBlackHeight(tree, node.left);
        int right = computeBlackHeight(tree, node.right);
        if (left == -1 || right == -1) return -1;
        if (left != right) return -1;
        return left + (node.color == RBNode.Color.BLACK ? 1 : 0);
    }
}
