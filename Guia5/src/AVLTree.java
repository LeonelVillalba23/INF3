public class AVLTree {

    private AVLNode root;
    private int rotationCount = 0;

    public void insert(int key) {
        root = insert(root, key);
    }

    private AVLNode insert(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // no se permiten duplicados
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        System.out.println("Insertando " + key + " → Nodo: " + node.key + ", FE: " + balance);

        // LL
        if (balance > 1 && key < node.left.key) {
            System.out.println("Rotación simple derecha (LL) en nodo " + node.key);
            return rotateRight(node);
        }

        // RR
        if (balance < -1 && key > node.right.key) {
            System.out.println("Rotación simple izquierda (RR) en nodo " + node.key);
            return rotateLeft(node);
        }

        // LR
        if (balance > 1 && key > node.left.key) {
            System.out.println("Rotación doble LR en nodo " + node.key);
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            System.out.println("Rotación doble RL en nodo " + node.key);
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    private AVLNode delete(AVLNode node, int key) {
        if (node == null) return null;

        if (key < node.key) node.left = delete(node.left, key);
        else if (key > node.key) node.right = delete(node.right, key);
        else {
            // nodo con uno o cero hijos
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    // sin hijos
                    node = null;
                } else {
                    node = temp; // reemplazar
                }
            } else {
                // dos hijos: tomar sucesor inorder
                AVLNode succ = minValueNode(node.right);
                node.key = succ.key;
                node.right = delete(node.right, succ.key);
            }
        }

        if (node == null) return null;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0) return rotateRight(node);

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0) return rotateLeft(node);

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    private AVLNode rotateRight(AVLNode y) {
        rotationCount++;
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        rotationCount++;
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // In-order
    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);
        }
    }

    // Print tree rotated (simple pretty print)
    public void prettyPrint() {
        prettyPrint(root, "", true);
    }

    private void prettyPrint(AVLNode node, String prefix, boolean isTail) {
        if (node == null) return;
        if (node.right != null) {
            prettyPrint(node.right, prefix + (isTail ? "│   " : "    "), false);
        }
        System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.key + " (h=" + node.height + ", FE=" + getBalance(node) + ")");
        if (node.left != null) {
            prettyPrint(node.left, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    public void printNodeDetailsInOrder() {
        printNodeDetailsInOrder(root);
    }

    private void printNodeDetailsInOrder(AVLNode node) {
        if (node != null) {
            printNodeDetailsInOrder(node.left);
            System.out.println("Valor=" + node.key + ", altura=" + node.height + ", FE=" + getBalance(node));
            printNodeDetailsInOrder(node.right);
        }
    }

    public int getRotationCount() {
        return rotationCount;
    }

    // esAVL -> devuelve (esAVL, altura) en una pasada
    public static class AVLCheckResult {
        public final boolean isAVL;
        public final int height;

        public AVLCheckResult(boolean isAVL, int height) {
            this.isAVL = isAVL;
            this.height = height;
        }
    }

    public AVLCheckResult esAVL() {
        return esAVLHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Verifica AVL y propiedad BST usando rangos
    private AVLCheckResult esAVLHelper(AVLNode node, int min, int max) {
        if (node == null) return new AVLCheckResult(true, 0);

        if (node.key <= min || node.key >= max) return new AVLCheckResult(false, 0);

        AVLCheckResult left = esAVLHelper(node.left, min, node.key);
        AVLCheckResult right = esAVLHelper(node.right, node.key, max);

        boolean isAVL = left.isAVL && right.isAVL && Math.abs(left.height - right.height) <= 1;
        int height = 1 + Math.max(left.height, right.height);
        return new AVLCheckResult(isAVL, height);
    }

}
