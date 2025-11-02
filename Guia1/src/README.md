Guía 1 — Gestor de Tareas
Este módulo implementa un gestor de tareas simple por consola. Se separan responsabilidades en:
Modelo: [`Tarea`](src/Tarea.java)
Lógica/servicio: [`GestorTareas`](src/GestorTareas.java)
Interfaz por consola: [`Main`](src/Main.java)

Clases
Tarea (src/Tarea.java)
Qué hace:
Representa una tarea con descripción y estado (pendiente/completada).
Valida que la descripción no sea nula ni vacía.
Expone getters, permite marcar como completada y define toString para mostrar su estado.
Procedimiento (breve):
Modelo con descripcion y completada=false al inicio.
Invariante: descripción válida.
toString muestra “Completada” o “Pendiente”.

GestorTareas (src/GestorTareas.java)
Qué hace:
Mantiene una lista de tareas (ArrayList<Tarea>).
Permite agregar, listar, marcar como completada por índice y eliminar completadas.
Maneja errores de entrada (descripciones inválidas, índices fuera de rango).
Procedimiento (breve):
ArrayList por simplicidad y acceso O(1).
Normaliza índice de usuario (base 1) a interno (base 0).
removeIf(t -> t.estaCompletada()) para limpiar en una pasada.

Main (src/Main.java)
Qué hace:
Menú por consola (1..5) para operar el gestor.
Orquesta llamadas a GestorTareas y gestiona Scanner.
Procedimiento (breve):
Bucle do-while + switch.
Limpia buffer (nextLine() tras nextInt()).
Cierra Scanner al salir.

Complejidad (resumen)
Agregar: O(1) amortizado.
Listar: O(n).
Marcar por índice: O(1).
Eliminar completadas: O(n).

Ejecución
# Desde Guia1/src
javac Main.java && java Main
