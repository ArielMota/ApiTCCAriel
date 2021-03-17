/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.repository;

import com.example.apiariel.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author arielmota
 */
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    public Administrador findByLoginAndSenha(@Param("login")String login,@Param("senha")String senha);
    
}
