Resumen proyecto Guia6/src

Propósito
Implementar un Red Black Tree y herramientas para probar y visualizar inserciones, rotaciones, recoloreos y verificadores de invariantes. Entrada Main.java con un menú interactivo.

Estructura y organización
Diseño modular con cada responsabilidad en su propia clase. Uso de un sentinel NIL negro para evitar comprobaciones de null. Flujo típico de operación: InsertBST inserta como árbol binario de búsqueda sin balancear, luego FixInsert reequilibra mediante recoloreos y rotaciones.

Clases principales
- RBNode: nodo genérico con clave, valor, color y punteros left, right y parent.
- RBTree: contiene root y NIL, e implementa rotateLeft, rotateRight e impresiones del árbol.
- InsertBST: inserción como ABB que devuelve el nodo insertado.
- FixInsert: algoritmo de reparación tras inserción que aplica recoloreo y rotaciones para mantener propiedades rojinegras.
- CaseClassifier: determina el caso que corresponde durante fixInsert como tio rojo, LL, LR, RR o RL.
- RecolorUncleRed: aplica el recoloreo cuando el tio es rojo y promueve la variable z.
- RotateLeft y RotateRight: operaciones de rotación sobre subárboles.
- RotationHandler: coordina rotaciones simples y dobles según el caso detectado.
- SuccessorPredecessor: calcula el sucesor y el predecesor de un nodo en el BST.
- RangeQuery: obtiene las claves dentro de un intervalo usando recorrido in order parcial.
- InvariantChecks: verifica que la raiz sea negra, que no existan dos rojos consecutivos y que la altura negra sea consistente, devolviendo la altura negra o menos uno si hay inconsistencia.
- Main: interfaz de consola que integra todas las clases para demostraciones paso a paso.

Patrones técnicos clave
Rotaciones con complejidad O1. Operaciones principales con comportamiento O(log n) en un árbol balanceado. Sentinel NIL único para simplificar casos borde. Verificaciones implementadas mediante recorridos recursivos. Separación clara entre colocación del nodo, clasificación del caso, ejecución de acciones y verificación de invariantes.

Guion corto para explicar oralmente
1. Implementé un Red Black Tree con un menú interactivo para probar inserciones, rotaciones y verificadores. 
2. Estructura base con RBNode y RBTree y un sentinel NIL negro. 
3. Inserción en dos pasos: InsertBST coloca el nodo como ABB y FixInsert corrige con recoloreos y rotaciones guiado por CaseClassifier y RotationHandler. 
4. Incluye utilidades como successor, predecessor y range query y verificadores de invariantes. 
5. Complejidad típica O(log n) y diseño modular para facilitar demostraciones.

Es un Red Black Tree modular con sentinel NIL; InsertBST inserta como ABB y FixInsert reequilibra mediante recolor y rotaciones guiadas por CaseClassifier y RotationHandler; incluye consultas y verificadores; operaciones típicas O(log n).
