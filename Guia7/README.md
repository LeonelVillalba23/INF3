# INTRODUCCIÓN
Este proyecto demuestra montículos (heaps) y una agenda por prioridad. El código está dividido en clases pequeñas y ejercicios independientes que ilustran cada concepto.

# ESTRUCTURA DEL PROYECTO
- Main: menú que ejecuta los diez ejercicios.  
- MinHeap: montículo mínimo sobre arreglo.  
- MaxHeap: montículo máximo (criterio invertido).  
- Tarea: modelo simple (descripción, prioridad).  
- AgendaTareas: cola de prioridad basada en ArrayList.  
- Ejercicio1..Ejercicio10: demos independientes con casos prácticos.

# DISEÑO Y DECISIONES
- Usé arreglos / ArrayList para representar heaps por eficiencia en accesos por índice.  
- Operaciones iterativas (percolateUp/Down) para claridad y control del flujo.  
- Modularicé: clases reutilizables + ejercicios para cada idea.  
- Priorización: claridad didáctica antes que micro‑optimizaciones.

# CONCEPTOS CLAVE
- percolateUp: mantener propiedad del heap al insertar.  
- percolateDown: restaurar heap al extraer la raíz.  
- heapify bottom‑up: construir heap en O(n).  
- heapsort: ordenar mediante extracciones sucesivas.  
- Complejidades: add O(log n), poll O(log n), peek O(1), heapify O(n), heapsort O(n log n).

# CLASES Y PENSAMIENTO
- MinHeap: métodos add, peek, poll, printTree; diseñé percolateUp/Down claros y una versión heapify.  
- MaxHeap: misma estructura que MinHeap con comparación invertida para facilitar comparación.  
- Tarea: POJO mínimo para separar dato de lógica.  
- AgendaTareas: cola de prioridad por prioridad numérica; métodos agregar, peek, poll y listar; reutiliza percolateUp/Down.

# EJERCICIOS (PROPÓSITO)
- Ejercicio1: inserciones y extracción básica.  
- Ejercicio2: percolateUp paso a paso.  
- Ejercicio3: percolateDown paso a paso.  
- Ejercicio4: visualización del heap como árbol.  
- Ejercicio5: heapify desde un arreglo.  
- Ejercicio6: heapsort con el heap.  
- Ejercicio7: uso de MaxHeap (orden descendente).  
- Ejercicio8: cola de prioridad aplicada a pacientes.  
- Ejercicio9: traza del estado interno del heap.  
- Ejercicio10: interfaz interactiva de AgendaTareas.

# ORGANIZACIÓN DEL TRABAJO
- Dividí el contenido en unidades conceptuales y desarrollé demos independientes.  
- Implementé primero operaciones básicas, luego utilidades (heapify, printTree) y finalmente la aplicación (agenda).  
- Busqué mantener el código legible para explicar fácilmente la relación índice ↔ estructura de árbol.

# CONCLUSIÓN
El proyecto progresa desde operaciones básicas de heap hasta una aplicación práctica (agenda). La implementación con arreglos y métodos iterativos facilita la comprensión y la exposición oral.
