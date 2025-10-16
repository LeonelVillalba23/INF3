public class EliminarPorValor {
    private CrearNodo inicio; // Referencia al primer nodo de la lista

    public EliminarPorValor() {
        this.inicio = null; // Inicialmente, la lista está vacía
    }

    // Método para eliminar el primer nodo que contiene el valor especificado
    public void eliminarPorValor(int valor) {
        if (inicio == null) {
            System.out.println("La lista está vacía. No se puede eliminar.");
            return;
        }

        // Si el nodo a eliminar es el primero
        if (inicio.getDato() == valor) {
            inicio = inicio.getEnlace(); // Actualizar el inicio para que apunte al siguiente nodo
            return;
        }

        CrearNodo actual = inicio;
        CrearNodo anterior = null;

        // Buscar el nodo con el valor especificado
        while (actual != null && actual.getDato() != valor) {
            anterior = actual;
            actual = actual.getEnlace();
        }

        // Si se encontró el nodo con el valor
        if (actual != null) {
            anterior.setEnlace(actual.getEnlace()); // Saltar el nodo a eliminar
        } else {
            System.out.println("El valor " + valor + " no se encontró en la lista.");
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

    // Método para insertar un nuevo nodo al final de la lista (se añade para poblar
    // la lista desde el menú)
    public void insertarAlFinal(int dato) {
        CrearNodo nuevoNodo = new CrearNodo(dato);
        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            CrearNodo actual = inicio;
            while (actual.getEnlace() != null)
                actual = actual.getEnlace();
            actual.setEnlace(nuevoNodo);
        }
    }

}