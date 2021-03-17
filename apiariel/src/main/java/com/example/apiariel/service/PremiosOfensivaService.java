/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.PremiosOfensiva;
import com.example.apiariel.repository.PremiosOfensivaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arielmota
 */

@Service
public class PremiosOfensivaService {

    @Autowired
    PremiosOfensivaRepository premiosOfensivaRepository;

    public PremiosOfensivaService() {
    }

    public List<PremiosOfensiva> buscaTodos() {
        return premiosOfensivaRepository.findAll();
    }

    public void cadastrarTresPosicoesVazias() {
        PremiosOfensiva premiosOfensiva = new PremiosOfensiva();
                PremiosOfensiva premiosOfensiva2 = new PremiosOfensiva();

                        PremiosOfensiva premiosOfensiva3 = new PremiosOfensiva();

        
        premiosOfensivaRepository.save(premiosOfensiva);
        premiosOfensivaRepository.save(premiosOfensiva2);
        premiosOfensivaRepository.save(premiosOfensiva3);

    }
    
       public void editaPremiosOfensiva(PremiosOfensiva premiosOfensiva){
         premiosOfensivaRepository.save(premiosOfensiva);
     }

}
