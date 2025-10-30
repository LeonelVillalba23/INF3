// RBNode.java
public class RBNode<K extends Comparable<K>, V> {
    public enum Color { RED, BLACK }

    public K key;
    public V val;
    public Color color;
    public RBNode<K,V> left, right, parent;

    // Constructor para nodo normal: deja hijos/padre apuntando a 'nil' que le pases
    public RBNode(K key, V val, RBNode<K,V> nil) {
        this.key = key;
        this.val = val;
        this.color = Color.RED;
        this.left = nil;
        this.right = nil;
        this.parent = nil;
    }

    // Constructor sin args para crear el NIL sentinel; después hay que fijar sus punteros a sí mismo
    public RBNode() {
        this.key = null;
        this.val = null;
        this.color = Color.BLACK;
        this.left = this.right = this.parent = this;
    }

    @Override
    public String toString() {
        if (key == null) return "NIL";
        return key.toString() + "(" + (color==Color.RED ? "R" : "B") + ")";
    }
}
