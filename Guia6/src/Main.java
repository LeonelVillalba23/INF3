// Main.java
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RBTree<Integer,String> tree = new RBTree<>();
        InsertBST<Integer,String> inserter = new InsertBST<>();
        FixInsert<Integer,String> fixer = new FixInsert<>();
        RotateLeft<Integer,String> rl = new RotateLeft<>();
        RotateRight<Integer,String> rr = new RotateRight<>();
        CaseClassifier<Integer,String> classifier = new CaseClassifier<>();
        RecolorUncleRed<Integer,String> recolor = new RecolorUncleRed<>();
        RotationHandler<Integer,String> rHandler = new RotationHandler<>();
        SuccessorPredecessor<Integer,String> sp = new SuccessorPredecessor<>();
        RangeQuery<Integer,String> rq = new RangeQuery<>();
        InvariantChecks<Integer,String> checks = new InvariantChecks<>();

        Scanner sc = new Scanner(System.in);
        int opt = -1;

        System.out.println("Árbol Rojinegro - Práctico (10 consignas)");
        while (opt != 0) {
            System.out.println("\n---- MENÚ (elige una opción) ----");
            System.out.println("1) Crear árbol (NIL sentinel ya creado al iniciar)");
            System.out.println("2) Rotación izquierda (prueba en mini-árbol)");
            System.out.println("3) Rotación derecha (prueba en mini-árbol)");
            System.out.println("4) Insertar como ABB (sin balance) - insertBST");
            System.out.println("5) Clasificar caso para fixInsert (muestra caso de una clave)");
            System.out.println("6) Recoloreo por tío rojo (ejecuta sobre una clave existente)");
            System.out.println("7) Rotación simple/doble (LL/LR/RR/RL) (ejecuta fixInsert para una clave)");
            System.out.println("8) Successor y Predecessor (de una clave)");
            System.out.println("9) Consulta por rango [a,b]");
            System.out.println("10) Verificadores de invariantes");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            try { opt = Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { opt = -1; }

            switch (opt) {
                case 1 -> {
                    tree = new RBTree<>();
                    System.out.println("Árbol inicializado (NIL sentinel negro único).");
                }

                case 2 -> {
                    System.out.println("Rotación izquierda:");
                    tree.printEstructura(tree.getRoot(), "", false);
                    if (tree.getRoot() != tree.getNIL()) {
                        tree.rotateLeft(tree.getRoot());
                        System.out.println("Después de rotación izquierda:");
                        tree.printEstructura(tree.getRoot(), "", false);
                    } else {
                        System.out.println("Árbol vacío, no se puede rotar.");
                    }
                }

                case 3 -> {
                    System.out.println("Rotación derecha:");
                    tree.printEstructura(tree.getRoot(), "", false);
                    if (tree.getRoot() != tree.getNIL()) {
                        tree.rotateRight(tree.getRoot());
                        System.out.println("Después de rotación derecha:");
                        tree.printEstructura(tree.getRoot(), "", false);
                    } else {
                        System.out.println("Árbol vacío, no se puede rotar.");
                    }
                }

                case 4 -> {
                    System.out.print("Clave (int): ");
                    int k = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Valor (string): ");
                    String v = sc.nextLine();
                    RBNode<Integer,String> z = inserter.insertBST(tree, k, v);
                    System.out.println("InsertBST: nodo " + z + " insertado (sin balance).");
                }

                case 5 -> {
                    System.out.print("Clave para clasificar (debe existir): ");
                    int key = Integer.parseInt(sc.nextLine().trim());
                    RBNode<Integer,String> node = tree.search(key);
                    if (node == tree.getNIL()) System.out.println("Clave no encontrada.");
                    else {
                        CaseClassifier.CaseType c = classifier.classify(tree, node);
                        System.out.println("Caso detectado: " + c);
                    }
                }

                case 6 -> {
                    System.out.print("Clave sobre la que aplicar recoloreo (tiene que tener padre y abuelo): ");
                    int key = Integer.parseInt(sc.nextLine().trim());
                    RBNode<Integer,String> node = tree.search(key);
                    if (node == tree.getNIL()) System.out.println("Clave no encontrada.");
                    else {
                        RBNode<Integer,String> newZ = recolor.handle(tree, node);
                        System.out.println("Recoloreo aplicado. Subir z a: " + newZ);
                    }
                }

                case 7 -> {
                    System.out.print("Clave insertada (insertBST + fixInsert) que querés balancear: ");
                    int key = Integer.parseInt(sc.nextLine().trim());
                    RBNode<Integer,String> z = inserter.insertBST(tree, key, "V" + key);
                    fixer.fixInsert(tree, z);
                    System.out.println("fixInsert aplicado sobre " + key + ". Árbol ahora:");
                    tree.printInOrderWithColors();
                }

                case 8 -> {
                    System.out.print("Clave para successor/predecessor: ");
                    int key = Integer.parseInt(sc.nextLine().trim());
                    RBNode<Integer,String> node = tree.search(key);
                    if (node == tree.getNIL()) System.out.println("Clave no encontrada.");
                    else {
                        RBNode<Integer,String> s = sp.successor(tree, node);
                        RBNode<Integer,String> p = sp.predecessor(tree, node);
                        System.out.println("Nodo: " + node);
                        System.out.println("Successor: " + (s == tree.getNIL() ? "NIL" : s));
                        System.out.println("Predecessor: " + (p == tree.getNIL() ? "NIL" : p));
                    }
                }

                case 9 -> {
                    System.out.print("a: ");
                    int a = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("b: ");
                    int b = Integer.parseInt(sc.nextLine().trim());
                    List<Integer> out = rq.rangeQuery(tree, a, b);
                    System.out.println("Claves en [" + a + "," + b + "]: " + out);
                }

                case 10 -> {
                    System.out.println("raizNegra: " + checks.raizNegra(tree));
                    System.out.println("sinRojoRojo: " + checks.sinRojoRojo(tree));
                    System.out.println("alturaNegra (-1 indica inconsistencia): " + checks.alturaNegra(tree));
                }

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción inválida.");
            }
        }

        sc.close();
    }
}
