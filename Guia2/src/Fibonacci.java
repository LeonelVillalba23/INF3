public class Fibonacci {
    // Versión recursiva simple
    /*public static int fibonacci(int n) {
        if (n <= 0) {
            return 0; // Caso base: Fibonacci(0) = 0
        } else if (n == 1) {
            return 1; // Caso base: Fibonacci(1) = 1
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2); // Llamada recursiva
        }
    }*/

    // Versión con memorización
    public static long fibonacciMemo(int n) {
        long[] memo = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return fibonacciMemoHelper(n, memo);
    }

    private static long fibonacciMemoHelper(int n, long[] memo) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = fibonacciMemoHelper(n - 1, memo) + fibonacciMemoHelper(n - 2, memo);
        return memo[n];
    }
}