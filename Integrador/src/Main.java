package src;

import java.io.*;
import java.util.*;

public class Main {
    private static HistorialAcciones historialAcciones = new HistorialAcciones(50); // máximo 50 acciones

    public static void main(String[] args) {
        List<Paciente> pacientes = new ArrayList<>();
        List<Medico> medicos = new ArrayList<>();
        List<Turno> turnos = new ArrayList<>();

        try {
            // Rutas a los CSV en la carpeta DATA (mayúsculas tal como están en el proyecto)
            BufferedReader brPacientes = new BufferedReader(new FileReader("DATA/Paciente.csv"));
            brPacientes.readLine();
            String linea;
            while ((linea = brPacientes.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    pacientes.add(new Paciente(partes[0], partes[1]));
                }
            }
            brPacientes.close();

            BufferedReader brMedicos = new BufferedReader(new FileReader("DATA/Medico.csv"));
            brMedicos.readLine();
            while ((linea = brMedicos.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    medicos.add(new Medico(partes[0], partes[1], partes[2]));
                }
            }
            brMedicos.close();

            BufferedReader brTurnos = new BufferedReader(new FileReader("DATA/Turno.csv"));
            brTurnos.readLine();
            while ((linea = brTurnos.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 7) {
                    String id = partes[0];
                    String fecha = partes[1];
                    String hora = partes[2];
                    int duracion = Integer.parseInt(partes[3]);
                    String dniPaciente = partes[4];
                    String matriculaMedico = partes[5];
                    String motivo = partes[6];
                    turnos.add(new Turno(id, fecha, hora, duracion, dniPaciente, matriculaMedico, motivo));
                }
            }
            brTurnos.close();

        } catch (IOException e) {
            System.out.println("Error leyendo archivos: " + e.getMessage());
        }

        // Mostrar resultados cargados
        System.out.println("\nPacientes cargados: " + pacientes.size());
        for (Paciente p : pacientes)
            System.out.println("  " + p);

        System.out.println("\nMédicos cargados: " + medicos.size());
        for (Medico m : medicos)
            System.out.println("  " + m);

        // Validaciones básicas de turnos según consigna
        java.time.format.DateTimeFormatter[] formatos = new java.time.format.DateTimeFormatter[] {
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME
        };

        java.util.Set<String> pacientesDni = new java.util.HashSet<>();
        for (Paciente p : pacientes)
            pacientesDni.add(p.dni);
        // Indice rapido de pacientes (hash con chaining)
        MapaPacientes mapaPacientes = new MapaPacientes(Math.max(16, pacientes.size() * 2));
        for (Paciente p : pacientes)
            mapaPacientes.put(p.dni, p);
        java.util.Set<String> medicosMat = new java.util.HashSet<>();
        for (Medico m : medicos)
            medicosMat.add(m.matricula);
        java.util.Set<String> idsTurno = new java.util.HashSet<>();

        List<Turno> aceptados = new ArrayList<>();
        int rechazados = 0;

        for (Turno t : turnos) {
            boolean ok = true;
            String motivoRechazo = "";

            // 1) ID duplicado
            if (idsTurno.contains(t.id)) {
                ok = false;
                motivoRechazo = "ID duplicado: " + t.id;
            }

            // 2) paciente y medico existen
            if (ok && !pacientesDni.contains(t.dniPaciente)) {
                ok = false;
                motivoRechazo = "Paciente inexistente: " + t.dniPaciente;
            }
            if (ok && !medicosMat.contains(t.matriculaMedico)) {
                ok = false;
                motivoRechazo = "Médico inexistente: " + t.matriculaMedico;
            }

            // 3) duración > 0
            if (ok && t.duracion <= 0) {
                ok = false;
                motivoRechazo = "Duración inválida: " + t.duracion;
            }

            // 4) fecha y hora no en el pasado (intentar parsear)
            if (ok) {
                String fh = t.fecha + " " + t.hora;
                java.time.LocalDateTime dt = null;
                for (java.time.format.DateTimeFormatter fmt : formatos) {
                    try {
                        dt = java.time.LocalDateTime.parse(fh, fmt);
                        break;
                    } catch (Exception ex) {
                        // intentar siguiente formato
                    }
                }
                if (dt == null) {
                    // intentar si fecha ya incluye hora
                    try {
                        dt = java.time.LocalDateTime.parse(t.fecha);
                    } catch (Exception ex) {
                        ok = false;
                        motivoRechazo = "Fecha/hora inválida: " + fh;
                    }
                }
                if (ok && dt != null) {
                    if (dt.isBefore(java.time.LocalDateTime.now())) {
                        ok = false;
                        motivoRechazo = "Fecha en el pasado: " + fh;
                    }
                }
            }

            if (ok) {
                idsTurno.add(t.id);
                aceptados.add(t);
            } else {
                rechazados++;
                System.out.println("Turno rechazado (" + t.id + "): " + motivoRechazo);
            }
        }

        System.out.println("\nTurnos cargados: " + aceptados.size() + "  (" + rechazados + " rechazados)");
        for (Turno t : aceptados)
            System.out.println("  " + t);

        // 🔽 MENÚ PRINCIPAL (do-while + switch) 🔽
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1) Ver agenda de un médico");
            System.out.println("2) Buscar próximo turno disponible");
            System.out.println("3) Simular sala de espera");
            System.out.println("4) Programar recordatorios");
            System.out.println("5) Consultar índice de pacientes");
            System.out.println("6) Consolidador de agendas");
            System.out.println("7) Reportes de ordenamiento");
            System.out.println("8) Auditoría Undo/Redo");
            System.out.println("9) Planificador de quirófano");
            System.out.println("10) (Reserva) - Funcionalidad adicional");
            System.out.println("0) Salir");
            System.out.print("Ingrese una opción: ");

