package src;

import java.time.LocalDateTime;

public class Accion {
    public enum TipoAccion {
        AGREGAR_TURNO,
        ELIMINAR_TURNO,
        MODIFICAR_TURNO,
        AGREGAR_PACIENTE,
        ELIMINAR_PACIENTE,
        MODIFICAR_PACIENTE
    }

    private TipoAccion tipo;
    private Object estadoAnterior; // Estado antes de la acción
    private Object estadoNuevo; // Estado después de la acción
    private LocalDateTime timestamp;
    private String descripcion;

    public Accion(TipoAccion tipo, Object estadoAnterior, Object estadoNuevo, String descripcion) {
        this.tipo = tipo;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.timestamp = LocalDateTime.now();
        this.descripcion = descripcion;
    }

    // Para deshacer, retornamos el estado anterior
    public Object deshacer() {
        return estadoAnterior;
    }

    // Para rehacer, retornamos el estado nuevo
    public Object rehacer() {
        return estadoNuevo;
    }

    @Override
    public String toString() {
        String fecha = timestamp.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        return String.format("[%s] %s - %s", fecha, tipo, descripcion);
    }

    public TipoAccion getTipo() {
        return tipo;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescripcion() {
        return descripcion;
    }
}