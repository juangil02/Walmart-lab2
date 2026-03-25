/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.walmart.service;

/**
 * Clase que representa un contrato laboral de salario mensual en Walmart.
 * Implementa la lógica de nómina estándar bajo la ley colombiana, incluyendo
 * aportes a seguridad social y auxilio de transporte.
 * @author USUARIO
 */
public class LaborContract extends Contract {
    public LaborContract(String position, double baseSalary, String type) {
        super(position, baseSalary, type); 
    }

    @Override
    public double calculateGross() { 
        return baseSalary; 
    }

    @Override
    public double calculateDeductions() {
        return calculateGross() * 0.08; 
    }

    @Override
    public double calculateNet() {
        double aid = (baseSalary <= 2 * MIN_WAGE_2026) ? TRANSPORT_AID : 0;
        return calculateGross() - calculateDeductions() + aid;
    }

    @Override
    public String getPayrollDetail() {
        double aid = (baseSalary <= 2 * MIN_WAGE_2026) ? TRANSPORT_AID : 0;
        
        return "--- WALMART: COMPROBANTE DE PAGO MENSUAL ---\n" +
               "FECHA: " + fechaEmision + " | CARGO: " + position + "\n" +
               "TIPO: " + type + "\n" +
               "------------------------------------------\n" +
               "Salario brutoO:     $" + String.format("%,.0f", calculateGross()) + "\n" +
               "Salud (4%):        $" + String.format("%,.0f", calculateGross() * 0.04) + "\n" +
               "Pension (4%):      $" + String.format("%,.0f", calculateGross() * 0.04) + "\n" +
               "Aux. Transporte:   $" + String.format("%,.0f", aid) + "\n" +
               "------------------------------------------\n" +
               "Total neto a pagar:    $" + String.format("%,.0f", calculateNet()) + "\n" +
               "------------------------------------------";
    }
}