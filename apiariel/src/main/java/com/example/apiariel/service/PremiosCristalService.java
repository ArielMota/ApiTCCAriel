/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.PremiosCristal;
import com.example.apiariel.model.PremiosOfensiva;
import com.example.apiariel.repository.PremiosCristalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arielmota
 */

@Service
public class PremiosCristalService {

    @Autowired
    PremiosCristalRepository premiosCristalRepository;

    public PremiosCristalService() {

    }
    
    public List<PremiosCristal> buscaTodos(){
        return premiosCristalRepository.findAll();
    }
    
     public void cadastrarTresPosicoesVazias() {
        PremiosCristal premiosCristal = new PremiosCristal();
                PremiosCristal premiosCristal2 = new PremiosCristal();
        PremiosCristal premiosCristal3 = new PremiosCristal();

        
        premiosCristalRepository.save(premiosCristal);
        premiosCristalRepository.save(premiosCristal2);
        premiosCristalRepository.save(premiosCristal3);

    }
     
     public void editaPremiosCristal(PremiosCristal premiosCristal){
         premiosCristalRepository.save(premiosCristal);
     }
     
    

}
