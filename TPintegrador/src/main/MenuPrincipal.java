package main;

import java.util.Scanner;
import modulos.*; // vamos a ir creando estas clases después

public class MenuPrincipal {

    private Scanner scanner = new Scanner(System.in);
    private boolean salir = false;

    public void mostrarMenu() {
        while (!salir) {
            System.out.println("-------------------------------------------");
            System.out.println("MENÚ PRINCIPAL");
            System.out.println("-------------------------------------------");
            System.out.println("1) Ver agenda de un médico");
            System.out.println("2) Buscar próximo turno disponible");
            System.out.println("3) Simular sala de espera");
            System.out.println("4) Programar recordatorios");
            System.out.println("5) Consultar índice de pacientes (Hash)");
            System.out.println("6) Consolidador de agendas");
            System.out.println("7) Reportes de ordenamiento");
            System.out.println("8) Auditoría Undo/Redo");
            System.out.println("9) Planificador de quirófano");
            System.out.println("0) Salir");
            System.out.println("-------------------------------------------");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    ModuloAgenda.ejecutar();
                    break;
                case 2:
                    ModuloTurnosDisponibles.ejecutar();
                    break;
                case 3:
                    ModuloSalaEspera.ejecutar();
                    break;
                case 4:
                    ModuloRecordatorios.ejecutar();
                    break;
                case 5:
                    ModuloHashPacientes.ejecutar();
                    break;
                case 6:
                    ModuloConsolidador.ejecutar();
                    break;
                case 7:
                    ModuloReportes.ejecutar();
                    break;
                case 8:
                    ModuloUndoRedo.ejecutar();
                    break;
                case 9:
                    ModuloQuirofano.ejecutar();
                    break;
                case 0:
                    salir = true;
                    System.out.println("Fin de ejecución.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
