public class AplicacionPractica {

    /** Clase que representa un alumno con nombre y legajo. */
    public static class Alumno {
        private String nombre;
        private int legajo;

        public Alumno(String nombre, int legajo) {
            this.nombre = nombre;
            this.legajo = legajo;
        }

        public String getNombre() {
            return nombre;
        }

        public int getLegajo() {
            return legajo;
        }

        @Override
        public String toString() {
            return nombre + " (" + legajo + ")";
        }
    }

    /** Lista enlazada simple para almacenar objetos Alumno. */
    public static class ListaAlumnos {
        private Nodo cabeza;

        private static class Nodo {
            Alumno dato;
            Nodo siguiente;

            Nodo(Alumno a) {
                this.dato = a;
            }
        }

        public ListaAlumnos() {
            cabeza = null;
        }

        /**
         * Agrega un alumno al final de la lista.
         * 
         * @param nombre nombre del alumno
         * @param legajo legajo del alumno
         */
        public void agregarAlumno(String nombre, int legajo) {
            Alumno a = new Alumno(nombre, legajo);
            Nodo nuevo = new Nodo(a);
            if (cabeza == null) {
                cabeza = nuevo;
                return;
            }
            Nodo actual = cabeza;
            while (actual.siguiente != null)
                actual = actual.siguiente;
            actual.siguiente = nuevo;
        }

        /**
         * Busca y devuelve el Alumno con el legajo indicado, o null si no existe.
         * 
         * @param legajo legajo a buscar
         * @return Alumno encontrado o null
         */
        public Alumno buscarAlumno(int legajo) {
            Nodo actual = cabeza;
            while (actual != null) {
                if (actual.dato.getLegajo() == legajo)
                    return actual.dato;
                actual = actual.siguiente;
            }
            return null;
        }

        /**
         * Elimina el primer alumno que tenga el legajo indicado.
         * 
         * @param legajo legajo a eliminar
         * @return true si se eliminó, false si no se encontró
         */
        public boolean eliminarAlumno(int legajo) {
            if (cabeza == null)
                return false;
            if (cabeza.dato.getLegajo() == legajo) {
                cabeza = cabeza.siguiente;
                return true;
            }
            Nodo prev = cabeza;
            Nodo actual = cabeza.siguiente;
            while (actual != null) {
                if (actual.dato.getLegajo() == legajo) {
                    prev.siguiente = actual.siguiente;
                    return true;
                }
                prev = actual;
                actual = actual.siguiente;
            }
            return false;
        }

        /** Imprime la lista en formato legible. */
        public void imprimirLista() {
            if (cabeza == null) {
                System.out.println("Lista de alumnos vacía.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            Nodo actual = cabeza;
            while (actual != null) {
                sb.append(actual.dato.toString());
                if (actual.siguiente != null)
                    sb.append(" -> ");
                actual = actual.siguiente;
            }
            System.out.println(sb.toString());
        }
    }

}
