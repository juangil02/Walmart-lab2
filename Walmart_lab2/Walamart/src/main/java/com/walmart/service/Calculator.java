/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.walmart.service;

/**
 *
 * Interfaz que define el contrato legal para el cálculo de nómina en Walmart segun la ley en 2026.
 * Proporciona las constantes vigentes según la ley colombiana y los métodos
 * necesarios para obtener el salario bruto, deducciones y neto.
 * @author USUARIO
 */
public interface Calculator {
    double MIN_WAGE_2026 = 1750950; 
    double TRANSPORT_AID = 249095;

    double calculateGross();
    double calculateDeductions();
    double calculateNet();
}
