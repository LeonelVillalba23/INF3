# Guía 4 — Listas enlazadas y operaciones básicas

Este módulo implementa ejercicios y utilidades sobre listas enlazadas simples en Java: creación y manipulación de nodos, inserciones (inicio, final, posición), búsqueda, eliminación (por valor y duplicados), conteo, inversión y una aplicación práctica de registro de alumnos. El objetivo es practicar operaciones fundamentales sobre estructuras enlazadas y separar responsabilidades por clase para facilitar la comprensión.

Archivos en /workspaces/INF3/Guia4/src
- AplicacionPractica.java
- BuscarUnValor.java
- ContarElementos.java
- CrearNodo.java
- EliminarDuplicados.java
- EliminarPorValor.java
- IncertarInicio.java
- InsertarEnPosicion.java
- InsertarFinal.java
- InvertirLista.java
- ListaEnlazada.java
- Main.java

## Resumen por archivo (qué hace y procedimiento usado — breve)

### CrearNodo.java
- Qué hace:
  - Representa el nodo básico de la lista: almacena un `int dato` y una referencia `CrearNodo enlace`.
- Procedimiento:
  - Constructores (con dato y por defecto), getters/setters para dato/enlace y `toString()` que imprime la sublista desde ese nodo.
- Consideraciones:
  - Es la unidad mínima; las demás clases usan instancias para construir listas.

### ListaEnlazada.java
- Qué hace:
  - Implementa operaciones genéricas sobre una lista enlazada simple: insertarInicio, insertarFinal, eliminarPorValor, buscar, contar, invertir, insertarEn, eliminarDuplicados, imprimir.
- Procedimiento:
  - Mantiene referencia `inicio`.
  - Las operaciones iteran sobre la lista con punteros temporales (`actual`, `prev`) para insertar, eliminar o buscar en O(n).
- Consideraciones:
  - Diseño centralizado para reusar en ejercicios y en el `Main`.

### InsertarFinal.java
- Qué hace:
  - Clase focalizada en insertar nodos al final y mostrar la lista.
- Procedimiento:
  - Si `inicio == null` asigna el nuevo nodo a inicio; si no, recorre hasta el fin y enlaza el nuevo nodo.
- Complejidad:
  - O(n) por inserción si no se mantiene referencia a la cola.

### IncertarInicio.java
- Qué hace:
  - Inserta un nodo al inicio de la lista.
- Procedimiento:
  - Crea nodo y enlaza su `enlace` al `inicio` actual, luego actualiza `inicio` al nuevo nodo.
- Complejidad:
  - O(1).

### InsertarEnPosicion.java
- Qué hace:
  - Inserta un nodo en una posición específica (índice) dentro de la lista.
- Procedimiento:
  - Valida posición; recorre hasta el nodo anterior a la posición y ajusta enlaces.
- Complejidad:
  - O(n) en el peor caso.

### BuscarUnValor.java
- Qué hace:
  - Inserta al final (para poblar) y busca si existe un valor en la lista.
- Procedimiento:
  - Recorre desde `inicio` comparando `dato` con el valor buscado; devuelve boolean.
- Complejidad:
  - O(n).

### ContarElementos.java
- Qué hace:
  - Inserta nodos (ej. al inicio) y cuenta los elementos de la lista.
- Procedimiento:
  - Recorre la lista incrementando un contador hasta `null`.
- Complejidad:
  - O(n).

### EliminarPorValor.java
- Qué hace:
  - Elimina el primer nodo que contenga un valor dado.
- Procedimiento:
  - Recorre con `actual` y `prev`; si se encuentra, ajusta `prev.enlace` o `inicio` para eliminar el nodo.
- Complejidad:
  - O(n).

### EliminarDuplicados.java
- Qué hace:
  - Elimina nodos duplicados de la lista (manteniendo la primera aparición).
- Procedimiento:
  - Para cada nodo, recorre los siguientes y elimina nodos con el mismo `dato` ajustando enlaces; O(n^2) en la implementación simple.
- Consideraciones:
  - Para mejor rendimiento usar un conjunto auxiliar (HashSet) y lograr O(n) tiempo y O(n) espacio.

### InvertirLista.java
- Qué hace:
  - Inserta al final y ofrece método para invertir la lista enlazada.
- Procedimiento:
  - Inversión iterativa: recorre la lista y para cada nodo hace `actual.enlace = prev`, avanzando punteros `prev`, `actual`.
- Complejidad:
  - O(n) tiempo, O(1) espacio extra.

### AplicacionPractica.java
- Qué hace:
  - Contiene clases internas para una pequeña aplicación de registro de alumnos:
    - `Alumno` (nombre, legajo)
    - `ListaAlumnos` (lista enlazada simple con agregar, buscar por legajo, eliminar por legajo e imprimir)
- Procedimiento:
  - `ListaAlumnos` usa nodos para almacenar `Alumno` y proporciona operaciones típicas (agregar al final, buscar recorriendo la lista, eliminar ajustando enlaces).
- Uso:
  - Ejemplo en `Main` para poblar lista de alumnos, buscar y eliminar por legajo.

### Main.java
- Qué hace:
  - Menú interactivo en consola que agrupa los ejercicios 1..10 sobre listas enlazadas y la aplicación práctica.
- Procedimiento:
  - Lee opciones con `Scanner`, crea instancias de `ListaEnlazada` o `AplicacionPractica.ListaAlumnos` y llama los métodos correspondientes para probar cada ejercicio.
- Consideraciones:
  - Es el punto de entrada pensado para pruebas manuales y demostraciones educativas.

## Decisiones de diseño y notas
- Cada ejercicio se encapsula en su propia clase para facilitar pruebas y lectura.
- Muchas operaciones básicas usan recorridos iterativos con referencias temporales (`actual`, `prev`).
- Validaciones mínimas: es recomendable añadir chequeos de entrada (posiciones fuera de rango, listas vacías) y pruebas unitarias.
- Complejidad típica:
  - Operaciones puntuales en lista enlazada: O(1) (insertar inicio), O(n) (insertar final, buscar, eliminar, contar, invertir).
  - Eliminación de duplicados sin DS auxiliar: O(n^2); con HashSet: O(n).

## Cómo compilar y ejecutar
Desde el directorio del proyecto:
```bash
cd /workspaces/INF3/Guia4/src
javac *.java
java Main
```
