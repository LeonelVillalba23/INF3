import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Menu de ejercicios (1-9) ---");
            System.out.println("1) Ejercicio 1 - Crear 3 nodos y enlazarlos manualmente");
            System.out.println("2) Ejercicio 2 - Insertar al inicio (insertarAlInicio)");
            System.out.println("3) Ejercicio 3 - Insertar al final (insertarAlFinal)");
            System.out.println("4) Ejercicio 4 - Eliminar por valor");
            System.out.println("5) Ejercicio 5 - Buscar un valor");
            System.out.println("6) Ejercicio 6 - Contar elementos");
            System.out.println("7) Ejercicio 7 - Invertir la lista");
            System.out.println("8) Ejercicio 8 - Insertar en posición");
            System.out.println("9) Ejercicio 9 - Eliminar duplicados");
            System.out.println("10) Ejercicio 10 - Aplicación práctica (registro de alumnos)");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");
            int opt = -1;
            try {
                opt = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                opt = -1;
            }

            switch (opt) {
                case 1:
                    // Crear 3 nodos y enlazarlos manualmente (entrada por usuario)
                    try {
                        System.out.println("Crear 3 nodos manualmente: ingresa 3 valores");
                        System.out.print("Valor 1: ");
                        int v1 = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Valor 2: ");
                        int v2 = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Valor 3: ");
                        int v3 = Integer.parseInt(sc.nextLine().trim());
                        CrearNodo nn1 = new CrearNodo(v1);
                        CrearNodo nn2 = new CrearNodo(v2);
                        CrearNodo nn3 = new CrearNodo(v3);
                        nn1.setEnlace(nn2);
                        nn2.setEnlace(nn3);
                        System.out.println("Lista creada manualmente: " + nn1);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada invalida. Regresando al menu.");
                    }
                    break;
                case 2:
                    ListaEnlazada listaInicio = new ListaEnlazada();
                    System.out.println("Insertar al inicio: ingresa 3 valores ");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Valor #" + (i + 1) + ": ");
                        int v = Integer.parseInt(sc.nextLine().trim());
                        listaInicio.insertarInicio(v);
                    }
                    listaInicio.imprimir();
                    break;
                case 3:
                    ListaEnlazada listaFinal = new ListaEnlazada();
                    System.out.println("Insertar al final: ingresa 3 valores ");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Valor #" + (i + 1) + ": ");
                        int v = Integer.parseInt(sc.nextLine().trim());
                        listaFinal.insertarFinal(v);
                    }
                    listaFinal.imprimir();
                    break;
                case 4:
                    ListaEnlazada elim = new ListaEnlazada();
                    System.out.println("Datos de lista con: 10,20,30,40");
                    elim.insertarFinal(10);
                    elim.insertarFinal(20);
                    elim.insertarFinal(30);
                    elim.insertarFinal(40);
                    System.out.println("Antes: ");
                    elim.imprimir();
                    System.out.print("Valor a eliminar : ");
                    int toDel = Integer.parseInt(sc.nextLine().trim());
                    elim.eliminarPorValor(toDel);
                    System.out.println("Después: ");
                    elim.imprimir();
                    break;
                case 5:
                    ListaEnlazada busc = new ListaEnlazada();
                    System.out.println("Datos de lista con: 5,15,25,35");
                    busc.insertarFinal(5);
                    busc.insertarFinal(15);
                    busc.insertarFinal(25);
                    busc.insertarFinal(35);
                    System.out.print("Buscar valor : ");
                    int buscar = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Resultado: " + busc.buscar(buscar));
                    break;
                case 6:
                    ListaEnlazada contar = new ListaEnlazada();
                    // Crear lista 1..5
                    contar.insertarFinal(1);
                    contar.insertarFinal(2);
                    contar.insertarFinal(3);
                    contar.insertarFinal(4);
                    contar.insertarFinal(5);
                    System.out.println("Lista: ");
                    contar.imprimir();
                    System.out.println("Cantidad: " + contar.contar());
                    break;
                case 7:
                    ListaEnlazada inv = new ListaEnlazada();
                    inv.insertarFinal(10);
                    inv.insertarFinal(20);
                    inv.insertarFinal(30);
                    inv.insertarFinal(40);
                    System.out.println("Antes: ");
                    inv.imprimir();
                    inv.invertir();
                    System.out.println("Después: ");
                    inv.imprimir();
                    break;
                case 8:
                    ListaEnlazada iep = new ListaEnlazada();
                    iep.insertarFinal(1);
                    iep.insertarFinal(2);
                    iep.insertarFinal(4);
                    System.out.println("Antes: ");
                    iep.imprimir();
                    System.out.print("Insertar valor en posición (ej: 3 2 -> valor 3 en pos 2): ");
                    try {
                        String[] parts = sc.nextLine().trim().split("\\s+");
                        int val = Integer.parseInt(parts[0]);
                        int pos = Integer.parseInt(parts[1]);
                        iep.insertarEn(pos, val);
                        System.out.println("Después: ");
                        iep.imprimir();
                    } catch (Exception e) {
                        System.out.println("Entrada invalida. Debes ingresar: <valor> <pos>\nEj: 3 2");
                    }
                    break;
                case 9:
                    ListaEnlazada ed = new ListaEnlazada();
                    System.out.println("Datos Lista con: 1 -> 2 -> 2 -> 3 -> 1");
                    ed.insertarFinal(1);
                    ed.insertarFinal(2);
                    ed.insertarFinal(2);
                    ed.insertarFinal(3);
                    ed.insertarFinal(1);
                    System.out.println("Antes: ");
                    ed.imprimir();
                    ed.eliminarDuplicados();
                    System.out.println("Después: ");
                    ed.imprimir();
                    break;
                case 10:
                    AplicacionPractica.ListaAlumnos lista = new AplicacionPractica.ListaAlumnos();
                    System.out.println("Vamos a agregar 3 alumnos (nombre y legajo)");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Nombre del alumno #" + (i + 1) + ": ");
                        String nombre = sc.nextLine().trim();
                        System.out.print("Legajo del alumno #" + (i + 1) + ": ");
                        int legajo = Integer.parseInt(sc.nextLine().trim());
                        lista.agregarAlumno(nombre, legajo);
                    }
                    System.out.println("Lista de alumnos:");
                    lista.imprimirLista();

                    System.out.print("Buscar alumno por legajo (ingresa legajo): ");
                    try {
                        int legBuscar = Integer.parseInt(sc.nextLine().trim());
                        AplicacionPractica.Alumno a = lista.buscarAlumno(legBuscar);
                        System.out.println(a == null ? "Alumno no encontrado." : "Encontrado: " + a);
                    } catch (Exception e) {
                        System.out.println("Entrada invalida para legajo.");
                    }

                    System.out.print("Eliminar alumno por legajo (ingresa legajo): ");
                    try {
                        int legE = Integer.parseInt(sc.nextLine().trim());
                        boolean ok = lista.eliminarAlumno(legE);
                        System.out.println(ok ? "Alumno eliminado." : "No se encontró el legajo.");
                    } catch (Exception e) {
                        System.out.println("Entrada invalida para legajo.");
                    }

                    System.out.println("Estado final de la lista:");
                    lista.imprimirLista();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
