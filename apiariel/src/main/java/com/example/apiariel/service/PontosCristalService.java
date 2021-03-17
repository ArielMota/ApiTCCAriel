/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.Cliente;
import com.example.apiariel.model.Cristal;
import com.example.apiariel.model.PontosCristal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apiariel.repository.PontosCristalRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author arielmota
 */
@Service
public class PontosCristalService {

    @Autowired
    PontosCristalRepository pontosCristalRepository;
    
    @Autowired
    CristalService cristalService;

    public List<PontosCristal> buscaTodos() {

        return pontosCristalRepository.findAll();

    }

    public Long valorTotalPontosCristalCliente(Long id) {

        return pontosCristalRepository.valorTotalPontosCristal(id);

    }

    public List<PontosCristal> buscaTodosPontosCristalDoCliente(Cliente cliente) {
        return pontosCristalRepository.buscaTodosPontosCristalDoCliente(cliente.getId().intValue());
    }

    public PontosCristal cadastrarPontoCristal(PontosCristal pontosCristal) {
        
        Cristal c = cristalService.buscarValorCristal().get(0);
        System.out.println(c.getValorDoCristal());
        
        pontosCristal.setValor_pontos(pontosCristal.getValor_pontos() / c.getValorDoCristal());
        
        return pontosCristalRepository.save(pontosCristal);
    }

    public PontosCristal buscarPontoCristalPorId(long id) {
        return pontosCristalRepository.findById(id).get();
    }

    public PontosCristal editarPontoCristal(PontosCristal pontoscristal) {
        return pontosCristalRepository.save(pontoscristal);
    }

    public void excluirPontoCristalPorId(Long id) {
        pontosCristalRepository.deleteById(id);
    }
     public void deletarTodosPontosCristais(){
         pontosCristalRepository.deleteAll();
     }

    public Long buscarTotalPontosCristalGanhosDiaCliente(Long id) {

        LocalDateTime date = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        sdf1.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Sao_Paulo")));
        Date y = new Date();
        String dataValor = "%" + sdf1.format(y) + "%";

        Long valorTotal = pontosCristalRepository.TotalPontosCristalGanhosDia(id, dataValor);

        return valorTotal;

    }

}
