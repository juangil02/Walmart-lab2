/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.walmart.service;

/**
 *
 * Clase abstracta que representa la estructura base de un contrato en Walmart.
 * Implementa {@link Calculator} para obligar a definir la lógica de pago.
 * @author USUARIO
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Contract implements Calculator {
    protected String position;
    protected double baseSalary;
    protected String type;
    protected String fechaEmision;

    public Contract(String position, double baseSalary, String type) {
        this.position = position;
        this.baseSalary = baseSalary;
        this.type = type;
        this.fechaEmision = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public abstract String getPayrollDetail();
}