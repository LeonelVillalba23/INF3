import java.util.Scanner;

public class Main {
 public static void main(String[] args){

Scanner sc = new Scanner(System.in);
GestorTareas gestor = new GestorTareas();
int op;

do{
    System.out.println("\nMenu de opciones");
    System.out.println("1. Agregar tarea");
    System.out.println("2. Mostrar tareas");
    System.out.println("3. Marcar tarea como completada");
    System.out.println("4. Eliminar tareas completadas");
    System.out.println("5. Salir"); 
    System.out.print("Seleccione una opcion: ");
    op = sc.nextInt();
    sc.nextLine(); 
    switch(op){
        case 1:
            System.out.print("Ingrese la descripcion de la tarea: ");
            String descripcion = sc.nextLine();
            gestor.agregarTarea(descripcion);
            break;
        case 2:
            gestor.mostrarTareas();
            break;
        case 3:
            System.out.print("Ingrese el indice de la tarea a marcar como completada: ");
            int indice = sc.nextInt();
            gestor.marcarTareaCompletada(indice);
            break;
        case 4:
            gestor.eliminarCompletadas();
            break;
        case 5:
            System.out.println("Saliendo del programa...");
            break;
        default:
            System.out.println("Opcion invalida. Intente de nuevo.");
    }   




 }while(op !=5);

    sc.close();
    }
}

