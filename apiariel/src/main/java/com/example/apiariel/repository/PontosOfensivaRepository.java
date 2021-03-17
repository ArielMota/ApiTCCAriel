/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.repository;

import com.example.apiariel.model.PontosCristal;
import com.example.apiariel.model.PontosOfensiva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author arielmota
 */
public interface PontosOfensivaRepository extends JpaRepository<PontosOfensiva, Long> {
    
    
    @Query(value = "UPDATE `pontos_ofensiva` SET `quantidade` = '0' WHERE 1 = 1", nativeQuery = true)
        public List<PontosCristal> zerarTodosPontosOfensiva();
}
