import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1) Ejercicio 1 - Montículo básico");
            System.out.println("2) Ejercicio 2 - percolateUp");
            System.out.println("3) Ejercicio 3 - percolateDown");
            System.out.println("4) Ejercicio 4 - Mostrar heap como árbol");
            System.out.println("5) Ejercicio 5 - Heapify");
            System.out.println("6) Ejercicio 6 - Heapsort");
            System.out.println("7) Ejercicio 7 - MaxHeap");
            System.out.println("8) Ejercicio 8 - Cola de prioridad de pacientes");
            System.out.println("9) Ejercicio 9 - Estado interno del heap");
            System.out.println("10) Ejercicio 10 - Agenda de tareas con prioridad");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            int opt = sc.nextInt();

            if (opt == 0) break;
            else if (opt == 1) Ejercicio1.ejecutar();
            else if (opt == 2) Ejercicio2.ejecutar();
            else if (opt == 3) Ejercicio3.ejecutar();
            else if (opt == 4) Ejercicio4.ejecutar();
            else if (opt == 5) Ejercicio5.ejecutar();
            else if (opt == 6) Ejercicio6.ejecutar();
            else if (opt == 7) Ejercicio7.ejecutar();
            else if (opt == 8) Ejercicio8.ejecutar();
            else if (opt == 9) Ejercicio9.ejecutar();
            else if (opt == 10) Ejercicio10.ejecutar();
            else System.out.println("Opción inválida.");
        }

        sc.close();
    }
}
