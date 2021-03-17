/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.Cliente;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author arielmota
 */
@Component
public class ZerarOfensivasConcluidas {

    @Autowired
    ClienteService clienteService;

    @Scheduled(cron = "59 59 18 * * *")
    public void verificaPorDia() {

        if (clienteService != null) {
            clienteService.zerarOfensivaDiariaConcluida();
        } else {

            System.out.println("headHunterService Object is null");
        }

    }
}
