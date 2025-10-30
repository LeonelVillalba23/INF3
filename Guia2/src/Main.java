import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int op;

        do {
            System.out.println("1)Conteo de digitos");
            System.out.println("2)Invertir cadena");
            System.out.println("3)Suma de elementos de un arreglo");
            System.out.println("4)Maximo comun divisor");
            System.out.println("5)Conversion binaria");
            System.out.println("6)Palindromo");
            System.out.println("7)Fibonacci");
            System.out.println("8)Buscar en arreglo");
            System.out.println("9)Salir");
            System.out.print("Ingrese una opción: ");
            op = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer después de nextInt()


            switch(op){

            case 1: 
                System.out.print("Ingrese un numero: ");
                int numero = scanner.nextInt();
                int digitos = ContadorDigitos.contar(numero);
                System.out.println("El numero " + numero + " tiene " + digitos + " digitos.");
                break;

            case 2:
                System.out.print("Ingrese una cadena: ");
                // Ya se limpió el buffer antes del switch
                String cadena = scanner.nextLine();
                String cadenaInvertida = InversorCadena.invertir(cadena);
                System.out.println("Cadena invertida: " + cadenaInvertida);
                break;

            case 3:
                System.out.print("Ingrese el tamaño del arreglo: ");
                int n = scanner.nextInt();
                int[] arreglo = new int[n];

                for (int i = 0; i < n; i++) {
                    System.out.print("Ingrese el elemento " + (i + 1) + ": ");
                    arreglo[i] = scanner.nextInt();
                }
                int suma = SumadorArreglo.sumar(arreglo, n);
                float promedio = SumadorArreglo.promedio(arreglo);
                System.out.println("La suma de los elementos es: " + suma);
                System.out.println("El promedio de los elementos es: " + promedio);
                break;
            case 4:
                System.out.print("Ingrese el primer numero: ");
                int a = scanner.nextInt();
                System.out.print("Ingrese el segundo numero: ");
                int b = scanner.nextInt();
                int resultadoMCD = CalculadorMCD.mcd(a, b);
                System.out.println("El MCD de " + a + " y " + b + " es: " + resultadoMCD);
                break;
            case 5:
                System.out.print("Ingrese un numero decimal: ");
                int decimal = scanner.nextInt();
                String binario = ConversorBinario.decimalABinario(decimal);
                System.out.println("El numero binario es: " + binario);
                break;

                case 6:
                System.out.print("Ingrese una cadena: ");
                // Ya se limpió el buffer antes del switch
                String texto = scanner.nextLine();
                boolean esPalindromo = Palindromo.esPalindromo(texto);
                if (esPalindromo) {
                    System.out.println("La cadena es un palíndromo.");
                } else {
                    System.out.println("La cadena no es un palíndromo.");
                }
                break;

            case 7:
                System.out.print("Ingrese un numero para calcular su Fibonacci: ");
                int fibNum = scanner.nextInt();
                long fibResult = Fibonacci.fibonacciMemo(fibNum);
                System.out.println("El Fibonacci de " + fibNum + " es: " + fibResult);
                break;

            case 8:
                System.out.print("Ingrese el tamaño del arreglo: ");
                int size = scanner.nextInt();
                int[] arr = new int[size];

                for (int i = 0; i < size; i++) {
                    System.out.print("Ingrese el elemento " + (i + 1) + ": ");
                    arr[i] = scanner.nextInt();
                }

                System.out.print("Ingrese el numero a buscar: ");
                int target = scanner.nextInt();

                boolean encontrado = BuscarArreglo.buscar(arr, size, target);
                if (encontrado) {
                    System.out.println("El numero " + target + " fue encontrado en el arreglo.");
                } else {
                    System.out.println("El numero " + target + " no fue encontrado en el arreglo.");
                }
                break;
                

            case 9:
                System.out.println("Saliendo del programa.");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
                break;


            }
        } while(op != 9);
        scanner.close();
    }


    





}
