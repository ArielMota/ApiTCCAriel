/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.repository;

import com.example.apiariel.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author arielmota
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    public Cliente findByLoginAndSenha(@Param("login")String login,@Param("senha")String senha);
    
    public Cliente findByLogin(@Param("login")String login);
    
    @Query(value = "SELECT * FROM cliente c LEFT JOIN pontos_cristal p ON c.id = p.cliente_id LEFT JOIN pontos_ofensiva po ON c.id_ponto_ofensiva = po.id GROUP by c.id ORDER BY SUM(p.valor_pontos) DESC, po.quantidade DESC, c.id ASC", nativeQuery = true)
    public List<Cliente> buscarClientesOrdenadoPelaMaiorPontuacao();
    
    @Query(value = "SELECT * FROM cliente c LEFT JOIN pontos_ofensiva p ON c.id_ponto_ofensiva = p.id LEFT JOIN pontos_cristal pc ON c.id = pc.cliente_id GROUP by c.id ORDER BY p.quantidade DESC, SUM(pc.valor_pontos) DESC, c.id ASC", nativeQuery = true)
    public List<Cliente> buscarClientesOrdenadoPelaMaiorOfensiva();
            
    @Query(value = "UPDATE cliente SET senha = :senha WHERE cliente.id = :id", nativeQuery = true)
    public Cliente alterarCliente(@Param("id")long id,@Param("senha")String senha);
    
    @Query(value = "SELECT * FROM cliente WHERE login LIKE %:login%", nativeQuery = true)
    public List<Cliente> clientesFindByLogin(@Param("login") String login);
    
    @Query(value = "SELECT * FROM cliente WHERE login LIKE %:login%", nativeQuery = true)
    public List<Cliente> sugestaoLoginclientesFindByLogin(@Param("login") String login);

}


