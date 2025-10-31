package src;

public class Turno {
    String id;
    String dniPaciente;
    String matriculaMedico;
    String fecha;
    String hora;
    int duracion;
    String motivo;

    public Turno(String id, String fecha, String hora, int duracion, String dniPaciente, String matriculaMedico,
            String motivo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.dniPaciente = dniPaciente;
        this.matriculaMedico = matriculaMedico;
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return id + " | " + fecha + " " + hora + " (" + duracion + " min) - Paciente: "
                + dniPaciente + ", Médico: " + matriculaMedico;
    }
}