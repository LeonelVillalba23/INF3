public class CrearNodo {
    private int dato;
    private CrearNodo enlace;

    public CrearNodo(int d) {
        this.dato = d;
        this.enlace = null;
    }

    // Constructor por defecto
    public CrearNodo() {
        this(0);
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public CrearNodo getEnlace() {
        return enlace;
    }

    public void setEnlace(CrearNodo enlace) {
        this.enlace = enlace;
    }

    // Imprime la sublista desde este nodo (ej: 10 -> 20 -> 30)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        CrearNodo actual = this;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.enlace != null)
                sb.append(" -> ");
            actual = actual.enlace;
        }
        return sb.toString();
    }
}
