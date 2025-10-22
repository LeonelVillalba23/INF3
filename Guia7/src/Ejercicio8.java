public class Ejercicio8 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 8: Cola de prioridad de pacientes ===");
        ColaPacientes cola = new ColaPacientes();
        cola.ingresar(new Paciente("Juan", 2));
        cola.ingresar(new Paciente("Ana", 1));
        cola.ingresar(new Paciente("Luis", 3));
        cola.ingresar(new Paciente("Sofía", 1));
        cola.ingresar(new Paciente("Carlos", 2));
        System.out.println("\nAtendiendo pacientes según prioridad:");
        while (!cola.isEmpty()) {
            Paciente p = cola.atender();
            System.out.println("→ Atendido: " + p);
        }
    }
}
