Guía del módulo Integrador
Resumen
Este módulo integra estructuras y utilidades para manejar turnos médicos, pacientes y recordatorios. Contiene clases para representar entidades (Turno, Paciente, Medico), estructuras de datos (cola circular, heap indexado, hash con chaining), utilidades de ordenamiento y un historial de acciones para deshacer/rehacer. Los datos de ejemplo se encuentran en la carpeta DATA.

Archivos principales (ubicados en /workspaces/INF3/Integrador/src)
- Turno.java
- Paciente.java
- Medico.java
- Sorts.java
- SalaEspera.java
- Recordatorio.java
- Planner.java
- MapaPacientes.java
- Accion.java
- HistorialAcciones.java
- Main.java

Archivos de datos (ubicados en /workspaces/INF3/Integrador/DATA)
- Medico.csv
- Paciente.csv
- Turno.csv
- Turno_nube.csv

Descripción de clases y procedimiento usado (breve)

Turno (Turno.java)
Qué hace:
Representa un turno médico con id, fecha, hora, duración, dni de paciente, matrícula de médico y motivo.
Procedimiento:
Contenedor simple de campos y toString() para presentación.
Consideraciones:
Formato de fecha/hora es tratado por Main al parsear.

Paciente (Paciente.java)
Qué hace:
Modelo simple de paciente con dni y nombre.
Procedimiento:
Objeto ligero con constructor y toString().

Medico (Medico.java)
Qué hace:
Modelo simple de médico con matrícula, nombre y especialidad.
Procedimiento:
Objeto ligero con constructor y toString().

Sorts (Sorts.java)
Qué hace:
Colección de algoritmos de ordenamiento aplicables a listas de Turno.
Procedimiento:
Implementa insertion sort (estable), shell sort (gap n/2) y quicksort (partición Lomuto). También incluye un generador sintético de turnos para pruebas.
Consideraciones:
Los métodos reciben Comparator<Turno> para ordenar por distintos criterios. El generador reutiliza pacientes/medicos si existen.

SalaEspera (SalaEspera.java)
Qué hace:
Cola circular para representar la sala de espera con capacidad fija.
Procedimiento:
Usa buffer circular (front/rear/size). Al insertar cuando está llena, sobrescribe (pisar) el elemento más antiguo (overflow control).
Consideraciones:
Operaciones O(1); atiende() devuelve null si está vacía.

Recordatorio (Recordatorio.java)
Qué hace:
Representa un recordatorio con id, fecha (LocalDateTime), dniPaciente y mensaje.
Procedimiento:
POJO con constructor y toString().

Planner (Planner.java)
Qué hace:
Planner de recordatorios implementado como heap indexado para permitir push/pop y reprogramar en O(log n).
Procedimiento:
Mantiene lista como heap y un mapa id→índice para operaciones indexadas; implementa siftUp/siftDown, programar(), proximo(), reprogramar(), peek() y listado.
Consideraciones:
Permite reprogramar eficientemente usando el índice; ideal para agendas/colas temporales.

MapaPacientes (MapaPacientes.java)
Qué hace:
Implementación de hash map con chaining para mapear dni → Paciente.
Procedimiento:
Array de buckets con nodos enlazados; rehash cuando load factor > 0.75; métodos put/get/remove/containsKey/keys.
Consideraciones:
Diseñado para control educativo: expone details de colisiones y rehash.

Accion (Accion.java)
Qué hace:
Representa una acción modificadora del sistema (agregar/eliminar/modificar turno/paciente).
Procedimiento:
Guarda tipo (enum), estado anterior y nuevo, timestamp y descripción. Provee métodos para deshacer/rehacer (retornando el estado apropiado).
Consideraciones:
Usada por HistorialAcciones para implementar undo/redo.

HistorialAcciones (HistorialAcciones.java)
Qué hace:
Registro de acciones con capacidades de deshacer y rehacer.
Procedimiento:
Mantiene dos pilas (undoStack y redoStack) y tamaño máximo; agregarAccion(), deshacer(), rehacer(), puedeDeshacer(), puedeRehacer().
Consideraciones:
Controla máximo de acciones y limpia redoStack al agregar nueva acción tras cambios.

Main (Main.java)
Qué hace:
Punto de entrada. Carga CSVs (pacientes, médicos, turnos), parsea fechas/horas, muestra estadísticas y posibilita ejecutar utilidades del módulo.
Procedimiento:
Lee archivos DATA, crea objetos Paciente/Medico/Turno, utiliza utilidades (Sorts, Planner, MapaPacientes, SalaEspera, HistorialAcciones) según flujo. Incluye helper para parseo de fecha/hora con varios formatos.
Consideraciones:
Los detalles de parseo y manejo de duplicados (nube vs local) se realizan en Main al cargar los CSV.

Descripción breve de los CSV (ubicados en /workspaces/INF3/Integrador/DATA)
Medico.csv
Qué contiene:
matricula,nombre,especialidad
Uso:
Fuente de médicos para poblar objetos Medico. Cada fila → Medico(matricula,nombre,especialidad).

Paciente.csv
Qué contiene:
dni,nombre
Uso:
Fuente de pacientes para poblar objetos Paciente. Cada fila → Paciente(dni,nombre).

Turno.csv
Qué contiene:
id,fecha,hora,duracion,dniPaciente,matriculaMedico,motivo
Uso:
Turnos locales/primarios. Main los parsea y crea objetos Turno; pueden mostrarse, ordenarse y gestionarse.

Turno_nube.csv
Qué contiene:
mismo esquema que Turno.csv, proviene de la “nube”.
Uso:
Fuente remota que puede contener duplicados o actualizaciones; Main/logic de integración debe decidir cómo reconciliar (ej. merge, preferir nube, evitar duplicados por id).

Flujo y decisiones de diseño (resumen)
- Separación por responsabilidad: modelos, estructuras y utilidades independientes.
- Interfaces simples (POJOs) para facilitar pruebas y generación sintética.
- Estructuras didácticas (hash chaining, heap indexado, cola circular) para ilustrar algoritmos y complejidad.
- Uso de Comparator en sort para flexibilidad en criterios de ordenamiento.

Ejecución rápida
Desde la carpeta del proyecto:
cd /workspaces/INF3/Integrador
javac src/*.java
java -cp src src.Main

Nota
Los datos CSV se esperan en /workspaces/INF3/Integrador/DATA. Ajustar rutas si se ejecuta desde otra carpeta.