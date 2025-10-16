public class ContarElementos {
    private CrearNodo inicio; // Referencia al primer nodo de la lista

    public ContarElementos() {
        this.inicio = null; // Inicialmente, la lista está vacía
    }

    // Método para insertar un nuevo nodo al inicio de la lista
    public void insertarAlInicio(int dato) {
        CrearNodo nuevoNodo = new CrearNodo(dato); // Crear un nuevo nodo
        nuevoNodo.setEnlace(inicio); // El nuevo nodo apunta al nodo que era el primero
        inicio = nuevoNodo; // Actualizar el inicio para que apunte al nuevo nodo
    }

    // Método para contar los elementos en la lista
    public int contarElementos() {
        int contador = 0;
        CrearNodo actual = inicio;
        while (actual != null) {
            contador++;
            actual = actual.getEnlace(); // Avanzar al siguiente nodo
        }
        return contador;
    }

}