// RangeQuery.java
import java.util.ArrayList;
import java.util.List;

public class RangeQuery<K extends Comparable<K>, V> {
    public List<K> rangeQuery(RBTree<K,V> tree, K a, K b) {
        List<K> out = new ArrayList<>();
        rangeInOrder(tree, tree.getRoot(), a, b, out);
        return out;
    }

    private void rangeInOrder(RBTree<K,V> tree, RBNode<K,V> node, K a, K b, List<K> out) {
        RBNode<K,V> NIL = tree.getNIL();
        if (node == NIL) return;
        if (node.key.compareTo(a) < 0) {
            rangeInOrder(tree, node.right, a, b, out);
        } else if (node.key.compareTo(b) > 0) {
            rangeInOrder(tree, node.left, a, b, out);
        } else {
            rangeInOrder(tree, node.left, a, b, out);
            out.add(node.key);
            rangeInOrder(tree, node.right, a, b, out);
        }
    }
}
