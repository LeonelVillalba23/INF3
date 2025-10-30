public class ConversorBinario {
    public static String decimalABinario(int numero) {
        if (numero == 0) {
            return "0";
        } else if (numero == 1) {
            return "1";
        } else {
            return decimalABinario(numero / 2) + (numero % 2);
        }
    }
}