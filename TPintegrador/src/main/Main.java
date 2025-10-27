package main;

import data.Data;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE TURNOS MÉDICOS ===");
        System.out.println("Cargando datos iniciales...");

        Data.cargarCSV();

        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrarMenu();
    }
}
