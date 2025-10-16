public class IncertarInicio {
    private CrearNodo inicio; // Referencia al primer nodo de la lista

    public IncertarInicio() {
        this.inicio = null; // Inicialmente, la lista está vacía
    }

    // Método para insertar un nuevo nodo al inicio de la lista
    public void insertarAlInicio(int dato) {
        CrearNodo nuevoNodo = new CrearNodo(dato); // Crear un nuevo nodo
        nuevoNodo.setEnlace(inicio); // El nuevo nodo apunta al nodo que era el primero
        inicio = nuevoNodo; // Actualizar el inicio para que apunte al nuevo nodo
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