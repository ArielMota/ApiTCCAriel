/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.PontosOfensiva;
import com.example.apiariel.repository.PontosOfensivaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arielmota
 */

@Service
public class PontosOfensivaService {
    
    @Autowired
    PontosOfensivaRepository pontosOfensivaRepository;
    
    
    
    
      public PontosOfensiva editarPontoOfensiva(PontosOfensiva pontosOfensiva) {
        return pontosOfensivaRepository.save(pontosOfensiva);
    }
      
      
      public void excluirPontoOfensivaPorId(Long id) {
        pontosOfensivaRepository.deleteById(id);
    }
      
       public void zerarTodosPontosOfensiva(){
         pontosOfensivaRepository.zerarTodosPontosOfensiva();
     }
    
}
