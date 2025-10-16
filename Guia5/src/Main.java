import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       
    int opc;

    try (Scanner scanner = new Scanner(System.in)) {

    do {
        System.out.println("\n=== Menu de Ejercicios AVL ===");
        System.out.println("1. Inserciones y FE LL/RR");
        System.out.println("2. Insercion con rotaciones LR/RL");
        System.out.println("3. Secuencia ordenada y efecto peinar");
        System.out.println("4. Eliminacion con rebalanceo");
        System.out.println("5. Verificacion AVL y alturas");
        System.out.println("6. Factor de equilibrio completo");
        System.out.println("7. Implementacion guiada: Rotacion izquierdo");
        System.out.println("8. Implementacion guiada: Rotacion doble izquierda- derecha");
        System.out.println("9. Costo y altura");
        System.out.println("10. Secuencias estresantes y pruebas unitarias");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
        opc = scanner.nextInt();

        switch(opc){
            case 1:
                InsercionesYFE_LL_RR.ejecutar();
                break;
            case 2:
                Ej2_RotDoble_LR_RL.ejecutar();  
            break;
            case 3:
                Ej3_EfectoPeinar.ejecutar();
                break;
            case 4:
                Ej4_EliminacionRebalanceo.ejecutar();
                break;
            case 5:
                Ej5_ComprobadorAVL.ejecutar();
                break;
            case 6:
                Ej6_FECompleto.ejecutar();  
                break;
            case 7:
                Ej7_RotacionSimple.ejecutar();
                break;  
            case 8:
                Ej8_RotacionDobleLR.ejecutar();
                break;  
            case 9:
                Ej9_Teoria.ejecutar();  
                break;
            case 10:
                Ej10_Tests.ejecutar();
                break;  

            case 0:
                System.out.println("Saliendo.");
                break;
            default:
                System.out.println("Opcion no valida. Intente de nuevo.");
        }

    } while (opc != 0);
    }
    }
}
