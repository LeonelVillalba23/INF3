package src;

class Paciente {
    String dni;
    String nombre;

    public Paciente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + " (DNI: " + dni + ")";
    }
}