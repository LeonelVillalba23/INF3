public class BuscarUnValor {

    private CrearNodo inicio; // Referencia al primer nodo de la lista

    public BuscarUnValor() {
        this.inicio = null; // Inicialmente, la lista está vacía
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

    // Método para buscar un valor en la lista
    public boolean buscarValor(int valor) {
        CrearNodo actual = inicio;
        while (actual != null) {
            if (actual.getDato() == valor) {
                return true; // Valor encontrado
            }
            actual = actual.getEnlace(); // Avanzar al siguiente nodo
        }
        return false; // Valor no encontrado
    }

}