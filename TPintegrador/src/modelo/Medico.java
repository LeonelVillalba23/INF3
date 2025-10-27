package modelo;

public class Medico {
    public String matricula;
    public String nombre;
    public String especialidad;

    public Medico(String matricula, String nombre, String especialidad) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return matricula + " - " + nombre + " (" + especialidad + ")";
    }
}
