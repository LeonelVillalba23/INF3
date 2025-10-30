public class InversorCadena {
    public static String invertir(String cadena) {
        if (cadena.isEmpty()) {
            return cadena;
        } else {
            return cadena.charAt(cadena.length() - 1) + 
            invertir(cadena.substring(0, cadena.length() - 1));
        }
    }
}