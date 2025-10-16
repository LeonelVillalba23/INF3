public class InsertarEnPosicion {
    private CrearNodo inicio; // Referencia al primer nodo de la lista

    public InsertarEnPosicion() {
        this.inicio = null; // Inicialmente, la lista está vacía
    }

    // Método para insertar un nuevo nodo al inicio de la lista
    public void insertarAlInicio(int dato) {
        CrearNodo nuevoNodo = new CrearNodo(dato); // Crear un nuevo nodo
        nuevoNodo.setEnlace(inicio); // El nuevo nodo apunta al nodo que era el primero
        inicio = nuevoNodo; // Actualizar el inicio para que apunte al nuevo nodo
    }

    // Método para insertar un nuevo nodo en una posición específica
    public void insertarEnPosicion(int dato, int posicion) {
        if (posicion < 0) {
            System.out.println("Posición inválida.");
            return;
        }
        if (posicion == 0) {
            insertarAlInicio(dato);
            return;
        }
        CrearNodo nuevoNodo = new CrearNodo(dato);
        CrearNodo actual = inicio;
        for (int i = 0; i < posicion - 1; i++) {
            if (actual == null) {
                System.out.println("Posición fuera de los límites de la lista.");
                return;
            }
            actual = actual.getEnlace();
        }
        if (actual == null) {
            System.out.println("Posición fuera de los límites de la lista.");
            return;
        }
        nuevoNodo.setEnlace(actual.getEnlace());
        actual.setEnlace(nuevoNodo);
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