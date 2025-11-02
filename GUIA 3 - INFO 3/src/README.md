# Guía 3 — Pilas y Colas: implementaciones y simulaciones

Este módulo contiene implementaciones y ejercicios prácticos con pilas y colas: estructuras base (arreglo y circular), utilidades (invertir cadena, palíndromo con pila/cola), manejo de deshacer/rehacer con pilas y simulaciones (banco, impresora). Cada clase agrupa una responsabilidad concreta para facilitar pruebas y lectura.

Archivos principales (ubicados en /workspaces/INF3/GUIA 3 - INFO 3/src)
- ColaArreglo.java
- ColaCircularGestionLlamadas.java
- DeshacerRehacerPila.java
- ImplementacionPila.java
- InvertirCadenaPila.java
- PalindromoPilaCola.java
- SimulacionBancoSimple.java
- SimulacionImpresoraCola.java
- SimulacionTurnosCola.java
- Main.java

## Descripción de clases y procedimiento usado (breve)

### ColaArreglo (ColaArreglo.java)
- Qué hace:
  - Cola simple basada en un arreglo con operaciones básicas: enqueue, dequeue, top, isEmpty, isFull, size y toString.
- Procedimiento:
  - Mantiene índice `frente` y `tamano` (o `rear` según diseño) y controla capacidad fija.
  - `enqueue`: inserta al final si no está llena; lanza excepción si está llena.
  - `dequeue`: devuelve y elimina frente; lanza excepción si está vacía.
- Complejidad: operaciones O(1).

### ColaCircularGestionLlamadas (ColaCircularGestionLlamadas.java)
- Qué hace:
  - Cola circular para gestionar llamadas; encola sobrescribiendo la entrada más antigua cuando está llena (policy de rotación).
- Procedimiento:
  - Arreglo circular con índices `frente`, `rear` y contador `count`.
  - `enqueueOverwrite`: si está llena, avanza `frente` antes de escribir para sobrescribir el elemento más antiguo; actualiza `rear` y `count`.
  - `dequeue`: devuelve frente, actualiza `frente` y decrementa `count`.
- Uso: útil para buffers en tiempo real donde se prefiere los datos más recientes.

### DeshacerRehacerPila (DeshacerRehacerPila.java)
- Qué hace:
  - Implementa patrón undo/redo con dos pilas (`undo`, `redo`) guardando acciones como Strings.
- Procedimiento:
  - `doAction(accion)`: push en `undo` y limpia `redo`.
  - `undo()`: pop de `undo` y push en `redo` (devuelve acción deshecha o null si no hay).
  - `redo()`: pop de `redo` y push en `undo` (devuelve acción rehecha o null).
- Complejidad: operaciones O(1).

### ImplementacionPila (ImplementacionPila.java)
- Qué hace:
  - Pila basada en arreglo con operaciones `push`, `pop`, `isEmpty`, `isFull`.
- Procedimiento:
  - Controla `capacidad`, array `datos` y `cima` (índice).
  - `push` lanza excepción si llena; `pop` devuelve y decrementa `cima`.
- Uso: base para ejercicios (invertir cadenas, palíndromo, etc.).

### InvertirCadenaPila (InvertirCadenaPila.java)
- Qué hace:
  - Programa que invierte una cadena usando `ImplementacionPila`.
- Procedimiento:
  - Apila cada carácter de la cadena; luego desapila para reconstruir la cadena invertida.
- Complejidad: O(n) tiempo y espacio.

### PalindromoPilaCola (PalindromoPilaCola.java)
- Qué hace:
  - Comprueba si una frase es palíndromo usando una pila y una cola (comparando orden inverso y directo).
- Procedimiento:
  - Normaliza la entrada (elimina espacios, minúsculas).
  - Llena una pila y una cola con caracteres y compara desapilando/desencolando.
- Complejidad: O(n) tiempo, O(n) espacio.

### SimulacionBancoSimple (SimulacionBancoSimple.java)
- Qué hace:
  - Simula turnos en un banco usando `ColaArreglo` (ejemplo educativo).
- Procedimiento:
  - Encola clientes (ejemplo guarda primera letra del nombre para simplificar), atiende y muestra estado de la cola.
- Uso: demuestra comportamiento FIFO y representación de la cola.

### SimulacionImpresoraCola (SimulacionImpresoraCola.java)
- Qué hace:
  - Implementa una cola de impresora con arreglo circular (estructura con frente, final y capacidad).
- Procedimiento:
  - Encolado/desencolado circulares para gestionar trabajos de impresión en buffer fijo.
- Uso: ejemplo de cola circular clásica.

### SimulacionTurnosCola (SimulacionTurnosCola.java)
- Qué hace:
  - Simulación sencilla usando Java Queue (LinkedList) para ilustrar operaciones remove/add en una fila.
- Procedimiento:
  - Agrega clientes, remueve los atendidos y muestra estado antes/después.

### Main (Main.java)
- Qué hace:
  - Menú simple (esqueleto) pensado para crear/usar pila y cola y ejecutar utilidades (invertir, apilar, desencolar, etc.).
- Procedimiento:
  - Interfaz por consola con BufferedReader; integrar llamadas a las clases concretas según selección.

## Decisiones de diseño y notas
- Separación por responsabilidad: cada clase pequeña y enfocada facilita pruebas y mantenimiento.
- Algunas clases usan excepciones Runtime para errores (pila/cola llena o vacía).
- Variantes: la cola circular con overwrite es útil para buffers de eventos; la implementación estándar lanza excepción cuando está llena.
- En ambientes productivos: añadir validaciones de parámetros, manejo de inputs nulos y tests unitarios.

## Complejidad (resumen)
- Operaciones básicas en pilas/colas: O(1).
- Invertir/palíndromo/mostrar: O(n) tiempo y O(n) espacio (para estructuras auxiliares).
- Overwrite en cola circular: O(1).

## Ejecución rápida (desde el contenedor)
```bash
cd /workspaces/INF3/GUIA\ 3\ -\ INFO\ 3/src
javac *.java
# Ejecutar un ejemplo (p. ej. SimulacionTurnosCola):
java SimulacionTurnosCola
```

