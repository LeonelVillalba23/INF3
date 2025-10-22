public class Paciente {
    String nombre;
    int prioridad; // 1 = alta, 2 = media, 3 = baja

    public Paciente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return nombre + " (prioridad " + prioridad + ")";
    }
}
