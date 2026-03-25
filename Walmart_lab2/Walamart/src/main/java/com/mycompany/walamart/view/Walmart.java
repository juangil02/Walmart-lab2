/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.walamart.view;

/**
 *Muestra un menú en la maquina para que el entrevistador pueda gestionar 
 *el ingreso de hojas de vida, hacer entrevistas y ver resultados.
 * @author USUARIO
 */
import javax.swing.JOptionPane;
import com.mycompany.walmart.model.*;
import com.walmart.service.*;
import java.util.ArrayList;

public class Walmart {
    private static Recruitment recruitmentManager = new Recruitment();

    public static void main(String[] args) {
        int opcion; 
        do { 
            String menuPrincipal = "--- BIENVENIDO A WALMART 2026 ---\n" +
                                 "1. Ingresar Hoja de Vida (Aspirante)\n" +
                                 "2. Iniciar como Recursos Humanos (RRHH)\n" +
                                 "3. Salir";
            
            String input = JOptionPane.showInputDialog(menuPrincipal);
            if (input == null) break;
            opcion = Integer.parseInt(input);

            switch (opcion) {
                case 1: ingresarHojaDeVida(); break;
                case 2: menuRRHH(); break;
                case 3: JOptionPane.showMessageDialog(null, "Cerrando sistema..."); break;
            }
        } while (opcion != 3);
    }
    
    /**
    *El metodo encargado de capturar los datos de el aspirante
    */
    public static void ingresarHojaDeVida() {
        String nombre = JOptionPane.showInputDialog("Nombre completo:");
        String ciudad = JOptionPane.showInputDialog("Ciudad de residencia:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        String nivel = JOptionPane.showInputDialog("Nivel Académico:");
        int exp = Integer.parseInt(JOptionPane.showInputDialog("Años de experiencia:"));

        Applicant nuevo = new Applicant(nombre, ciudad, edad, nivel, exp);
        recruitmentManager.getApplicants().add(nuevo);
        JOptionPane.showMessageDialog(null, "Hoja de vida registrada.");
    }
    
    /**
    * Menu que usara en gestor de recursos humanos
    */
    public static void menuRRHH() {
        String menu = "--- MÓDULO RRHH WALMART ---\n" +
                      "1. Filtrar Candidatos Aptos\n" +
                      "2. Realizar Entrevistas\n" +
                      "3. Gestionar Nómina y Contratos\n" +
                      "4. Volver";
        
        String input = JOptionPane.showInputDialog(menu);
        if (input == null) return;
        int opcion = Integer.parseInt(input);

        switch (opcion) {
            case 1: filtrarCandidatos(); break;
            case 2: realizarEntrevistas(); break;
            case 3: gestionarNomina(); break;
        }
    }
    
    /**
    * llama al metodo de recruitment para pasar de aspitante en candidato apto
    */
    public static void filtrarCandidatos() {
        recruitmentManager.filterCandidates();
        
        int totalAptos = recruitmentManager.getCandidates().size();
        JOptionPane.showMessageDialog(null, "Filtrado completado en clase Recruitment.\n" +
                                           "Candidatos Aptos: " + totalAptos);
    }
    
    /**
    * llama al metodo de recruitment para pasar de aspitante en candidato apto
    */
    public static void realizarEntrevistas() {
        ArrayList<Candidate> aptos = recruitmentManager.getCandidates();
        
        if (aptos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay candidatos aptos. Filtre primero.");
            return;
        }

        for (Candidate c : aptos) {
            Interview interview = new Interview();
            interview.realizarCuestionario(c.getNombre());
            
            
            c.setPuntajeEntrevista(interview.getPuntajeTotal());
            c.setObservaciones(interview.getObservaciones());

            if (!interview.esApto()) {
                JOptionPane.showMessageDialog(null, c.getNombre() + " NO cumple puntaje mínimo.");
            }
        }
    }
    
    /**
    * Muestra las opciones de tipo de contrato para sacr la nomina, ya que dependiendo del tipo de contrato tiene un proceso distinto
    */
    public static void gestionarNomina() {
        ArrayList<Candidate> aptos = recruitmentManager.getCandidates();
        
        if (aptos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay candidatos para contratar.");
            return;
        }

        String[] tipos = {"Prestación", "Por Horas", "Laboral"};
        for (Candidate can : aptos) {
            int sel = JOptionPane.showOptionDialog(null, "Contrato para: " + can.getNombre(), 
                      "RRHH Walmart", 0, JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

            if (sel == -1) continue;
            String cargo = JOptionPane.showInputDialog("Nombre del Cargo:");
            Contract con = null;

            switch (sel) {
                case 0:
                    con = new ServiceContract(cargo, Double.parseDouble(JOptionPane.showInputDialog("Honorarios:")));
                    break;
                case 1:
                    double vH = Double.parseDouble(JOptionPane.showInputDialog("Valor Hora:"));
                    int ord = Integer.parseInt(JOptionPane.showInputDialog("Horas Mes:"));
                    int ext = Integer.parseInt(JOptionPane.showInputDialog("Extras Diurnas (25%):"));
                    con = new HourlyContract(cargo, vH, ord, ext, 0);
                    break;
                case 2:
                    con = new LaborContract(cargo, Double.parseDouble(JOptionPane.showInputDialog("Sueldo Base:")), "Fijo");
                    break;
                default:
                    break;
            }

            can.setContract(con);
            if (can.getContract() != null) {
                JOptionPane.showMessageDialog(null, can.getContract().getPayrollDetail());
            }
        }
    }
}  
