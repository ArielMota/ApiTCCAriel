/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.Administrador;
import com.example.apiariel.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arielmota
 */
@Service
public class AdministradorService {
    
    @Autowired   
    AdministradorRepository administradorRepository;

    public Administrador autenticarAdministrador(Administrador adm) {
        return administradorRepository.findByLoginAndSenha(adm.getLogin(),adm.getSenha());
        
    }
    
}
