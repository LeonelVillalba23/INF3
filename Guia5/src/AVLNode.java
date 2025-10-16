public class AVLNode {
    public int key;
    public int height;
    public AVLNode left, right;

    public AVLNode(int key) {
        this.key = key;
        this.height = 1; // nuevo nodo empieza con altura 1
    }
}
