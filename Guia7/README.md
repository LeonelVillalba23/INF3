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
Ejercicio1: inserciones y extracción básica.
Muestra cómo insertar varios elementos en MinHeap y cómo extraer la raíz repetidamente. Demuestra el cambio del arreglo interno tras cada add/poll y permite ver la propiedad de heap mínima en acción. Útil para entender el flujo básico de inserción y extracción y las complejidades asociadas.

Ejercicio2: percolateUp paso a paso.
Inserta elementos y explica (imprime) cada intercambio que realiza percolateUp hasta restaurar la propiedad del heap. Enseña el criterio de comparación entre nodo y padre, y por qué la inserción tiene coste O(log n). Ideal para visualizar el proceso de burbujeo hacia arriba.

Ejercicio3: percolateDown paso a paso.
Extrae la raíz o modifica la raíz y muestra cada paso de percolateDown mientras el elemento baja por el árbol. Ilustra la elección entre hijo izquierdo/derecho en cada comparación y cómo se restaura la estructura. Sirve para comprender la eliminación de la raíz y la reestructuración del heap.

Ejercicio4: visualización del heap como árbol.
Construye un heap y lo imprime en formato de árbol (niveles alineados) además de mostrar el arreglo interno. Permite correlacionar índices del arreglo con posiciones en el árbol, facilitando la explicación visual de parent/child y relaciones de recorrido.

Ejercicio5: heapify desde un arreglo.
Toma un arreglo arbitrario y aplica heapify bottom‑up para convertirlo en un MinHeap en tiempo O(n). Muestra los nodos que se ajustan desde el último padre hacia la raíz y compara coste frente a insertar elementos uno a uno. Explica por qué el enfoque bottom‑up es más eficiente.

Ejercicio6: heapsort con el heap.
Usa MinHeap para ordenar una colección: construye el heap y extrae elementos sucesivamente para generar una secuencia ordenada. Expone la relación entre heap y ordenamiento, y discute la complejidad O(n log n) y las características prácticas frente a otros algoritmos.

Ejercicio7: uso de MaxHeap (orden descendente).
Implementa MaxHeap análogo a MinHeap invirtiendo la comparación y demuestra extracción en orden descendente. Permite comparar comportamiento y código con MinHeap y ver cómo basta cambiar el criterio de comparación para obtener la otra variante.

Ejercicio8: cola de prioridad aplicada a pacientes.
Modela pacientes como objetos con prioridad y muestra en un caso práctico cómo la AgendaTareas gestiona la cola. Incluye ejemplos de inserción de pacientes, consulta del próximo y atención (poll), demostrando uso real de la estructura en gestión de urgencias.

Ejercicio9: traza del estado interno del heap.
Registra y muestra el estado del arreglo interno y los movimientos elementales durante múltiples operaciones (inserciones/extracciones). Pensado para depuración y para explicar paso a paso cómo cambian índices y valores en cada operación.

Ejercicio10: interfaz interactiva de AgendaTareas.
Provee un pequeño menú para agregar tareas, consultar la próxima, completar (extraer) y listar pendientes. Integra Tarea y AgendaTareas para mostrar un caso de uso completo: entrada de datos, mantenimiento de la prioridad y salida formateada para el usuario. Ideal para demostrar la aplicación práctica y la separación entre modelo (Tarea) y estructura (AgendaTareas).

# ORGANIZACIÓN DEL TRABAJO
- Dividí el contenido en unidades conceptuales y desarrollé demos independientes.  
- Implementé primero operaciones básicas, luego utilidades (heapify, printTree) y finalmente la aplicación (agenda).  
- Busqué mantener el código legible para explicar fácilmente la relación índice ↔ estructura de árbol.

# CONCLUSIÓN
El proyecto progresa desde operaciones básicas de heap hasta una aplicación práctica (agenda). La implementación con arreglos y métodos iterativos facilita la comprensión y la exposición oral.
