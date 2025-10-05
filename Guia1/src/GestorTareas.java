import java.util.ArrayList;

public class GestorTareas {

    private ArrayList<Tarea> tareas;

    // Constructor
    public GestorTareas() {
        tareas = new ArrayList<>();
    }

    // Agregar una nueva tarea
    public void agregarTarea(String descripcion) {
        try {
            Tarea nuevaTarea = new Tarea(descripcion);
            tareas.add(nuevaTarea);
            System.out.println(" Tarea agregada: " + descripcion);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al agregar tarea: " + e.getMessage());
        }
    }

    // Mostrar todas las tareas
    public void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
            return;
        }

        System.out.println("\n Lista de tareas:");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i));
        }
    }

    // Marcar una tarea como completada
    public void marcarTareaCompletada(int indice) {
        if (indice < 1 || indice > tareas.size()) {
            System.out.println("Indice inválido.");
            return;
        }

        Tarea tarea = tareas.get(indice - 1);
        if (tarea.estaCompletada()) {
            System.out.println("La tarea ya está completada.");
        } else {
            tarea.marcarCompletada();
            System.out.println(" Tarea completada: " + tarea.getDescripcion());
        }
    }

    // Eliminar tareas completadas
    public void eliminarCompletadas() {
        tareas.removeIf(Tarea::estaCompletada);
        System.out.println("Tareas completadas eliminadas.");
    }
}
