public class SumadorArreglo {
    public static int sumar(int[] arreglo, int n) {
        if (n <= 0) {
            return 0;
        } else {
            return arreglo[n - 1] + sumar(arreglo, n - 1);
        }
    }
    public static float promedio(int arreglo[]){

        if(arreglo.length == 0){
            return 0;
        }
        int total = sumar(arreglo, arreglo.length);
        return (float) total / arreglo.length;
    }
}
