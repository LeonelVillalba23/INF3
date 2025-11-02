# Guía 2 — Operaciones recursivas y utilidades sobre arreglos/cadenas

Este módulo contiene varias utilidades implementadas principalmente con recursión: conteo de dígitos, inversión de cadenas, búsquedas y operaciones sobre arreglos, cálculo de MCD y Fibonacci (con memorización). Se separan responsabilidades por clase para mantener el código simple y testeable.

Archivos principales (ubicados en /workspaces/INF3/Guia2/src)

- BuscarArreglo.java
- SumadorArreglo.java
- Palindromo.java
- CalculadorMCD.java
- ContadorDigitos.java
- ConversorBinario.java
- Fibonacci.java
- InversorCadena.java
- Main.java

## Descripción de clases y procedimiento usado (breve)

### BuscarArreglo (BuscarArreglo.java)
- Qué hace:
  - Determina si un valor objetivo aparece entre los primeros `n` elementos de un arreglo de enteros.
- Procedimiento:
  - Implementación recursiva decreciente: caso base `n <= 0` → `false`.
  - Compara `arreglo[n-1]` con `objetivo`; si coincide → `true`; si no → llamada recursiva con `n-1`.
- Consideraciones:
  - Requiere `arreglo != null` y `0 <= n <= arreglo.length` para evitar excepciones.
- Complejidad:
  - Tiempo O(n), espacio O(n) (pila de llamadas).

### SumadorArreglo (SumadorArreglo.java)
- Qué hace:
  - `sumar(int[] arreglo, int n)`: suma recursiva de los primeros `n` elementos.
  - `promedio(int[] arreglo)`: calcula el promedio (retorna 0 si el arreglo está vacío).
- Procedimiento:
  - `sumar`: caso base `n <= 0` → 0; retorna `arreglo[n-1] + sumar(arreglo, n-1)`.
  - `promedio`: usa `sumar(arreglo, arreglo.length)` y divide como `float`.
- Consideraciones:
  - Validar arreglo no nulo; para arreglos grandes, la recursión puede desbordar pila.
- Complejidad:
  - Tiempo O(n), espacio O(n).

### Palindromo (Palindromo.java)
- Qué hace:
  - Verifica si una cadena es palíndromo ignorando espacios y mayúsculas/minúsculas.
- Procedimiento:
  - Normaliza: `replaceAll("\\s+", "")` + `toLowerCase()`.
  - Recursión con dos índices (`inicio`, `fin`): caso base `inicio >= fin` → true; compara caracteres y avanza.
- Complejidad:
  - Tiempo O(n), espacio O(n).

### CalculadorMCD (CalculadorMCD.java)
- Qué hace:
  - Calcula el máximo común divisor (MCD) de dos enteros usando el algoritmo de Euclides.
- Procedimiento:
  - Recursión: caso base `b == 0` → retorna `a`; else `mcd(b, a % b)`.
- Consideraciones:
  - Funciona para enteros (posibles ajustes si se admiten negativos).
- Complejidad:
  - Tiempo O(log min(a,b)) en promedio, espacio O(log min(a,b)).

### ContadorDigitos (ContadorDigitos.java)
- Qué hace:
  - Cuenta la cantidad de dígitos de un número entero positivo.
- Procedimiento:
  - Recursión: caso base `numero < 10` → 1; else `1 + contar(numero / 10)`.
- Consideraciones:
  - Diseñado para enteros no negativos; para negativos convertir a positivo previamente.
- Complejidad:
  - Tiempo O(d) donde d es el número de dígitos, espacio O(d).

### ConversorBinario (ConversorBinario.java)
- Qué hace:
  - Convierte un entero decimal a su representación binaria en forma de cadena.
- Procedimiento:
  - Recursión: casos base 0->"0", 1->"1"; else `decimalABinario(n/2) + (n%2)`.
- Consideraciones:
  - Para números grandes puede producir cadenas largas; no maneja negativos.
- Complejidad:
  - Tiempo O(log n), espacio O(log n).

### Fibonacci (Fibonacci.java)
- Qué hace:
  - Calcula Fibonacci n usando memorización (DP top-down).
- Procedimiento:
  - Crea array `memo` inicializado a -1; función helper recursiva que guarda resultados.
  - Evita la exponencialidad de la recursión simple.
- Consideraciones:
  - Retorna `long`; el tamaño `n` debe ser razonable para evitar overflow.
- Complejidad:
  - Tiempo O(n), espacio O(n).

### InversorCadena (InversorCadena.java)
- Qué hace:
  - Invierte una cadena mediante recursión.
- Procedimiento:
  - Caso base `isEmpty()` -> retorna cadena; else toma último carácter + invertir(subcadena sin último).
- Consideraciones:
  - Para cadenas largas, la recursión consume pila; versiones iterativas son más seguras.
- Complejidad:
  - Tiempo O(n), espacio O(n).

### Main (Main.java)
- Qué hace:
  - Menú por consola con opciones para invocar las utilidades (conteo, invertir cadena, suma, MCD, conversión binaria, palíndromo, Fibonacci, búsqueda).
- Procedimiento:
  - Bucle `do-while` que muestra menú y lee opción con `Scanner`.
  - Actualmente sólo muestra el menú y espera opción; integrar llamadas a las clases según opción es directo (invocar métodos estáticos).
- Consideraciones:
  - Recordar limpiar buffer (`scanner.nextLine()` tras `nextInt()`).

## Flujo y decisiones de diseño (resumen)
- Predominancia de soluciones recursivas para ilustrar técnicas: recursión simple y memorización.
- Separación por responsabilidad: cada clase es pequeña y hace una tarea concreta.
- Para producción preferir:
  - Validaciones (null, negativos) y manejo de errores.
  - Versiones iterativas cuando el tamaño de entrada pueda causar desbordamiento de pila.
  - Documentar semántica (por ejemplo, `promedio` retorna 0 para arreglo vacío).

## Complejidad general (resumen)
- Operaciones lineales (sumar, buscar, palíndromo, invertir, contar dígitos): O(n) tiempo, O(n) espacio por recursión.
- MCD: O(log min(a,b)).
- Fibonacci con memo: O(n).

## Ejecución rápida
Desde la carpeta del proyecto:
```bash
cd /workspaces/INF3/Guia2/src
javac *.java
java Main
```

