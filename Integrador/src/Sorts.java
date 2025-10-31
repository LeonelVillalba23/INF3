package src;

import java.util.*;

public class Sorts {

    // Insertion sort (estable)
    public static void insertionSort(List<Turno> a, Comparator<Turno> cmp) {
        for (int i = 1; i < a.size(); i++) {
            Turno key = a.get(i);
            int j = i - 1;
            while (j >= 0 && cmp.compare(a.get(j), key) > 0) {
                a.set(j + 1, a.get(j));
                j--;
            }
            a.set(j + 1, key);
        }
    }

    // Shellsort (gap sequence n/2)
    public static void shellSort(List<Turno> a, Comparator<Turno> cmp) {
        int n = a.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Turno temp = a.get(i);
                int j;
                for (j = i; j >= gap && cmp.compare(a.get(j - gap), temp) > 0; j -= gap) {
                    a.set(j, a.get(j - gap));
                }
                a.set(j, temp);
            }
        }
    }

    // Quicksort Lomuto
    public static void quickSort(List<Turno> a, Comparator<Turno> cmp) {
        quickSortRec(a, 0, a.size() - 1, cmp);
    }

    private static void quickSortRec(List<Turno> a, int lo, int hi, Comparator<Turno> cmp) {
        if (lo < hi) {
            int p = partition(a, lo, hi, cmp);
            quickSortRec(a, lo, p - 1, cmp);
            quickSortRec(a, p + 1, hi, cmp);
        }
    }

    private static int partition(List<Turno> a, int lo, int hi, Comparator<Turno> cmp) {
        Turno pivot = a.get(hi);
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (cmp.compare(a.get(j), pivot) <= 0) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, hi);
        return i + 1;
    }

    private static void swap(List<Turno> a, int i, int j) {
        Turno tmp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, tmp);
    }

    // Generador sintético de turnos: n elementos, reutiliza pacientes y medicos si
    // existen
    public static List<Turno> generateSyntheticTurnos(int n, List<Paciente> pacientes, List<Medico> medicos) {
        List<Turno> res = new ArrayList<>(n);
        Random rnd = new Random(12345);
        int pc = Math.max(1, pacientes.size());
        int mc = Math.max(1, medicos.size());
        java.time.LocalDate start = java.time.LocalDate.now().plusDays(1);
        for (int i = 0; i < n; i++) {
            String id = "SYN-" + i;
            java.time.LocalDate d = start.plusDays(i / 8); // varios por dia
            java.time.LocalTime t = java.time.LocalTime.of(8 + (i % 8), (i % 2) * 30);
            String fecha = d.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String hora = t.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
            int dur = 15 + rnd.nextInt(60);
            Paciente p = pacientes.isEmpty() ? new Paciente("00000000", "Paciente") : pacientes.get(i % pc);
            Medico m = medicos.isEmpty() ? new Medico("M-000", "Dr. X", "Gen") : medicos.get(i % mc);
            String motivo = "Sintético";
            res.add(new Turno(id, fecha, hora, dur, p.dni, m.matricula, motivo));
        }
        return res;
    }
}
