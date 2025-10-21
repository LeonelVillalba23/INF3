import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opc = -1;

        do{
            System.out.println("\n--- PRÁCTICO ÁRBOL ROJINEGRO ---");
            System.out.println("1) Nodo y NIL sentinel (No hay nodo)");
            System.out.println("2) Rotacion Izquierda");
            System.out.println("3) Rotacion Derecha");
            System.out.println("4) Insercion como ABB (sin balance)");
            System.out.println("5) Clasificador de caso para fixInsert");
            System.out.println("6) Recoloreo por tio rojo");
            System.out.println("7) Rotacion simple vs doble (un lado)");
            System.out.println("8) Successor y Predecessor");
            System.out.println("9) Consulta por rango [a,b]");
            System.out.println("10) Verificadores de invariantes");
            System.out.println("0) Salir");
            System.out.print("Elegí una opción: ");

            try{
                opc = Integer.parseInt(sc.nextLine().trim());
            }catch(Exception e){
                System.out.println("Entrada inválida. Por favor ingresa un número.");
                continue;
            }

            switch(opc){
                case 1:
                    System.out.println("1) Nodo y NIL sentinel (No hay nodo)");
                    Nodo.ejecutar();
                    break;
                case 2:
                    System.out.println("2) Rotacion Izquierda");
                    RotacionIzquierda.ejecutar();
                    break;
                case 3:
                    System.out.println("3) Rotacion Derecha");
                    RotacionDerecha.ejecutar();
                    break;
                case 4:
                    System.out.println("4) Insercion como ABB (sin balance)");
                    InsercionABB.ejecutar();
                    break;
                case 5:
                    System.out.println("5) Clasificador de caso para fixInsert");
                    ClasificadorCaso.ejecutar();
                    break;
                case 6:
                    System.out.println("6) Recoloreo por tio rojo");
                    RecoloreoTioRojo.ejecutar();
                    break;
                case 7:
                    System.out.println("7) Rotacion simple vs doble (un lado)");
                    RotacionSimpleDoble.ejecutar();
                    break;
                case 8:
                    System.out.println("8) Successor y Predecessor");
                    SuccessorPredecessor.ejecutar();
                    break;
                case 9:
                    System.out.println("9) Consulta por rango [a,b]");
                    ConsultaRango.ejecutar();
                    break;
                case 10:
                    System.out.println("10) Verificadores de invariantes");
                    VerificadorInvariantes.ejecutar();
                    break;
                case 0:
                    System.out.println("Saliendo.");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        }while(opc!=0);
    
        sc.close();
    }
}