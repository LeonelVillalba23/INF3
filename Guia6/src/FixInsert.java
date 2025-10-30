// FixInsert.java
public class FixInsert<K extends Comparable<K>, V> {
    private final CaseClassifier<K,V> classifier = new CaseClassifier<>();
    private final RecolorUncleRed<K,V> recolor = new RecolorUncleRed<>();
    private final RotationHandler<K,V> rotHandler = new RotationHandler<>();

    public void fixInsert(RBTree<K,V> tree, RBNode<K,V> z) {
        RBNode<K,V> NIL = tree.getNIL();
        while (z != tree.getRoot() && z.parent.color == RBNode.Color.RED) {
            CaseClassifier.CaseType c = classifier.classify(tree, z);
            if (c == CaseClassifier.CaseType.TIO_ROJO) {
                z = recolor.handle(tree, z); // recolorear y subir z = g
            } else if (c == CaseClassifier.CaseType.LL) {
                rotHandler.handleLeftSide(tree, z);
                break;
            } else if (c == CaseClassifier.CaseType.RR) {
                rotHandler.handleRightSide(tree, z);
                break;
            } else if (c == CaseClassifier.CaseType.LR) {
                rotHandler.handleLeftSide(tree, z);
                break;
            } else if (c == CaseClassifier.CaseType.RL) {
                rotHandler.handleRightSide(tree, z);
                break;
            } else {
                break;
            }
        }
        // asegurar raíz negra
        tree.getRoot().color = RBNode.Color.BLACK;
    }
}
