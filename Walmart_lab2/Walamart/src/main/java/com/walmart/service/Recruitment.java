/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.walmart.service;

/**
* La clase Recruitment gestiona el proceso de filtrado de aplicantes
* para convertirlos en candidatos o rechazados según criterios específicos.
* @author USUARIO
*/
import com.mycompany.walmart.model.Applicant;
import com.mycompany.walmart.model.Candidate;
import java.util.ArrayList;
import java.util.Arrays;

public class Recruitment {
    private ArrayList<Applicant> applicants = new ArrayList<>();
    private ArrayList<Candidate> candidates = new ArrayList<>();
    private ArrayList<Applicant> rejects = new ArrayList<>();

    private ArrayList<String> allowedCities = new ArrayList<>(Arrays.asList("bogota", "soacha", "chia"));

    public void filterCandidates() {
        this.candidates.clear();
        this.rejects.clear();

        for (Applicant a : applicants) {
            boolean livesNear = false;
            for (String c : allowedCities) {
                if (a.getCiudad().equalsIgnoreCase(c)) {
                    livesNear = true;
                    break;
                }
            }
            if (a.getEdad() >= 18 && livesNear && 
               (a.getNivelAcademico().equalsIgnoreCase("Bachiller") || 
                a.getNivelAcademico().equalsIgnoreCase("Universitario")) && 
                a.getExperiencia() > 0) {
                this.candidates.add(new Candidate(a)); 
            } else {
                this.rejects.add(a);
            }
        }
    }

    public ArrayList<Applicant> getApplicants() { return applicants; }
    public ArrayList<Candidate> getCandidates() { return candidates; }
}