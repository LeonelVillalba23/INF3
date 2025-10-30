public class ContadorDigitos {
    public static int contar(int numero) {
        
        if(numero < 10){
            return 1;
        } else {
            return 1 + contar(numero / 10);
        }

    }
}