package data;

import modelo.*;
import java.io.*;
import java.time.*;
import java.util.*;

public class Data {
    public static List<Paciente> pacientes = new ArrayList<>();
    public static List<Medico> medicos = new ArrayList<>();
    public static List<Turno> turnos = new ArrayList<>();

    public static void cargarCSV() {
        try {
            System.out.println("> Leyendo pacientes.csv ...");
            leerPacientes("data/pacientes.csv");
            System.out.println("> Leyendo medicos.csv ......");
            leerMedicos("data/medicos.csv");
            System.out.println("> Leyendo turnos.csv .......");
            leerTurnos("data/turnos.csv");

            validarDatos();

            System.out.println("> Estructuras internas inicializadas correctamente.");

        } catch (Exception e) {
            System.out.println("Error cargando datos: " + e.getMessage());
        }
    }

    private static void leerPacientes(String ruta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;
        br.readLine(); // encabezado
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(",");
            pacientes.add(new Paciente(campos[0], campos[1]));
        }
        br.close();
        System.out.println("[OK] (" + pacientes.size() + " registros)");
    }

    private static void leerMedicos(String ruta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;
        br.readLine();
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(",");
            medicos.add(new Medico(campos[0], campos[1], campos[2]));
        }
        br.close();
        System.out.println("[OK] (" + medicos.size() + " registros)");
    }

    private static void leerTurnos(String ruta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;
        br.readLine();
        while ((linea = br.readLine()) != null) {
            String[] campos = linea.split(",");
            try {
                Turno t = new Turno(
                    campos[0],
                    campos[1],
                    campos[2],
                    LocalDateTime.parse(campos[3]),
                    Integer.parseInt(campos[4]),
                    campos[5]
                );
                turnos.add(t);
            } catch (Exception e) {
                System.out.println("Error parseando turno: " + linea);
            }
        }
        br.close();
        System.out.println("[OK] (" + turnos.size() + " registros)");
    }

    private static void validarDatos() {
        System.out.println("> Validando datos ...");
        int rechazadosFecha = 0;
        int rechazadosDuracion = 0;
        int rechazadosDuplicados = 0;
        int rechazadosReferencia = 0;

        Set<String> ids = new HashSet<>();
        List<Turno> validos = new ArrayList<>();

        for (Turno t : turnos) {
            boolean valido = true;

            // fecha futura
            if (t.fechaHora.isBefore(LocalDateTime.now())) {
                rechazadosFecha++;
                valido = false;
            }

            // duracion > 0
            if (t.duracionMin <= 0) {
                rechazadosDuracion++;
                valido = false;
            }

            // ID duplicado
            if (!ids.add(t.id)) {
                rechazadosDuplicados++;
                valido = false;
            }

            // referencias válidas
            if (!existePaciente(t.dniPaciente) || !existeMedico(t.matriculaMedico)) {
                rechazadosReferencia++;
                valido = false;
            }

            if (valido) validos.add(t);
        }

        turnos = validos;

        System.out.println("- " + rechazadosFecha + " turnos rechazados (fecha pasada)");
        System.out.println("- " + rechazadosDuracion + " turnos rechazados (duración inválida)");
        System.out.println("- " + rechazadosDuplicados + " turnos duplicados");
        System.out.println("- " + rechazadosReferencia + " turnos con paciente/médico inexistente");
    }

    private static boolean existePaciente(String dni) {
        return pacientes.stream().anyMatch(p -> p.dni.equals(dni));
    }

    private static boolean existeMedico(String matricula) {
        return medicos.stream().anyMatch(m -> m.matricula.equals(matricula));
    }
}
