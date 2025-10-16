public class EliminarDuplicados {
    private CrearNodo inicio; // Referencia al primer nodo de la lista

    public EliminarDuplicados() {
        this.inicio = null; // Inicialmente, la lista está vacía
    }

    // Método para eliminar nodos duplicados en la lista
    public void eliminarDuplicados() {
        CrearNodo actual = inicio;

        while (actual != null) {
            CrearNodo runner = actual;
            while (runner.getEnlace() != null) {
                if (runner.getEnlace().getDato() == actual.getDato()) {
                    // Se encontró un duplicado, eliminarlo
                    runner.setEnlace(runner.getEnlace().getEnlace());
                } else {
                    runner = runner.getEnlace();
                }
            }
            actual = actual.getEnlace();
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

    // Método para insertar al final (útil para poblar desde menú)
    public void insertarAlFinal(int dato) {
        CrearNodo nuevo = new CrearNodo(dato);
        if (inicio == null) {
            inicio = nuevo;
            return;
        }
        CrearNodo actual = inicio;
        while (actual.getEnlace() != null)
            actual = actual.getEnlace();
        actual.setEnlace(nuevo);
    }

}