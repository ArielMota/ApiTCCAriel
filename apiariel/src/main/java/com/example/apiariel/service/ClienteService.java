/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.Cliente;
import com.example.apiariel.repository.ClienteRepository;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arielmota
 */
@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteService() {
    }

    public void cadastrarCliente(Cliente cli) {
        
        Criptografia c = new Criptografia();
        
        try {
            cli.setSenha(c.criptografarSenha(cli.getSenha()));
                    clienteRepository.save(cli);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarCliente(Cliente cli) {
        Criptografia c = new Criptografia();
        
        try {
            cli.setSenha(c.criptografarSenha(cli.getSenha()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        clienteRepository.alterarCliente(cli.getId(), cli.getSenha());
    }

    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscaClienteId(Long id) {
        return clienteRepository.findById(id).get();
    }

    public List<Cliente> buscaClientesPorNome(String login) {
        return clienteRepository.clientesFindByLogin(login);
    }

    public List<Cliente> buscarSugestaoLoginClientes(String login) {
        return clienteRepository.sugestaoLoginclientesFindByLogin(login);
    }

    public Cliente buscaClienteLogin(String login) {
        Cliente cliente = clienteRepository.findByLogin(login);
        
        System.out.println(cliente.getGenero_de_filme_preferido());
        

        return cliente;
    }

    public java.util.List<Cliente> buscaTodos() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes;
    }

    public java.util.List<Cliente> buscaTodosOrdenadoPorMaiorPontuacao() {
        return clienteRepository.buscarClientesOrdenadoPelaMaiorPontuacao();
    }

    public java.util.List<Cliente> buscaTodosOrdenadoPorMaiorOfensiva() {
        return clienteRepository.buscarClientesOrdenadoPelaMaiorOfensiva();
    }

    public int buscaPosicaoRankingClienteCristais(Cliente cliente) {

        List<Cliente> clientes = buscaTodosOrdenadoPorMaiorPontuacao();

        int valor = clientes.indexOf(cliente);

        return valor;
    }

    public Cliente autenticarCliente(Cliente cli) {
        Criptografia c = new Criptografia();
        
        try {
            cli.setSenha(c.criptografarSenha(cli.getSenha()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return clienteRepository.findByLoginAndSenha(cli.getLogin(), cli.getSenha());
    }

    public boolean recuperarSenha(Cliente cli) {
        System.out.println(cli.getGenero_de_filme_preferido());

        Cliente c = buscaClienteLogin(cli.getLogin());

        System.out.println(c.getGenero_de_filme_preferido());

        return c.getNome_do_melhor_amigo_de_infancia().equals(cli.getNome_do_melhor_amigo_de_infancia())
                && c.getGenero_de_filme_preferido().equals(cli.getGenero_de_filme_preferido())
                && c.getNome_do_primeiro_cachorro().equals(cli.getNome_do_primeiro_cachorro());

    }

    public void zerarOfensivaDiariaConcluida() {
        List<Cliente> clientes = clienteRepository.findAll();

        for (Cliente cliente : clientes) {
            cliente.setOfensiva_diaria_concluida(Boolean.FALSE);
            cadastrarCliente(cliente);
        }

    }
    
    public void concluirOfensivaDiariaConcluida(String login) {
       Cliente cliente = clienteRepository.findByLogin(login);

            cliente.setOfensiva_diaria_concluida(Boolean.TRUE);
            cadastrarCliente(cliente);

    }
    
    public void cancelarOfensivaDiariaConcluida(String login) {
       Cliente cliente = clienteRepository.findByLogin(login);

            cliente.setOfensiva_diaria_concluida(Boolean.FALSE);
            cadastrarCliente(cliente);

    }
    
    public Long buscarTotalPontosOfensivaCliente(String login){
      Cliente cliente =  clienteRepository.findByLogin(login);
      
      return cliente.getPontosOfensiva().getQuantidade();
    }
    
     public void adcionarUmPontoOfensivaCliente(String login){
      Cliente cliente =  clienteRepository.findByLogin(login);
      cliente.getPontosOfensiva().setQuantidade(cliente.getPontosOfensiva().getQuantidade() + 1);
      
         cadastrarCliente(cliente);
    }
     
    public void removerUmPontoOfensivaCliente(String login){
      Cliente cliente =  clienteRepository.findByLogin(login);
      cliente.getPontosOfensiva().setQuantidade(cliente.getPontosOfensiva().getQuantidade() - 1);
      
         cadastrarCliente(cliente);
    }
}
