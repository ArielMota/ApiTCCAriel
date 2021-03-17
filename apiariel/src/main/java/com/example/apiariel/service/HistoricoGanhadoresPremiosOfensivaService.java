/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.HistoricoGanhadoresPremiosOfensiva;
import com.example.apiariel.repository.HistoricoGanhadoresPremiosOfensivaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arielmota
 */
@Service
public class HistoricoGanhadoresPremiosOfensivaService {
    
    @Autowired
    HistoricoGanhadoresPremiosOfensivaRepository historicoGanhadoresPremiosOfensivaRepository;
    

    public HistoricoGanhadoresPremiosOfensiva cadastrarGanhadorPremioOfensiva(HistoricoGanhadoresPremiosOfensiva hgpo) {
        return historicoGanhadoresPremiosOfensivaRepository.save(hgpo);
    }
    
    public HistoricoGanhadoresPremiosOfensiva editarGanhadorPremioOfensival(HistoricoGanhadoresPremiosOfensiva hgpo) {
return historicoGanhadoresPremiosOfensivaRepository.save(hgpo);    }

    public List<HistoricoGanhadoresPremiosOfensiva> buscarTodos() {
        return historicoGanhadoresPremiosOfensivaRepository.findAll();//To change body of generated methods, choose Tools | Templates.
    }
    
}
