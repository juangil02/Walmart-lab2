/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.walmart.service;

/**
 * Clase que representa un contrato por Prestación de Servicios en Walmart. 
 * Implementa la lógica de cobro para contratistas independientes, calculando
 * la seguridad social sobre el Ingreso Base de Cotización (IBC) del 40%.
 * @author USUARIO
 */
public class ServiceContract extends Contract {
    public ServiceContract(String position, double honorarios) {
        super(position, honorarios, "Prestación de Servicios");
    }

    @Override
    public double calculateGross() { return baseSalary; }

    @Override
    public double calculateDeductions() {
        double ibc = baseSalary * 0.40;
        return ibc * 0.285; 
    }

    @Override
    public double calculateNet() { return calculateGross() - calculateDeductions(); }

    @Override
    public String getPayrollDetail() {
        return "--- WALMART: HONORARIOS ---\n" +
               "Cargo: " + position + " | IBC (40%): $" + String.format("%,.0f", baseSalary*0.4) + "\n" +
               "Bruto: $" + String.format("%,.0f", calculateGross()) + "\n" +
               "Seguridad social: $" + String.format("%,.0f", calculateDeductions()) + "\n" +
               "Neto: $" + String.format("%,.0f", calculateNet());
    }
}
