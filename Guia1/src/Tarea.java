public class Tarea {
    private String descripcion;
    private boolean completada;

    // creamos constructor
    public Tarea(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion esta mal");

        }
        this.descripcion = descripcion;
        this.completada = false;

    }

    // generamos los getters
    public String getDescripcion() {
        return descripcion;
    }

    public boolean estaCompletada() {
        return completada;
    }

    public void marcarCompletada() {
        this.completada = true;
    }

    @Override
    public String toString() {
        String estado = completada ? "Completada " : "Pendiente";
        return descripcion + " - " + estado;

    }
}
