import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ImplementacionPila pila = null;
        ColaArreglo cola = null;

        while (true) {
            System.out.println("\n--- Menu simple ---");
            System.out.println("1 Crear pila");
            System.out.println("Apilar (numero)");
            System.out.println("Desapilar");
            System.out.println("2 Crear cola");
            System.out.println("Encolar (numero)");
            System.out.println("Desencolar");
            System.out.println("3 Invertir cadena");
            System.out.println("4 Simulacion de Turnos en Banco");
            System.out.println("5 Palindromo (simple)");
            System.out.println("6 Deshacer / Rehacer (submenu)");
            System.out.println("7 simulacion de impresora con cola");
            System.out.println("Cola circular para gestion de llamadas");
            System.out.println("9 Salir");
            System.out.print("Opcion: ");

            String line = br.readLine();
            if (line == null)
                break;
            int opc = Integer.parseInt(line);
            if (opc == 9)
                break;

            switch (opc) {
                case 1:
                    System.out.print("Tamanio pila: ");
                    pila = new ImplementacionPila(Integer.parseInt(br.readLine()));
                    System.out.print("Numero a apilar: ");
                    pila.push(Integer.parseInt(br.readLine()));
                    System.out.println("¿Desapilar ahora? (1-Si / 0-No): ");
                    int caso = Integer.parseInt(br.readLine());
                    if (caso == 1) {
                        System.out.println("Desapilado: " + pila.pop());
                    }

                    break;
                case 2:
                    System.out.print("Tamanio cola: ");
                    cola = new ColaArreglo(Integer.parseInt(br.readLine()));
                    System.out.print("Numero a encolar: ");
                    cola.enqueue(Integer.parseInt(br.readLine()));
                    System.out.println("¿Desencolar ahora? (1-Si / 0-No): ");
                    int caso2 = Integer.parseInt(br.readLine());
                    if (caso2 == 1) {
                        System.out.println("Desencolado: " + cola.dequeue());
                    }
                    break;
                case 3:
                    System.out.print("Cadena a invertir: ");
                    String texto = br.readLine();
                    ImplementacionPila p2 = new ImplementacionPila(texto.length());
                    for (char c : texto.toCharArray())
                        p2.push((int) c);
                    String inv = "";
                    while (!p2.isEmpty())
                        inv += (char) p2.pop();
                    System.out.println("Invertida: " + inv);
                    break;
                case 4:
                    // Llamar a la simulación de turnos que usa ColaArreglo
                    SimulacionBancoSimple.simular();
                    break;
                case 5:
                    System.out.print("Palabra o frase: ");
                    String linea = br.readLine();
                    String s = linea.replaceAll("\\s+", "").toLowerCase();
                    ImplementacionPila p = new ImplementacionPila(s.length());
                    ColaArreglo q = new ColaArreglo(s.length());
                    for (char c : s.toCharArray()) {
                        p.push((int) c);
                        q.enqueue((int) c);
                    }
                    boolean pal = true;
                    while (!p.isEmpty())
                        if (p.pop() != q.dequeue()) {
                            pal = false;
                            break;
                        }
                    System.out.println(pal ? "Es palindromo" : "No es palindromo");
                    break;
                case 6:

                    // Submenú: Deshacer / Rehacer usando DeshacerRehacerPila
                    DeshacerRehacerPila dr = new DeshacerRehacerPila();
                    while (true) {
                        System.out.println("\n--- Deshacer / Rehacer (submenu) ---");
                        System.out.println("1 Registrar accion (doAction)");
                        System.out.println("2 Deshacer (undo)");
                        System.out.println("3 Rehacer (redo)");
                        System.out.println("4 Mostrar pilas");
                        System.out.println("9 Volver al menu principal");
                        System.out.print("Opcion submenu: ");
                        String sl = br.readLine();
                        if (sl == null)
                            break;
                        int soc = Integer.parseInt(sl);
                        if (soc == 9)
                            break;
                        switch (soc) {
                            case 1:
                                System.out.print("Descripcion de la accion: ");
                                String accion = br.readLine();
                                dr.doAction(accion);
                                System.out.println("Accion registrada: " + accion);
                                break;
                            case 2:
                                String undone = dr.undo();
                                System.out.println(undone == null ? "Nada para deshacer" : "Deshecho: " + undone);
                                break;
                            case 3:
                                String redone = dr.redo();
                                System.out.println(redone == null ? "Nada para rehacer" : "Rehecho: " + redone);
                                break;
                            case 4:
                                System.out.println("Undo stack: " + dr.getUndoStack());
                                System.out.println("Redo stack: " + dr.getRedoStack());
                                break;
                            default:
                                System.out.println("Opcion submenu invalida");
                        }
                    }
                    break;
                case 7:
                    // Simulación de impresora
                    System.out.print("Ingrese la capacidad maxima de la cola de impresion: ");
                    int capacidadImpresora = Integer.parseInt(br.readLine());
                    SimulacionImpresoraCola impresora = new SimulacionImpresoraCola(capacidadImpresora);
                    while (true) {
                        System.out.println("\n--- Simulacion de Impresora ---");
                        System.out.println("1 Agregar documento a la cola");
                        System.out.println("2 Imprimir siguiente documento");
                        System.out.println("3 Ver proximo documento");
                        System.out.println("4 Ver cola completa");
                        System.out.println("5 Ver estado de la cola");
                        System.out.println("9 Volver al menu principal");
                        System.out.print("Opcion: ");

                        String op = br.readLine();
                        if (op == null || op.equals("9"))
                            break;

                        switch (Integer.parseInt(op)) {
                            case 1:
                                if (impresora.isFull()) {
                                    System.out.println("Error: Cola de impresion llena");
                                } else {
                                    System.out.print("Nombre del documento: ");
                                    String doc = br.readLine();
                                    if (impresora.enqueue(doc)) {
                                        System.out.println("Documento agregado a la cola");
                                    }
                                }
                                break;
                            case 2:
                                String printed = impresora.dequeue();
                                if (printed != null) {
                                    System.out.println("Imprimiendo: " + printed);
                                } else {
                                    System.out.println("No hay documentos en cola");
                                }
                                break;
                            case 3:
                                String next = impresora.peek();
                                if (next != null) {
                                    System.out.println("Proximo documento: " + next);
                                } else {
                                    System.out.println("No hay documentos en cola");
                                }
                                break;
                            case 4:
                                System.out.println("Cola de impresion: " + impresora);
                                break;
                            case 5:
                                System.out.println("Documentos en cola: " + impresora.getTamano());
                                System.out.println("Capacidad total: " + capacidadImpresora);
                                System.out.println("Estado: " +
                                        (impresora.isEmpty() ? "Vacia" : impresora.isFull() ? "Llena" : "Disponible"));
                                break;
                            default:
                                System.out.println("Opcion invalida");
                        }
                    }
                    break;
                case 8:
                    // Gestión de llamadas con cola circular
                    System.out.print("Ingrese la capacidad de la cola de llamadas: ");
                    int capacidadLlamadas = Integer.parseInt(br.readLine());
                    ColaCircularGestionLlamadas colaLlamadas = new ColaCircularGestionLlamadas(capacidadLlamadas);

                    while (true) {
                        System.out.println("\n--- Gestion de Llamadas ---");
                        System.out.println("1 Registrar nueva llamada");
                        System.out.println("2 Atender siguiente llamada");
                        System.out.println("3 Ver todas las llamadas");
                        System.out.println("4 Ver estado de la cola");
                        System.out.println("9 Volver al menu principal");
                        System.out.print("Opcion: ");

                        String opLlamada = br.readLine();
                        if (opLlamada == null || opLlamada.equals("9"))
                            break;

                        switch (Integer.parseInt(opLlamada)) {
                            case 1:
                                System.out.print("Numero o identificador de llamada: ");
                                String llamada = br.readLine();
                                colaLlamadas.enqueueOverwrite(llamada);
                                if (colaLlamadas.isFull()) {
                                    System.out.println("Aviso: Se sobrescribio la llamada mas antigua");
                                }
                                System.out.println("Llamada registrada exitosamente");
                                break;
                            case 2:
                                String siguienteLlamada = colaLlamadas.dequeue();
                                if (siguienteLlamada != null) {
                                    System.out.println("Atendiendo llamada: " + siguienteLlamada);
                                } else {
                                    System.out.println("No hay llamadas pendientes");
                                }
                                break;
                            case 3:
                                if (colaLlamadas.isEmpty()) {
                                    System.out.println("No hay llamadas en espera");
                                } else {
                                    System.out.println("Lista de llamadas en espera: " + colaLlamadas);
                                }
                                break;
                            case 4:
                                System.out.println(colaLlamadas.isEmpty() ? "Cola vacia"
                                        : colaLlamadas.isFull() ? "Cola llena" : "Cola con espacio disponible");
                                break;
                            default:
                                System.out.println("Opcion invalida");
                        }
                    }
                    break;
                default:
                    System.out.println("Opcion invalida");
            }

            if (pila != null)
                System.out.println("Pila: " + pila);
            if (cola != null)
                System.out.println("Cola: " + cola);
        }

        System.out.println("Fin del programa");
    }
}
