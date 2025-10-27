package modelo;

import java.time.LocalDateTime;

public class Turno {
    public String id;
    public String dniPaciente;
    public String matriculaMedico;
    public LocalDateTime fechaHora;
    public int duracionMin;
    public String motivo;

    public Turno(String id, String dniPaciente, String matriculaMedico, LocalDateTime fechaHora, int duracionMin, String motivo) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.matriculaMedico = matriculaMedico;
        this.fechaHora = fechaHora;
        this.duracionMin = duracionMin;
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return id + " | " + dniPaciente + " | " + matriculaMedico + " | " + fechaHora + " | " + motivo;
    }
}
