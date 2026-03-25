/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.walmart.service;

/**
 * Clase que representa un contrato de Trabajo por Horas en Walmart.
 * Implementa la lógica para el cálculo de recargos por horas extras diurnas (25%),
 * nocturnas (75%) y la provisión proporcional de vacaciones (4.17%).
 * @author USUARIO
 */
public class HourlyContract extends Contract {
    private double valorHora;
    private int horasOrdinarias, extrasDiurnas, extrasNocturnas;

    public HourlyContract(String position, double vHora, int ord, int diu, int noc) {
        super(position, 0, "Trabajo por Horas");
        this.valorHora = vHora;
        this.horasOrdinarias = ord;
        this.extrasDiurnas = diu;
        this.extrasNocturnas = noc;
    }

    @Override
    public double calculateGross() {
        double basico = valorHora * horasOrdinarias;
        double vDiurnas = (valorHora * 1.25) * extrasDiurnas;
        double vNocturnas = (valorHora * 1.75) * extrasNocturnas;
        double vacaciones = basico * 0.0417; 
        return basico + vDiurnas + vNocturnas + vacaciones;
    }

    @Override
    public double calculateDeductions() {
        return (valorHora * horasOrdinarias) * 0.08; 
    }

    @Override
    public double calculateNet() {
        double bruto = calculateGross();
        double auxilio = (bruto <= 2 * MIN_WAGE_2026) ? TRANSPORT_AID : 0;
        return bruto - calculateDeductions() + auxilio;
    }

    @Override
    public String getPayrollDetail() {
        return "--- WALMART: REPORTE POR HORAS ---\n" +
               "Fecha: " + fechaEmision + " | Cargo: " + position + "\n" +
               "Básico: $" + String.format("%,.0f", valorHora * horasOrdinarias) + "\n" +
               "Extras (D/N): $" + String.format("%,.0f", (valorHora*1.25*extrasDiurnas)+(valorHora*1.75*extrasNocturnas)) + "\n" +
               "Vacaciones: $" + String.format("%,.0f", (valorHora*horasOrdinarias*0.0417)) + "\n" +
               "Bruto: $" + String.format("%,.0f", calculateGross()) + "\n" +
               "Deducciones: $" + String.format("%,.0f", calculateDeductions()) + "\n" +
               "Neto: $" + String.format("%,.0f", calculateNet());
    }
}
