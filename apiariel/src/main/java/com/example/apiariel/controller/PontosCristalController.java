/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.controller;

import com.example.apiariel.model.Cliente;
import com.example.apiariel.model.PontosCristal;
import com.example.apiariel.service.PontosCristalService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author arielmota
 */

@RestController
public class PontosCristalController {
    @Autowired
    PontosCristalService pontoscristalservice;
    
    
    //Busca Todos Pontos Diamantes existentes
     @RequestMapping(method = RequestMethod.GET, value = "/pontosdiamante", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List <PontosCristal>> mostraTodosClientess(){
    
            List <PontosCristal> pontoscristal;
        try {
            pontoscristal = pontoscristalservice.buscaTodos();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pontoscristal, HttpStatus.OK);
    }
    
    //Busca todos pontos do cliente através do {id}
    @RequestMapping(method = RequestMethod.GET, value = "/pontoscristalcliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity <List<PontosCristal>> mostraTodosPontosCristaleDoCliente(@PathVariable Long id) {
        
        Cliente cliente = new Cliente();
        cliente.setId(id);
        List<PontosCristal> list_pontos_cristal;
        
        try {
            list_pontos_cristal = pontoscristalservice.buscaTodosPontosCristalDoCliente(cliente);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(list_pontos_cristal, HttpStatus.OK);
    }
    
     @RequestMapping(method = RequestMethod.GET, value = "/somapontoscristaldiario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity <List<PontosCristal>> buscarTotalPontosCristalGanhosDiaCliente(@PathVariable Long id) {
        Long somaPontosCristalNoDia;
        
        try {
            somaPontosCristalNoDia = pontoscristalservice.buscarTotalPontosCristalGanhosDiaCliente(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(somaPontosCristalNoDia, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/somapontoscristalcliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity <List<PontosCristal>> mostraSomaPontosCristaleDoCliente(@PathVariable Long id) {
        
        Long somaPontosCristal;
        
        try {
            somaPontosCristal = pontoscristalservice.valorTotalPontosCristalCliente(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(somaPontosCristal, HttpStatus.OK);
    }
    
    //Cadastrar pontos diamante para o cliente
     @RequestMapping(method = RequestMethod.POST,value = "/admin/pontoscristal", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarPontoCristal(@RequestBody PontosCristal pontosCristal) {

        pontoscristalservice.cadastrarPontoCristal(pontosCristal);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    
    // Buscar ponto diamante pelo {id}
    @RequestMapping(method = RequestMethod.GET, value = "/pontoscristal/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity   buscarPontoCristalPorId(@PathVariable Long id) {
        
                PontosCristal pontosDiamante;

        
        try {
            
            pontosDiamante = pontoscristalservice.buscarPontoCristalPorId(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pontosDiamante, HttpStatus.OK);
    }
     
    //Deletar ponto diamante pelo {id}
    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/pontoscristal/{id}")
    ResponseEntity removerPontoCristal(@PathVariable Long id) {

        
        
         try {
            
        pontoscristalservice.excluirPontoCristalPorId(id);
        
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
    
    //Editar ponto diamante
     @RequestMapping(method = RequestMethod.PUT,value = "/admin/pontoscristal", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity EditarCristal(@RequestBody PontosCristal pontosCristal) {

        pontoscristalservice.editarPontoCristal(pontosCristal);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    
}
