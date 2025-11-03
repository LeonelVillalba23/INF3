Introducción
Este proyecto implementa y demuestra montículos y una agenda por prioridad. El código está organizado en clases pequeñas y ejercicios independientes para ilustrar cada concepto. A continuación está el texto para leer en la presentación.

Estructura del proyecto
Main: punto de entrada con menú que ejecuta los diez ejercicios.
MinHeap: montículo mínimo implementado sobre arreglo.
MaxHeap: montículo máximo implementado sobre arreglo.
Tarea: modelo simple con descripción y prioridad.
AgendaTareas: cola de prioridad de tareas basada en ArrayList.
Ejercicio1 a Ejercicio10: demos independientes que muestran operaciones y algoritmos relacionados con heaps y la agenda.
Diseño y decisiones principales
Elegí representar los montículos con arreglos y ArrayList para aprovechar accesos por índice y cálculos parent/child eficientes.
Implementé operaciones iterativas indexadas en lugar de recursivas para claridad y control del flujo.
El código está modularizado: estructura del heap en clases reutilizables y ejercicios que prueban casos concretos.
Prioricé claridad didáctica en lugar de optimizaciones prematuras.
Conceptos y técnicas implementadas
percolateUp para mantener la propiedad del heap al insertar.
percolateDown para restaurar el heap tras extraer la raíz.
heapify bottom-up para construir un heap en tiempo O(n).
heapsort usando extracciones sucesivas para ordenar.
Complejidades relevantes: add O(log n), poll O(log n), peek O(1), heapify O(n), heapsort O(n log n).
Resumen de cada clase y cómo lo pensé
MinHeap: implementé add, peek, poll, mostrar como array y printTree. Pensé en métodos claros para percolateUp y percolateDown y en una versión de heapify que recibe un arreglo.
MaxHeap: adaptación de MinHeap donde la comparación invierte el criterio. Mantiene la misma estructura y métodos para facilitar comparación entre ambos.
Tarea: POJO minimalista con los atributos necesarios para la agenda. Decidí mantenerlo simple para que la lógica de la agenda quede separada.
AgendaTareas: cola de prioridad sobre ArrayList que ordena por prioridad numérica. Implementé métodos agregar, peek, poll y listar pendientes y reutilicé percolateUp/percolateDown para mantener la propiedad de prioridad.
Resumen de los ejercicios y su propósito
Ejercicio1: muestra inserciones y extracción básica en MinHeap.
Ejercicio2: ilustra percolateUp paso a paso.
Ejercicio3: ilustra percolateDown paso a paso.
Ejercicio4: visualiza el heap como árbol para entender la estructura lógica.
Ejercicio5: demuestra heapify bottom-up a partir de un arreglo.
Ejercicio6: muestra heapsort usando el heap para ordenar.
Ejercicio7: muestra uso de MaxHeap para extraer en orden descendente.
Ejercicio8: adapta la cola de prioridad a un caso de pacientes para ejemplificar uso práctico.
Ejercicio9: traza cambios internos del heap durante operaciones para depuración y comprensión.
Ejercicio10: interfaz interactiva de AgendaTareas para agregar, consultar y completar tareas.
Organización del trabajo y pensamiento
Dividí el material en unidades conceptuales y escribí demos independientes para cada idea.
Primero implementé las operaciones básicas del heap, luego construí utilidades como heapify y printTree.
Después diseñé la agenda usando la implementación del heap para aplicar el concepto en un caso real.
En cada paso busqué claridad del código para poder explicar fácilmente la relación entre índice y estructura de árbol.
Conclusión
El proyecto enseña de forma progresiva cómo construir y usar montículos y una cola de prioridad, partiendo de operaciones básicas hasta aplicaciones prácticas. La implementación con arreglos y métodos iterativos facilita la explicación y la demostración en los ejercicios.
