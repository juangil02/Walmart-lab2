/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.walmart.model;

import com.walmart.service.LaborContract;

/**
 *
 * @author USUARIO
 */
import com.walmart.service.Contract;

public class Candidate extends Applicant {
    private Contract contract;
    private double puntajeEntrevista;
    private String observaciones;

    public Candidate(Applicant a) {
        super(a.getNombre(), a.getCiudad(), a.getEdad(), a.getNivelAcademico(), a.getExperiencia());
    }

    public void setPuntajeEntrevista(double puntaje) { this.puntajeEntrevista = puntaje; }
    public void setObservaciones(String obs) { this.observaciones = obs; }

    public void setContract(Contract contract) { this.contract = contract; }
    public Contract getContract() { return contract; }
}