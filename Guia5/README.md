# Guía 5 — Árboles AVL (resumen para exposición)

Descripción breve
-----------------
Colección de ejercicios pedagógicos sobre árboles AVL. Implementa inserciones, eliminaciones, rotaciones (simples y dobles), cálculo del factor de equilibrio y pruebas de stress. Pensado para demostrar por consola cómo AVL mantiene el balance.

Qué hace el código (nivel general)
---------------------------------
- Implementa un árbol binario de búsqueda auto‑balanceado (AVL).  
- Permite insertar y eliminar claves, manteniendo la propiedad AVL mediante rotaciones.  
- Muestra trazas por consola para seguir cambios en alturas y factores de equilibrio (FE).  
- Incluye demos individuales para cada caso (LL/RR, LR/RL, efecto peinar, rebalanceo, tests).

Logística técnica — cómo está organizado y por qué
------------------------------------------------
- Estructura principal:
  - `AVLNode`: nodo con clave, hijos y altura (estado local).  
  - `AVLTree`: toda la lógica algorítmica (inserción, eliminación, rotaciones, verificación).  
  - `Main`: menú interactivo que llama demos concretas (`ejecutar()`).
  - `Ej*` (p. ej. `InsercionesYFE_LL_RR`, `Ej2_RotDoble_LR_RL`, `Ej4_EliminacionRebalanceo`, `Ej10_Tests`): cada clase es una demostración o conjunto de pruebas.
- Recursividad:
  - Inserción y eliminación se implementan de forma recursiva. Cada llamada devuelve la raíz actualizada del subárbol, lo que facilita actualizar alturas bottom‑up y detectar el primer nodo desbalanceado.
- Rotaciones:
  - Rotaciones simples (LL/RR) y dobles (LR/RL) implementadas como operaciones que reciben y retornan la nueva raíz del subárbol.
- Mantenimiento de altura / FE:
  - Altura actualizada al retornar en la recursión; FE = altura(derecha) - altura(izquierda).
- Casos manejados:
  - Nodo hoja, 1 hijo, 2 hijos; política ante duplicados: (indicar en tu defensa si se ignoran o actualizan).
- Complejidad:
  - Mantiene altura O(log n) → operaciones en O(log n).

Por qué organizamos cada clase así
---------------------------------
- Single responsibility: cada `Ej*` es una demo autocontenida; facilita explicar un concepto en 1–2 minutos.  
- `AVLTree` concentra la lógica para mantener el balance y facilita pruebas unitarias.  
- `Main` sirve para ejecutar demos sin editar código durante la exposición.

Guion corto para la exposición (1 minuto)
----------------------------------------
1. Objetivo (10–15s): "Demostrar cómo AVL mantiene el balance tras inserciones/eliminaciones."  
2. Mostrar `Main` (5s) y elegir 2 demos: una inserción que produce una rotación y una eliminación que rebalancea (30–35s).  
3. Explicar recursividad + FE (20–25s): "la inserción sube por la recursión, actualiza alturas, detecta FE fuera de [-1,1] y aplica rotación."  
4. Concluir (10s): complejidad O(log n) y que `Ej10_Tests` valida casos extremos.

Diapositiva lista (6 bullets)
-----------------------------
- Objetivo: demostrar que AVL mantiene balance tras operaciones.  
- Estructura: `AVLNode` (estado), `AVLTree` (lógica), `Ej*` (demos).  
- Algoritmo: inserción/eliminación recursiva + rebalanceo bottom‑up.  
- Rotaciones: LL/RR (simples), LR/RL (dobles) — aplicadas cuando FE ∉ [-1,1].  
- Estado local: cada nodo guarda altura; FE = h(d) - h(i).  
- Resultado: altura O(log n) → operaciones O(log n); `Ej10` prueba robustez.

Notas rápidas que puedes decir en la defensa
-------------------------------------------
- "Usamos recursividad para propagar alturas y detectar el primer desbalance desde abajo hacia arriba."  
- "Las rotaciones son operaciones locales que reestructuran subárboles y actualizan alturas."  
- "Cada demo está separada para poder explicar un caso concreto sin distracciones."
