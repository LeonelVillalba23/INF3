package src;

import java.util.*;

public class HistorialAcciones {
    private Stack<Accion> undoStack; // Pila para deshacer
    private Stack<Accion> redoStack; // Pila para rehacer
    private int maxHistorial; // Máximo número de acciones en historial

    public HistorialAcciones(int maxHistorial) {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.maxHistorial = maxHistorial;
    }

    // Agregar una nueva acción al historial
    public void agregarAccion(Accion accion) {
        undoStack.push(accion);
        redoStack.clear(); // Al agregar una nueva acción, se limpia la pila de redo

        // Si excedemos el máximo, eliminamos la acción más antigua
        if (undoStack.size() > maxHistorial) {
            // Crear una nueva pila temporal con todas menos la primera
            Stack<Accion> temp = new Stack<>();
            while (undoStack.size() > 1) {
                temp.push(undoStack.pop());
            }
            undoStack.clear();
            // Devolver todas menos la primera (más antigua) a undoStack
            while (!temp.empty()) {
                undoStack.push(temp.pop());
            }
        }
    }

    // Deshacer última acción
    public Accion deshacer() {
        if (undoStack.empty()) {
            return null;
        }
        Accion accion = undoStack.pop();
        redoStack.push(accion);
        return accion;
    }

    // Rehacer última acción deshecha
    public Accion rehacer() {
        if (redoStack.empty()) {
            return null;
        }
        Accion accion = redoStack.pop();
        undoStack.push(accion);
        return accion;
    }

    // Verificar si se puede deshacer
    public boolean puedeDeshacer() {
        return !undoStack.empty();
    }

    // Verificar si se puede rehacer
    public boolean puedeRehacer() {
        return !redoStack.empty();
    }

    // Obtener lista de acciones en el historial (desde la más reciente)
    public List<Accion> obtenerHistorial() {
        List<Accion> historial = new ArrayList<>();
        Stack<Accion> temp = new Stack<>();

        // Copiar a temp invirtiendo el orden
        while (!undoStack.empty()) {
            temp.push(undoStack.pop());
        }

        // Restaurar undoStack y construir lista
        while (!temp.empty()) {
            Accion a = temp.pop();
            historial.add(a);
            undoStack.push(a);
        }

        return historial;
    }

    // Limpiar todo el historial
    public void limpiar() {
        undoStack.clear();
        redoStack.clear();
    }

    // Obtener tamaño actual del historial
    public int size() {
        return undoStack.size();
    }
}