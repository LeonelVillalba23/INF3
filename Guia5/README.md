# Práctico Árbol AVL — Guia4

Este repositorio contiene implementaciones y ejercicios para trabajar con árboles AVL.

Estructura principal (archivos .java en `src`):

- `AVLNode.java` — Nodo AVL básico.
- `AVLTree.java` — Implementación de AVL con: insert, delete, rotaciones simples/dobles, `esAVL()`, `prettyPrint()` y contador de rotaciones.
- `Ej1_InsercionesLL_RR.java` — Ejercicio 1: inserciones (LL y RR) paso a paso.
- `Ej2_RotDoble_LR_RL.java` — Ejercicio 2: rotaciones dobles (LR y RL).
- `Ej3_EfectoPeinar.java` — Ejercicio 3: secuencia ordenada / efecto "peinar".
- `Ej4_EliminacionRebalanceo.java` — Ejercicio 4: eliminación con rebalanceo.
- `Ej5_ComprobadorAVL.java` — Ejercicio 5: comprobador `esAVL` y ejemplos.
- `Ej6_FECompleto.java` — Ejercicio 6: listar (valor, altura, FE) y marcar nodos críticos.
- `Ej7_RotacionSimple.java` — Ejercicio 7: rotación simple (RR/RL) demostración.
- `Ej8_RotacionDobleLR.java` — Ejercicio 8: rotación doble LR demostración.
- `Ej9_Teoria.java` — Ejercicio 9: respuestas teóricas.
- `Ej10_Tests.java` — Ejercicio 10: secuencias estresantes y conteo de rotaciones.

Importante: según tu indicación, no he compilado los archivos ni generado `.class` en el repositorio. Cuando quieras compilar/ejecutar localmente en PowerShell puedes usar los siguientes comandos desde la carpeta `src`:

```powershell
cd "c:\IUA\Info 3\Guia4\src"
javac *.java    # Genera archivos .class
java Ej1_InsercionesLL_RR
java Ej2_RotDoble_LR_RL
```

Si prefieres no generar `.class` en el repo, compila/ejecuta localmente en tu máquina y elimina los `.class` antes de commitear, o añade/asegúrate de que `.gitignore` contiene `*.class` (ya incluido).

Qué hice en el repo (sin compilar):
- Implementé y mejoré `AVLTree.java` con rotaciones simples y dobles, `delete`, `esAVL()` y utilidades de impresión.
- Rellené los mains para los 10 ejercicios (`Ej1_...` a `Ej10_...`) para que muestren paso a paso lo pedido en la consigna.

Siguientes pasos opcionales (elige):
- Puedo compilar y ejecutar los tests ahora y compartir la salida (aceptarás que se generen `.class`).
- Puedo añadir tests JUnit y un script de ejecución automatizada.
- Puedo ajustar o extender la salida textual (más dibujitos del árbol, export a PNG, etc.).