            // Leer la línea completa y parsear para evitar InputMismatchException
            String linea = sc.nextLine();
            try {
                opcion = Integer.parseInt(linea.trim());
            } catch (NumberFormatException nfe) {
                System.out.println("Entrada inválida: ingrese un número entre 0 y 10.");
                opcion = -1; // fuerza a mostrar menú de nuevo
            }

            switch (opcion) {
                case 1: {
                    System.out.println("[1] Ver agenda de un médico");
                    System.out.print("Ingrese matrícula o parte del nombre del médico: ");
                    String criterio = sc.nextLine().trim();
                    List<Medico> matches = new ArrayList<>();
                    for (Medico m : medicos) {
                        if (m.matricula.equalsIgnoreCase(criterio)
                                || m.nombre.toLowerCase().contains(criterio.toLowerCase())) {
                            matches.add(m);
                        }
                    }
                    if (matches.isEmpty()) {
                        System.out.println("No se encontró ningún médico con ese criterio.");
                        break;
                    }
                    Medico seleccionado = null;
                    if (matches.size() == 1) {
                        seleccionado = matches.get(0);
                    } else {
                        System.out.println("Se encontraron varios médicos:");
                        for (int i = 0; i < matches.size(); i++) {
                            System.out.println((i + 1) + ") " + matches.get(i));
                        }
                        System.out.print("Seleccione el número correspondiente: ");
                        String sel = sc.nextLine().trim();
                        int idx = -1;
                        try {
                            idx = Integer.parseInt(sel);
                        } catch (Exception e) {
                        }
                        if (idx < 1 || idx > matches.size()) {
                            System.out.println("Selección inválida.");
                            break;
                        }
                        seleccionado = matches.get(idx - 1);
                    }

                    System.out
                            .println("\n[AGENDA DEL " + seleccionado.nombre + " - " + seleccionado.especialidad + "]");

                    // Recolectar turnos aceptados para ese médico y parsear fecha/hora
                    List<Map.Entry<Turno, java.time.LocalDateTime>> agenda = new ArrayList<>();
                    for (Turno t : aceptados) {
                        if (t.matriculaMedico.equalsIgnoreCase(seleccionado.matricula)) {
                            java.time.LocalDateTime dt = null;
                            for (java.time.format.DateTimeFormatter fmt : formatos) {
                                try {
                                    dt = java.time.LocalDateTime.parse(t.fecha + " " + t.hora, fmt);
                                    break;
                                } catch (Exception ex) {
                                }
                            }
                            if (dt == null) {
                                try {
                                    dt = java.time.LocalDateTime.parse(t.fecha);
                                } catch (Exception ex) {
                                }
                            }
                            if (dt != null) {
                                agenda.add(new AbstractMap.SimpleEntry<>(t, dt));
                            }
                        }
                    }

                    if (agenda.isEmpty()) {
                        System.out.println("No hay turnos para este médico.");
                        break;
                    }

                    // Ordenar por fecha
                    agenda.sort(Comparator.comparing(Map.Entry::getValue));

                    System.out.printf("%-6s %-15s %-20s %s\n", "ID", "PACIENTE", "FECHA Y HORA", "MOTIVO");
                    for (Map.Entry<Turno, java.time.LocalDateTime> e : agenda) {
                        Turno t = e.getKey();
                        java.time.LocalDateTime dt = e.getValue();
                        String pacienteNombre = t.dniPaciente;
                        for (Paciente p : pacientes) {
                            if (p.dni.equals(t.dniPaciente)) {
                                pacienteNombre = p.nombre;
                                break;
                            }
                        }
                        String fechaStr = dt.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        String motivo = (t.motivo == null) ? "" : t.motivo;
                        System.out.printf("%-6s %-15s %-20s %s\n", t.id, pacienteNombre, fechaStr, motivo);
                    }

                }
                    break;
                case 2:
                    System.out.println("[2] Buscar próximo turno disponible");
                    System.out.print("Ingrese matrícula o parte del nombre del médico: ");
                    String criterio2 = sc.nextLine().trim();
                    List<Medico> matches2 = new ArrayList<>();
                    for (Medico m : medicos) {
                        if (m.matricula.equalsIgnoreCase(criterio2)
                                || m.nombre.toLowerCase().contains(criterio2.toLowerCase())) {
                            matches2.add(m);
                        }
                    }
                    if (matches2.isEmpty()) {
                        System.out.println("No se encontró ningún médico con ese criterio.");
                        break;
                    }
                    Medico seleccionado2 = null;
                    if (matches2.size() == 1) {
                        seleccionado2 = matches2.get(0);
                    } else {
                        System.out.println("Se encontraron varios médicos:");
                        for (int i = 0; i < matches2.size(); i++) {
                            System.out.println((i + 1) + ") " + matches2.get(i));
                        }
                        System.out.print("Seleccione el número correspondiente: ");
                        String sel2 = sc.nextLine().trim();
                        int idx2 = -1;
                        try {
                            idx2 = Integer.parseInt(sel2);
                        } catch (Exception e) {
                        }
                        if (idx2 < 1 || idx2 > matches2.size()) {
                            System.out.println("Selección inválida.");
                            break;
                        }
                        seleccionado2 = matches2.get(idx2 - 1);
                    }

                    System.out.print("Ingrese fecha y hora (ej: dd/MM/yyyy HH:mm o yyyy-MM-dd HH:mm): ");
                    String entradaFecha = sc.nextLine().trim();
                    java.time.LocalDateTime t0 = null;
                    for (java.time.format.DateTimeFormatter fmt : formatos) {
                        try {
                            t0 = java.time.LocalDateTime.parse(entradaFecha, fmt);
                            break;
                        } catch (Exception ex) {
                        }
                    }
                    if (t0 == null) {
                        // intentar parsear si usuario ingresó fecha y hora separadas
                        try {
                            t0 = java.time.LocalDateTime.parse(entradaFecha);
                        } catch (Exception ex) {
                        }
                    }
                    if (t0 == null) {
                        System.out.println("Formato de fecha/hora inválido.");
                        break;
                    }

                    // Buscar el siguiente turno para el médico con dt >= t0
                    final Medico medSel = seleccionado2;
                    final java.time.LocalDateTime ref = t0;
                    java.util.Optional<java.util.AbstractMap.SimpleEntry<Turno, java.time.LocalDateTime>> siguiente = aceptados
                            .stream()
                            .filter(t -> t.matriculaMedico.equalsIgnoreCase(medSel.matricula))
                            .map(t -> {
                                java.time.LocalDateTime dt = null;
                                for (java.time.format.DateTimeFormatter fmt : formatos) {
                                    try {
                                        dt = java.time.LocalDateTime.parse(t.fecha + " " + t.hora, fmt);
                                        break;
                                    } catch (Exception ex) {
                                    }
                                }
                                if (dt == null) {
                                    try {
                                        dt = java.time.LocalDateTime.parse(t.fecha);
                                    } catch (Exception ex) {
                                    }
                                }
                                return (dt == null) ? null : new AbstractMap.SimpleEntry<>(t, dt);
                            })
                            .filter(Objects::nonNull)
                            .filter(e -> !e.getValue().isBefore(ref))
                            .min(Comparator.comparing(e -> e.getValue()));

                    if (siguiente.isPresent()) {
                        java.util.AbstractMap.SimpleEntry<Turno, java.time.LocalDateTime> e = siguiente.get();
                        Turno t = e.getKey();
                        java.time.LocalDateTime dt = e.getValue();
                        String pacienteNombre = t.dniPaciente;
                        for (Paciente p : pacientes) {
                            if (p.dni.equals(t.dniPaciente)) {
                                pacienteNombre = p.nombre;
                                break;
                            }
                        }
                        System.out.println("Siguiente turno para " + seleccionado2.nombre + ":");
                        System.out.printf("ID: %s\nPaciente: %s\nFecha y hora: %s\nDuración: %d min\nMotivo: %s\n",
                                t.id,
                                pacienteNombre,
                                dt.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                                t.duracion,
                                (t.motivo == null ? "" : t.motivo));
                    } else {
                        System.out.println("No hay turnos programados para ese médico a partir de "
                                + t0.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                    }
                    break;
                case 3:
                    System.out.println("[3] Simular sala de espera");
                    System.out.print("Ingrese capacidad de la sala (número entero): ");
                    String capStr = sc.nextLine().trim();
                    int capacidad = 0;
                    try {
                        capacidad = Integer.parseInt(capStr);
                    } catch (Exception ex) {
                        System.out.println("Capacidad inválida.");
                        break;
                    }
                    SalaEspera sala = new SalaEspera(capacidad);
                    System.out.println("Sala creada. Capacidad = " + capacidad);

                    // sub-menú interactivo para la sala de espera
                    while (true) {
                        System.out.println("\nSala de Espera - opciones:");
                        System.out.println("1) Llega paciente (dni)");
                        System.out.println("2) Atiende (sacar siguiente)");
                        System.out.println("3) Peek (ver siguiente)");
                        System.out.println("4) Mostrar estado");
                        System.out.println("5) Mostrar tamaño");
                        System.out.println("0) Volver al menú principal");
                        System.out.print("Seleccione: ");
                        String opt = sc.nextLine().trim();
                        int sub;
                        try {
                            sub = Integer.parseInt(opt);
                        } catch (Exception e) {
                            System.out.println("Opción inválida.");
                            continue;
                        }
                        if (sub == 0)
                            break;
                        switch (sub) {
                            case 1:
                                System.out.print("DNI paciente que llega: ");
                                String dni = sc.nextLine().trim();
                                sala.llega(dni);
                                System.out.println("Paciente " + dni + " agregado.");
                                break;
                            case 2:
                                String atendido = sala.atiende();
                                if (atendido == null)
                                    System.out.println("Sala vacía.");
                                else
                                    System.out.println("Atendido: " + atendido);
                                break;
                            case 3:
                                String prox = sala.peek();
                                if (prox == null)
                                    System.out.println("Sala vacía.");
                                else
                                    System.out.println("Siguiente a atender: " + prox);
                                break;
                            case 4:
                                System.out.println(sala.toString());
                                break;
                            case 5:
                                System.out.println("Tamaño actual: " + sala.size());
                                break;
                            default:
                                System.out.println("Opción inválida en sala de espera.");
                                break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("[4] Programar recordatorios");
                    Planner planner = new Planner();

                    // submenú para recordatorios
                    while (true) {
                        System.out.println("\nRecordatorios - opciones:");
                        System.out.println("1) Programar nuevo recordatorio");
                        System.out.println("2) Ver próximo (y extraer)");
                        System.out.println("3) Peek próximo (sin extraer)");
                        System.out.println("4) Reprogramar por ID");
                        System.out.println("5) Listar todos (heap interno)");
                        System.out.println("6) Tamaño");
                        System.out.println("0) Volver al menú principal");
                        System.out.print("Seleccione: ");
                        String ropt = sc.nextLine().trim();
                        int rop;
                        try {
                            rop = Integer.parseInt(ropt);
                        } catch (Exception e) {
                            System.out.println("Opción inválida.");
                            continue;
                        }
                        if (rop == 0)
                            break;
                        switch (rop) {
                            case 1: {
                                System.out.print("ID recordatorio: ");
                                String idr = sc.nextLine().trim();
                                System.out.print("Fecha y hora (dd/MM/yyyy HH:mm o yyyy-MM-dd HH:mm): ");
                                String fstr = sc.nextLine().trim();
                                java.time.LocalDateTime fechaR = null;
                                for (java.time.format.DateTimeFormatter fmt : formatos) {
                                    try {
                                        fechaR = java.time.LocalDateTime.parse(fstr, fmt);
                                        break;
                                    } catch (Exception ex) {
                                    }
                                }
                                if (fechaR == null) {
                                    System.out.println("Formato de fecha inválido.");
                                    break;
                                }
                                System.out.print("DNI paciente: ");
                                String dnir = sc.nextLine().trim();
                                System.out.print("Mensaje: ");
                                String msg = sc.nextLine().trim();
                                try {
                                    planner.programar(new Recordatorio(idr, fechaR, dnir, msg));
                                    System.out.println("Recordatorio programado: " + idr);
                                } catch (Exception ex) {
                                    System.out.println("Error al programar: " + ex.getMessage());
                                }
                                break;
                            }
                            case 2: {
                                Recordatorio pr = planner.proximo();
                                if (pr == null)
                                    System.out.println("No hay recordatorios programados.");
                                else
                                    System.out.println("Próximo (extraído): " + pr);
                                break;
                            }
                            case 3: {
                                Recordatorio pr = planner.peek();
                                if (pr == null)
                                    System.out.println("No hay recordatorios programados.");
                                else
                                    System.out.println("Próximo (sin extraer): " + pr);
                                break;
                            }
                            case 4: {
                                System.out.print("ID a reprogramar: ");
                                String idr = sc.nextLine().trim();
                                System.out.print("Nueva fecha y hora (dd/MM/yyyy HH:mm o yyyy-MM-dd HH:mm): ");
                                String fstr = sc.nextLine().trim();
                                java.time.LocalDateTime fechaR = null;
                                for (java.time.format.DateTimeFormatter fmt : formatos) {
                                    try {
                                        fechaR = java.time.LocalDateTime.parse(fstr, fmt);
                                        break;
                                    } catch (Exception ex) {
                                    }
                                }
                                if (fechaR == null) {
                                    System.out.println("Formato de fecha inválido.");
                                    break;
                                }
                                boolean ok = planner.reprogramar(idr, fechaR);
                                System.out.println(ok ? "Reprogramado correctamente." : "ID no encontrado.");
                                break;
                            }
                            case 5: {
                                List<Recordatorio> todos = planner.listAll();
                                if (todos.isEmpty())
                                    System.out.println("No hay recordatorios.");
                                else {
                                    System.out.println("Recordatorios (orden interno heap):");
                                    for (Recordatorio r : todos)
                                        System.out.println(" - " + r);
                                }
                                break;
                            }
                            case 6:
                                System.out.println("Tamaño planner: " + planner.size());
                                break;
                            default:
                                System.out.println("Opción inválida en recordatorios.");
                                break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("[5] Consultar índice de pacientes (Hash)");
                    while (true) {
                        System.out.println("\nIndice Pacientes - opciones:");
                        System.out.println("1) Buscar paciente por DNI");
                        System.out.println("2) Insertar/Actualizar paciente");
                        System.out.println("3) Eliminar paciente por DNI");
                        System.out.println("4) Contiene clave (DNI)?");
                        System.out.println("5) Mostrar buckets (debug)");
                        System.out.println("6) Listar claves (DNI)");
                        System.out.println("0) Volver al menú principal");
                        System.out.print("Seleccione: ");
                        String opt = sc.nextLine().trim();
                        int sub;
                        try {
                            sub = Integer.parseInt(opt);
                        } catch (Exception e) {
                            System.out.println("Opción inválida.");
                            continue;
                        }
                        if (sub == 0)
                            break;
                        switch (sub) {
                            case 1: {
                                System.out.print("DNI a buscar: ");
                                String dniq = sc.nextLine().trim();
                                Paciente res = mapaPacientes.get(dniq);
                                if (res == null)
                                    System.out.println("No existe paciente con DNI " + dniq);
                                else
                                    System.out.println("Encontrado: " + res);
                                break;
                            }
                            case 2: {
                                System.out.print("DNI nuevo/actualizar: ");
                                String dninew = sc.nextLine().trim();
                                System.out.print("Nombre: ");
                                String nombre = sc.nextLine().trim();
                                Paciente pnew = new Paciente(dninew, nombre);
                                mapaPacientes.put(dninew, pnew);
                                // mantener listas locales en sincronía
                                if (!pacientesDni.contains(dninew)) {
                                    pacientes.add(pnew);
                                    pacientesDni.add(dninew);
                                } else {
                                    // actualizar nombre en la lista
                                    for (int i = 0; i < pacientes.size(); i++) {
                                        if (pacientes.get(i).dni.equals(dninew)) {
                                            pacientes.set(i, pnew);
                                            break;
                                        }
                                    }
                                }
                                System.out.println("Paciente insertado/actualizado: " + pnew);
                                break;
                            }
                            case 3: {
                                System.out.print("DNI a eliminar: ");
                                String dnirem = sc.nextLine().trim();
                                boolean ok = mapaPacientes.remove(dnirem);
                                if (ok) {
                                    pacientesDni.remove(dnirem);
                                    pacientes.removeIf(pp -> pp.dni.equals(dnirem));
                                    System.out.println("Paciente eliminado: " + dnirem);
                                } else
                                    System.out.println("No se encontró DNI: " + dnirem);
                                break;
                            }
                            case 4: {
                                System.out.print("DNI a chequear: ");
                                String dnich = sc.nextLine().trim();
                                System.out.println(mapaPacientes.containsKey(dnich) ? "Sí, existe" : "No existe");
                                break;
                            }
                            case 5:
                                mapaPacientes.debugPrintBuckets();
                                break;
                            case 6: {
                                System.out.println("Claves (DNI) en índice - size=" + mapaPacientes.size() + ":");
                                for (String k : mapaPacientes.keys())
                                    System.out.println(" - " + k);
                                break;
                            }
                            default:
                                System.out.println("Opción inválida en índice de pacientes.");
                                break;
                        }
                    }
                    break;
                case 6:
                    System.out.println("[6] Consolidador de agendas");
                    System.out.println(
                            "Se cargará una agenda 'nube' de ejemplo desde DATA/Turno_nube.csv y se consolidará con la agenda local.");
                    // cargar agenda nube (sin repetir todas las validaciones para agilizar demo)
                    List<Turno> nube = new ArrayList<>();
                    try (BufferedReader br = new BufferedReader(new FileReader("DATA/Turno_nube.csv"))) {
                        br.readLine();
                        String l;
                        while ((l = br.readLine()) != null) {
                            String[] p = l.split(",");
                            if (p.length >= 7) {
                                nube.add(new Turno(p[0], p[1], p[2], Integer.parseInt(p[3]), p[4], p[5], p[6]));
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("No se pudo leer DATA/Turno_nube.csv: " + ex.getMessage());
                        break;
                    }

                    // Parsear y ordenar ambas agendas por fecha
                    List<Map.Entry<Turno, java.time.LocalDateTime>> aList = new ArrayList<>();
                    List<Map.Entry<Turno, java.time.LocalDateTime>> bList = new ArrayList<>();
                    for (Turno t : aceptados) {
                        java.time.LocalDateTime dt = parseTurnoDateTime(t, formatos);
                        if (dt != null)
                            aList.add(new AbstractMap.SimpleEntry<>(t, dt));
                    }
                    for (Turno t : nube) {
                        java.time.LocalDateTime dt = parseTurnoDateTime(t, formatos);
                        if (dt != null)
                            bList.add(new AbstractMap.SimpleEntry<>(t, dt));
                    }
                    aList.sort(Comparator.comparing(Map.Entry::getValue));
                    bList.sort(Comparator.comparing(Map.Entry::getValue));

                    // Merge O(|A|+|B|)
                    List<Turno> merged = new ArrayList<>();
                    List<String> conflictLogs = new ArrayList<>();
                    int i = 0, j = 0;
                    while (i < aList.size() && j < bList.size()) {
                        Map.Entry<Turno, java.time.LocalDateTime> ea = aList.get(i);
                        Map.Entry<Turno, java.time.LocalDateTime> eb = bList.get(j);
                        Turno ta = ea.getKey();
                        Turno tb = eb.getKey();
                        java.time.LocalDateTime dta = ea.getValue();
                        java.time.LocalDateTime dtb = eb.getValue();

                        // duplicate id
                        if (ta.id.equals(tb.id)) {
                            merged.add(ta); // keep local
                            conflictLogs.add("Duplicado ID " + ta.id + " (se mantiene registro local)");
                            i++;
                            j++;
                            continue;
                        }

                        // conflict: same medico and same exact start
                        if (ta.matriculaMedico.equalsIgnoreCase(tb.matriculaMedico) && dta.equals(dtb)) {
                            merged.add(ta); // keep local by policy
                            conflictLogs.add("Conflicto horario para medico " + ta.matriculaMedico + " en "
                                    + dta.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                                    + ": se mantiene local id=" + ta.id + " y se omite nube id=" + tb.id);
                            i++;
                            j++;
                            continue;
                        }

                        if (dta.isBefore(dtb) || dta.equals(dtb)) {
                            merged.add(ta);
                            i++;
                        } else {
                            merged.add(tb);
                            j++;
                        }
                    }
                    while (i < aList.size()) {
                        merged.add(aList.get(i++).getKey());
                    }
                    while (j < bList.size()) {
                        merged.add(bList.get(j++).getKey());
                    }

                    System.out.println("\nConsolidación completada. Resultados:");
                    System.out.println("Turnos totales consolidados: " + merged.size());
                    if (!conflictLogs.isEmpty()) {
                        System.out.println("Conflictos/duplicados detectados:");
                        for (String s : conflictLogs)
                            System.out.println(" - " + s);
                    } else
                        System.out.println("No se detectaron conflictos ni duplicados.");
                    System.out.println("Listado (ordenado):");
                    for (Turno t : merged)
                        System.out.println("  " + t);
                    break;
                case 7:
                    System.out.println("[7] Reportes de ordenamiento");
                    System.out.println("\nBenchmark de algoritmos de ordenamiento");
                    System.out.println("1) Insertion Sort (estable)");
                    System.out.println("2) Shellsort");
                    System.out.println("3) Quicksort (Lomuto)");
                    System.out.println("4) Comparar todos");
                    System.out.println("0) Volver al menú principal");
                    System.out.print("Seleccione algoritmo: ");

                    String opt7 = sc.nextLine().trim();
                    int alg;
                    try {
                        alg = Integer.parseInt(opt7);
                    } catch (Exception e) {
                        System.out.println("Opción inválida.");
                        break;
                    }

                    if (alg == 0)
                        break;
                    if (alg < 1 || alg > 4) {
                        System.out.println("Opción inválida.");
                        break;
                    }

                    System.out.print("\nIngrese tamaño de la lista a ordenar (n): ");
                    String nStr = sc.nextLine().trim();
                    int n;
                    try {
                        n = Integer.parseInt(nStr);
                        if (n <= 0)
                            throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Tamaño inválido.");
                        break;
                    }

                    // Generar lista sintética de turnos para ordenar
                    List<Turno> data = Sorts.generateSyntheticTurnos(n, pacientes, medicos);

                    // Criterio de comparación: por fecha/hora
                    Comparator<Turno> cmp = (t1, t2) -> {
                        java.time.LocalDateTime dt1 = parseTurnoDateTime(t1, formatos);
                        java.time.LocalDateTime dt2 = parseTurnoDateTime(t2, formatos);
                        if (dt1 == null && dt2 == null)
                            return 0;
                        if (dt1 == null)
                            return -1;
                        if (dt2 == null)
                            return 1;
                        return dt1.compareTo(dt2);
                    };

                    if (alg <= 3) {
                        // Un solo algoritmo
                        List<Turno> copy = new ArrayList<>(data);
                        String nombre = alg == 1 ? "Insertion Sort" : alg == 2 ? "Shellsort" : "Quicksort";
                        System.out.println("\nEjecutando " + nombre + " con n=" + n + "...");

                        long t1 = System.nanoTime();
                        switch (alg) {
                            case 1:
                                Sorts.insertionSort(copy, cmp);
                                break;
                            case 2:
                                Sorts.shellSort(copy, cmp);
                                break;
                            case 3:
                                Sorts.quickSort(copy, cmp);
                                break;
                        }
                        long t2 = System.nanoTime();
                        double ms = (t2 - t1) / 1_000_000.0;

                        // Verificar que quedó ordenado
                        boolean sorted = true;
                        for (int k = 1; k < copy.size(); k++) {
                            if (cmp.compare(copy.get(k - 1), copy.get(k)) > 0) {
                                sorted = false;
                                break;
                            }
                        }

                        System.out.printf("Tiempo: %.3f ms\n", ms);
                        if (!sorted)
                            System.out.println("ADVERTENCIA: Lista no ordenada!");

                    } else {
                        // Comparar los 3 algoritmos
                        System.out.println("\nComparando los 3 algoritmos con n=" + n + "...");
                        String[] nombres = { "Insertion Sort", "Shellsort", "Quicksort" };
                        double[] tiempos = new double[3];
                        boolean[] ordenado = new boolean[3];

                        for (int alg_idx = 0; alg_idx < 3; alg_idx++) {
                            List<Turno> copy = new ArrayList<>(data);
                            System.out.print(nombres[alg_idx] + "... ");

                            long t3 = System.nanoTime();
                            switch (alg_idx) {
                                case 0:
                                    Sorts.insertionSort(copy, cmp);
                                    break;
                                case 1:
                                    Sorts.shellSort(copy, cmp);
                                    break;
                                case 2:
                                    Sorts.quickSort(copy, cmp);
                                    break;
                            }
                            long t4 = System.nanoTime();
                            tiempos[alg_idx] = (t4 - t3) / 1_000_000.0;

                            // Verificar que quedó ordenado
                            ordenado[alg_idx] = true;
                            for (int m = 1; m < copy.size(); m++) {
                                if (cmp.compare(copy.get(m - 1), copy.get(m)) > 0) {
                                    ordenado[alg_idx] = false;
                                    break;
                                }
                            }
                            System.out.printf("%.3f ms%s\n", tiempos[alg_idx],
                                    ordenado[alg_idx] ? "" : " (ADVERTENCIA: no ordenado!)");
                        }

                        // Encontrar el más rápido
                        int mejor = 0;
                        for (int idx = 1; idx < 3; idx++) {
                            if (tiempos[idx] < tiempos[mejor])
                                mejor = idx;
                        }
                        System.out.printf("\nMás rápido: %s (%.3f ms)\n", nombres[mejor], tiempos[mejor]);
                    }
                    break;
                case 8:
                    System.out.println("[8] Auditoría Undo/Redo");

                    // Crear historial si no existe
                    if (historialAcciones == null) {
                        historialAcciones = new HistorialAcciones(50); // máximo 50 acciones
                    }

                    while (true) {
                        System.out.println("\nHistorial de Acciones - Opciones:");
                        System.out.println("1) Ver historial completo");
                        System.out.println("2) Deshacer última acción");
                        System.out.println("3) Rehacer última acción");
                        System.out.println("4) Simular nueva acción");
                        System.out.println("5) Ver tamaño del historial");
                        System.out.println("0) Volver al menú principal");
                        System.out.print("Seleccione: ");

                        String optH = sc.nextLine().trim();
                        int subH;
                        try {
                            subH = Integer.parseInt(optH);
                        } catch (Exception e) {
                            System.out.println("Opción inválida.");
                            continue;
                        }

                        if (subH == 0)
                            break;

                        switch (subH) {
                            case 1:
                                List<Accion> hist = historialAcciones.obtenerHistorial();
                                if (hist.isEmpty()) {
                                    System.out.println("El historial está vacío.");
                                } else {
                                    System.out.println("\nHistorial de acciones (más reciente primero):");
                                    for (Accion a : hist) {
                                        System.out.println("  " + a);
                                    }
                                }
                                break;

                            case 2:
                                if (!historialAcciones.puedeDeshacer()) {
                                    System.out.println("No hay acciones para deshacer.");
                                    break;
                                }
                                Accion deshecha = historialAcciones.deshacer();
                                System.out.println("Acción deshecha: " + deshecha);
                                // Aquí se debería restaurar el estado anterior
                                break;

                            case 3:
                                if (!historialAcciones.puedeRehacer()) {
                                    System.out.println("No hay acciones para rehacer.");
                                    break;
                                }
                                Accion rehecha = historialAcciones.rehacer();
                                System.out.println("Acción rehecha: " + rehecha);
                                // Aquí se debería restaurar el estado nuevo
                                break;

                            case 4:
                                System.out.println("\nSimular acción - Tipo:");
                                System.out.println("1) Agregar turno");
                                System.out.println("2) Eliminar turno");
                                System.out.println("3) Modificar turno");
                                System.out.println("4) Agregar paciente");
                                System.out.println("5) Eliminar paciente");
                                System.out.println("6) Modificar paciente");
                                System.out.print("Seleccione tipo: ");

                                int tipoNum = -1;
                                try {
                                    tipoNum = Integer.parseInt(sc.nextLine().trim());
                                } catch (Exception e) {
                                    System.out.println("Tipo inválido.");
                                    break;
                                }

                                if (tipoNum < 1 || tipoNum > 6) {
                                    System.out.println("Tipo inválido.");
                                    break;
                                }

                                Accion.TipoAccion[] tipos = Accion.TipoAccion.values();
                                if (tipoNum - 1 >= tipos.length) {
                                    System.out.println("Tipo no implementado.");
                                    break;
                                }

                                System.out.print("Descripción de la acción: ");
                                String desc = sc.nextLine().trim();

                                // Simular una acción (en una implementación real, los estados vendrían
                                // del objeto real siendo modificado)
                                Accion nuevaAccion = new Accion(
                                        tipos[tipoNum - 1],
                                        "Estado anterior simulado",
                                        "Estado nuevo simulado",
                                        desc);

                                historialAcciones.agregarAccion(nuevaAccion);
                                System.out.println("Acción registrada: " + nuevaAccion);
                                break;

                            case 5:
                                System.out.println("Tamaño actual del historial: " + historialAcciones.size());
                                break;

                            default:
                                System.out.println("Opción inválida.");
                                break;
                        }
                    }
                    break;
                case 9:
                    System.out.println("[9] Planificador de quirófano");
                    // Min-heap de turnos por fecha/hora
                    Comparator<Turno> cmpTurno = (t1, t2) -> {
                        java.time.LocalDateTime dt1 = parseTurnoDateTime(t1, formatos);
                        java.time.LocalDateTime dt2 = parseTurnoDateTime(t2, formatos);
                        if (dt1 == null && dt2 == null)
                            return 0;
                        if (dt1 == null)
                            return -1;
                        if (dt2 == null)
                            return 1;
                        return dt1.compareTo(dt2);
                    };
                    PriorityQueue<Turno> heapQuir = new PriorityQueue<>(cmpTurno);
                    heapQuir.addAll(aceptados);
                    while (true) {
                        System.out.println("\nPlanificador de Quirófano - Opciones:");
                        System.out.println("1) Extraer próximo turno a asignar");
                        System.out.println("2) Ver siguiente turno (peek)");
                        System.out.println("3) Ver todos los turnos en el heap");
                        System.out.println("4) Ver tamaño del heap");
                        System.out.println("0) Volver al menú principal");
                        System.out.print("Seleccione: ");
                        String optQ = sc.nextLine().trim();
                        int subQ;
                        try {
                            subQ = Integer.parseInt(optQ);
                        } catch (Exception e) {
                            System.out.println("Opción inválida.");
                            continue;
                        }
                        if (subQ == 0)
                            break;
                        switch (subQ) {
                            case 1:
                                Turno prox = heapQuir.poll();
                                if (prox == null) {
                                    System.out.println("No hay turnos en el heap.");
                                } else {
                                    System.out.println("Próximo turno asignado:");
                                    System.out.println("  " + prox);
                                }
                                break;
                            case 2:
                                Turno peek = heapQuir.peek();
                                if (peek == null) {
                                    System.out.println("No hay turnos en el heap.");
                                } else {
                                    System.out.println("Siguiente turno en el heap:");
                                    System.out.println("  " + peek);
                                }
                                break;
                            case 3:
                                if (heapQuir.isEmpty()) {
                                    System.out.println("No hay turnos en el heap.");
                                } else {
                                    System.out
                                            .println("Turnos en el heap (orden interno, no necesariamente ordenados):");
                                    for (Turno t : heapQuir) {
                                        System.out.println("  " + t);
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Tamaño actual del heap: " + heapQuir.size());
                                break;
                            default:
                                System.out.println("Opción inválida.");
                                break;
                        }
                    }
                    break;
                case 10:
                    System.out.println("[10] Opción extra para futuras funcionalidades -> (a implementar)");
                    // Reserva: caso 10 solicitado (puede mapearse a otra función del práctico)
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    // cuando opcion == -1 ya se imprimió mensaje de parseo inválido
                    if (opcion != -1)
                        System.out.println("Opción inválida, intente nuevamente.");
                    break;
            }
        } while (opcion != 0);

        sc.close();
    }

    // Helper: parsear fecha/hora de un Turno usando los formatos provistos
    private static java.time.LocalDateTime parseTurnoDateTime(Turno t, java.time.format.DateTimeFormatter[] formatos) {
        if (t == null)
            return null;
        String fh = t.fecha + " " + t.hora;
        java.time.LocalDateTime dt = null;
        for (java.time.format.DateTimeFormatter fmt : formatos) {
            try {
                dt = java.time.LocalDateTime.parse(fh, fmt);
                return dt;
            } catch (Exception ex) {
                // seguir intentando
            }
        }
        try {
            dt = java.time.LocalDateTime.parse(t.fecha);
            return dt;
        } catch (Exception ex) {
        }
        return null;
    }
}
