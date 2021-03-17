/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.Cristal;
import com.example.apiariel.repository.ClienteRepository;
import com.example.apiariel.repository.CristalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arielmota
 */
@Service
public class CristalService {
    
    @Autowired
    CristalRepository cristalRepository;
    
    public Cristal editarValorCristal(Cristal cristal){
        
        return cristalRepository.save(cristal);
    }
    
    
    public List<Cristal> buscarValorCristal(){
        
        return cristalRepository.findAll();
    }
    
}
