package src;
public class Medico {
    String matricula;
    String nombre;
    String especialidad;
    
    
    public Medico(String matricula, String nombre, String especialidad) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return nombre + " - " + especialidad + " (Matrícula: " + matricula + ")";
    }
    
}
