package src;

import java.time.LocalDateTime;

public class Recordatorio {
    public String id;
    public LocalDateTime fecha;
    public String dniPaciente;
    public String mensaje;

    public Recordatorio(String id, LocalDateTime fecha, String dniPaciente, String mensaje) {
        this.id = id;
        this.fecha = fecha;
        this.dniPaciente = dniPaciente;
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Recordatorio[" + id + "] " + fecha.toString() + " - " + dniPaciente + " - " + mensaje;
    }
}
