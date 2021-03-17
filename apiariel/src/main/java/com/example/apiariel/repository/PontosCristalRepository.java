/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.repository;

import com.example.apiariel.model.Cliente;
import com.example.apiariel.model.PontosCristal;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author arielmota
 */
public interface PontosCristalRepository extends JpaRepository<PontosCristal, Long> {
    
        public List<PontosCristal> findByCliente(Cliente cliente);
        
        @Query(value = "SELECT SUM(p.valor_pontos) FROM pontos_cristal p WHERE cliente_id = :id", nativeQuery = true)
        public Long valorTotalPontosCristal(@Param("id") Long id);
        
        @Query(value = "SELECT SUM(p.valor_pontos) FROM pontos_cristal p WHERE `data` LIKE :data AND `cliente_id` = :id", nativeQuery = true)
        public Long TotalPontosCristalGanhosDia(@Param("id") Long id, @Param("data") String data);
        
        @Query(value = "SELECT * FROM pontos_cristal WHERE pontos_cristal.cliente_id = :id ORDER BY pontos_cristal.data DESC", nativeQuery = true)
        public List<PontosCristal> buscaTodosPontosCristalDoCliente(@Param("id")int id);
        
        


    
     
     //@Query("INSERT INTO `pontos_diamante` (`id`, `data`, `cliente_id`) VALUES (NULL, ':A', ':B')")
     //public  void AdcionarPontosDiamante( @Param ("A")Date data, @Param ("B")Long cliente_id);
    
}
