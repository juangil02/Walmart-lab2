/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.walmart.model;

/**
 *
 * @author USUARIO
 */
public class Applicant extends Person {
    private String nivelAcademico;
    private int experiencia;

    public Applicant(String nombre, String ciudad, int edad, String nivelAcademico, int experiencia) {
        super(nombre, ciudad, edad); 
        this.nivelAcademico = nivelAcademico;
        this.experiencia = experiencia;
    }

    public String getNivelAcademico() { return nivelAcademico; }
    public int getExperiencia() { return experiencia; }
}
