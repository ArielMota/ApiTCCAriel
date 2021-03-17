/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author arielmota
 */
@Entity
public class Cristal {

    private Long id;
    private double valorDoCristal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public double getValorDoCristal() {
        return valorDoCristal;
    }

    public void setValorDoCristal(double valorDoCristal) {
        this.valorDoCristal = valorDoCristal;
    }
    
    

}
