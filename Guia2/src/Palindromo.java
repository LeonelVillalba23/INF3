public class Palindromo {
    public static boolean esPalindromo(String cadena) {
        cadena = cadena.replaceAll("\\s+", "").toLowerCase();
        return esPalindromoRecursivo(cadena, 0, cadena.length() - 1);
    }

    private static boolean esPalindromoRecursivo(String cadena, int inicio, int fin) {
        if (inicio >= fin) {
            return true;
        }
        if (cadena.charAt(inicio) != cadena.charAt(fin)) {
            return false;
        }
        return esPalindromoRecursivo(cadena, inicio + 1, fin - 1);
    }
}