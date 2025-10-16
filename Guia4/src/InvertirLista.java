public class InvertirLista {

    private CrearNodo inicio; // Referencia al primer nodo de la lista
    private CrearNodo fin; // Referencia al último nodo de la lista

    public InvertirLista() {
        this.inicio = null; // Inicialmente, la lista está vacía
        this.fin = null; // Inicialmente, la lista está vacía
    }

    // Método para insertar un nuevo nodo al final de la lista
    public void insertarAlFinal(int dato) {
        CrearNodo nuevoNodo = new CrearNodo(dato); // Crear un nuevo nodo
        if (inicio == null) {
            inicio = nuevoNodo; // Si la lista está vacía, el nuevo nodo es el primero
            fin = nuevoNodo; // El nuevo nodo también es el último
        } else {
            fin.setEnlace(nuevoNodo); // El último nodo apunta al nuevo nodo
            fin = nuevoNodo; // Actualizar el fin para que apunte al nuevo nodo
        }
    }

    // Método para invertir la lista
    public void invertirLista() {
        CrearNodo anterior = null;
        CrearNodo actual = inicio;
        CrearNodo siguiente = null;
        fin = inicio; // El nodo que era el primero será el último después de invertir
        while (actual != null) {
            siguiente = actual.getEnlace(); // Guardar el siguiente nodo
            actual.setEnlace(anterior); // Invertir el enlace
            anterior = actual; // Mover anterior a actual
            actual = siguiente; // Mover actual a siguiente
        }
        inicio = anterior; // Actualizar inicio para que apunte al nuevo primer nodo
    }

    // Método para imprimir la lista desde el inicio
    public void imprimirLista() {
        if (inicio == null) {
            System.out.println("La lista está vacía.");
        } else {
            System.out.println("Lista: " + inicio.toString());
        }
    }

}