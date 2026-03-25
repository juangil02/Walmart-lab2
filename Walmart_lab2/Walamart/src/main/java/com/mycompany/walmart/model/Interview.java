/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.walmart.model;

/**
 *Esta clase guarda todo lo que pasa en la entrevista con el candidato:
 *sus respuestas, el puntaje que sacó y las notas del entrevistador.
 *@author USUARIO
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Interview {
    private ArrayList<String> respuestas;
    private ArrayList<Integer> puntajesPorPregunta;
    private String observaciones;
    private int puntajeTotal;

    public Interview() {
        this.respuestas = new ArrayList<>();
        this.puntajesPorPregunta = new ArrayList<>();
        this.observaciones = "";
        this.puntajeTotal = 0;
    }

    /**
     * Lógica para registrar una respuesta y acumular puntaje
     */
    public void registrarRespuesta(String respuesta, int puntaje) {
        this.respuestas.add(respuesta);
        this.puntajesPorPregunta.add(puntaje);
        this.puntajeTotal += puntaje;
    }

    /**
     * Proceso de entrevista interactivo
     */
    public void realizarCuestionario(String nombreCandidato) {
        this.respuestas.clear();
        this.puntajesPorPregunta.clear();
        this.puntajeTotal = 0;

        String[] preguntas = {
            "¿Por qué desea trabajar en Walmart?",
            "Describa una situación difícil que haya resuelto:",
            "¿Está interesado en hacer carrera y progresar en la empresa?",
            "¿Cuáles son sus expectativas reales del trabajo?",
            "¿Prefiere el trabajo en equipo o roles de liderazgo?"
        };

        JOptionPane.showMessageDialog(null, "Iniciando entrevista para: " + nombreCandidato);

        for (String pregunta : preguntas) {
            String resp = JOptionPane.showInputDialog(nombreCandidato + "\n" + pregunta);
            
            // Validación básica para evitar errores si cancelan el diálogo
            String califStr = JOptionPane.showInputDialog("Calificación para esta respuesta (1 a 5):");
            int p = (califStr != null) ? Integer.parseInt(califStr) : 0;
            
            this.registrarRespuesta(resp, p);
        }
        
        this.observaciones = JOptionPane.showInputDialog("Observaciones finales del entrevistador:");
        
        JOptionPane.showMessageDialog(null, "Entrevista finalizada.\nPuntaje Total: " + this.puntajeTotal);
    }

    /**
     * Determina si el candidato es apto según el puntaje (Ej: Min 15 puntos)
     */
    public boolean esApto() {
        return this.puntajeTotal >= 15;
    }

    public int getPuntajeTotal() { return puntajeTotal; }
    public String getObservaciones() { return observaciones; }
    public ArrayList<String> getRespuestas() { return respuestas; }
}