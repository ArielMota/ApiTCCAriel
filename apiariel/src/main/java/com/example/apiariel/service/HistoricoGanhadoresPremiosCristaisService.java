/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.HistoricoGanhadoresPremiosCristais;
import com.example.apiariel.repository.HistoricoGanhadoresPremiosCristaisRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arielmota
 */
@Service
public class HistoricoGanhadoresPremiosCristaisService {

    @Autowired
    HistoricoGanhadoresPremiosCristaisRepository historicoGanhadoresPremiosCristaisRepository;

    public HistoricoGanhadoresPremiosCristais cadastrarGanhadorPremioCristal(HistoricoGanhadoresPremiosCristais hgpcr) {
        return historicoGanhadoresPremiosCristaisRepository.save(hgpcr);
    }
    
        public HistoricoGanhadoresPremiosCristais editarGanhadorPremioCristal(HistoricoGanhadoresPremiosCristais hgpcr) {
        return historicoGanhadoresPremiosCristaisRepository.save(hgpcr);
    }


    public List<HistoricoGanhadoresPremiosCristais> buscarTodos() {
        return historicoGanhadoresPremiosCristaisRepository.findAll();//To change body of generated methods, choose Tools | Templates.
    }
}
