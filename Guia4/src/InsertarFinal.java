public class InsertarFinal {
    private CrearNodo inicio; // Referencia al primer nodo de la lista

    public InsertarFinal() {
        this.inicio = null; // Inicialmente, la lista está vacia
    }

    // Método para insertar un nuevo nodo al final de la lista
    public void insertarAlFinal(int dato) {
        CrearNodo nuevoNodo = new CrearNodo(dato); // Crear un nuevo nodo
        if (inicio == null) {
            inicio = nuevoNodo; // Si la lista está vacía, el nuevo nodo es el primero
        } else {
            CrearNodo actual = inicio;
            while (actual.getEnlace() != null) {
                actual = actual.getEnlace(); // Avanzar hasta el último nodo
            }
            actual.setEnlace(nuevoNodo); // El último nodo apunta al nuevo nodo
        }
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