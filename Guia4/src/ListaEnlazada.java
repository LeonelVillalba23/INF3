public class ListaEnlazada {
    private CrearNodo inicio;

    public ListaEnlazada() {
        inicio = null;
    }

    public void insertarInicio(int dato) {
        CrearNodo n = new CrearNodo(dato);
        n.setEnlace(inicio);
        inicio = n;
    }

    public void insertarFinal(int dato) {
        CrearNodo n = new CrearNodo(dato);
        if (inicio == null) {
            inicio = n;
            return;
        }
        CrearNodo a = inicio;
        while (a.getEnlace() != null)
            a = a.getEnlace();
        a.setEnlace(n);
    }

    public void eliminarPorValor(int valor) {
        if (inicio == null)
            return;
        if (inicio.getDato() == valor) {
            inicio = inicio.getEnlace();
            return;
        }
        CrearNodo prev = inicio;
        CrearNodo cur = inicio.getEnlace();
        while (cur != null) {
            if (cur.getDato() == valor) {
                prev.setEnlace(cur.getEnlace());
                return;
            }
            prev = cur;
            cur = cur.getEnlace();
        }
    }

    public boolean buscar(int valor) {
        CrearNodo a = inicio;
        while (a != null) {
            if (a.getDato() == valor)
                return true;
            a = a.getEnlace();
        }
        return false;
    }

    public int contar() {
        int c = 0;
        CrearNodo a = inicio;
        while (a != null) {
            c++;
            a = a.getEnlace();
        }
        return c;
    }

    public void invertir() {
        CrearNodo prev = null;
        CrearNodo cur = inicio;
        CrearNodo next;
        while (cur != null) {
            next = cur.getEnlace();
            cur.setEnlace(prev);
            prev = cur;
            cur = next;
        }
        inicio = prev;
    }

    public void insertarEn(int pos, int valor) {
        if (pos < 0)
            return;
        if (pos == 0) {
            insertarInicio(valor);
            return;
        }
        CrearNodo a = inicio;
        for (int i = 0; i < pos - 1 && a != null; i++)
            a = a.getEnlace();
        if (a == null)
            return;
        CrearNodo n = new CrearNodo(valor);
        n.setEnlace(a.getEnlace());
        a.setEnlace(n);
    }

    public void eliminarDuplicados() {
        CrearNodo actual = inicio;
        while (actual != null) {
            CrearNodo runner = actual;
            while (runner.getEnlace() != null) {
                if (runner.getEnlace().getDato() == actual.getDato()) {
                    runner.setEnlace(runner.getEnlace().getEnlace());
                } else
                    runner = runner.getEnlace();
            }
            actual = actual.getEnlace();
        }
    }

    public void imprimir() {
        if (inicio == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        System.out.println(inicio.toString());
    }
}
