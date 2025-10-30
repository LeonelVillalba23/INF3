public class CalculadorMCD {

    public static int mcd(int a, int b) {
        
        if (b == 0) {   //caso base
            return a;
        } else {
            // Llamada recursiva: MCD de b y el resto de a dividido b 
            return mcd(b, a % b);
        }
    }
}