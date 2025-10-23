import java.util.Stack;

/**
 * Clase simple para gestionar deshacer/rehacer con dos pilas.
 * Uso mínimo: desde el Main se llaman doAction, undo y redo.
 */
public class DeshacerRehacerPila {
    private Stack<String> undo;
    private Stack<String> redo;

    public DeshacerRehacerPila() {
        undo = new Stack<>();
        redo = new Stack<>();
    }

    // Guardar una acción y limpiar la pila de redo
    public void doAction(String accion) {
        undo.push(accion);
        redo.clear();
    }

    // Deshacer: pasar de undo a redo y devolver la acción deshecha (o null)
    public String undo() {
        if (undo.isEmpty()) return null;
        String a = undo.pop();
        redo.push(a);
        return a;
    }

    // Rehacer: pasar de redo a undo y devolver la acción rehecha (o null)
    public String redo() {
        if (redo.isEmpty()) return null;
        String a = redo.pop();
        undo.push(a);
        return a;
    }

    public Stack<String> getUndoStack() {
        return undo;
    }

    public Stack<String> getRedoStack() {
        return redo;
    }
}
