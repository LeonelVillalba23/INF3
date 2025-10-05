public class BuscarArreglo {
    public static boolean buscar(int[] arreglo, int n, int objetivo) {
        if (n <= 0) {
            return false;
        } else if (arreglo[n - 1] == objetivo) {
            return true;
        } else {
            return buscar(arreglo, n - 1, objetivo);
        }
    }
}