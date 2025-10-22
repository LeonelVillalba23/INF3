import java.util.Scanner;

public class Ejercicio10 {
    public static void ejecutar() {
        System.out.println("\n=== EJERCICIO 10: Agenda de tareas con prioridad ===");
        AgendaTareas agenda = new AgendaTareas();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENÚ DE TAREAS ---");
            System.out.println("1) Agregar tarea");
            System.out.println("2) Ver próxima tarea urgente (peek)");
            System.out.println("3) Completar tarea más urgente (poll)");
            System.out.println("4) Mostrar todas las tareas pendientes");
            System.out.println("0) Volver al menú principal");
            System.out.print("Opción: ");
            int opt = sc.nextInt();
            sc.nextLine();

            if (opt == 0) break;
            else if (opt == 1) {
                System.out.print("Descripción: ");
                String desc = sc.nextLine();
                System.out.print("Prioridad (1=alta, 2=media, 3=baja): ");
                int p = sc.nextInt();
                sc.nextLine();
                agenda.agregar(new Tarea(desc, p));
            } else if (opt == 2) {
                Tarea t = agenda.verProxima();
                System.out.println(t == null ? "No hay tareas." : "Próxima tarea: " + t);
            } else if (opt == 3) {
                Tarea t = agenda.completar();
                System.out.println(t == null ? "No hay tareas." : "Completada: " + t);
            } else if (opt == 4) {
                agenda.mostrarPendientes();
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }
}
