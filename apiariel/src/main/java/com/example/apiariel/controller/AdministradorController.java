/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.controller;

import com.example.apiariel.config.Autenticacao;
import com.example.apiariel.model.Administrador;
import com.example.apiariel.model.Cliente;
import com.example.apiariel.model.Cristal;
import com.example.apiariel.model.HistoricoGanhadoresPremiosCristais;
import com.example.apiariel.model.HistoricoGanhadoresPremiosOfensiva;
import com.example.apiariel.model.PremiosCristal;
import com.example.apiariel.model.PremiosOfensiva;
import com.example.apiariel.service.AdministradorService;
import com.example.apiariel.service.CristalService;
import com.example.apiariel.service.HistoricoGanhadoresPremiosCristaisService;
import com.example.apiariel.service.HistoricoGanhadoresPremiosOfensivaService;
import com.example.apiariel.service.PremiosCristalService;
import com.example.apiariel.service.PremiosOfensivaService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author arielmota
 */
@RestController
@RequestMapping(value = "/administrador")
public class AdministradorController {

    @Autowired
    AdministradorService admnistradorService;

    @Autowired
    PremiosCristalService premiosCristalService;

    @Autowired
    PremiosOfensivaService premiosOfensivaService;

    @Autowired
    HistoricoGanhadoresPremiosCristaisService historicoGanhadoresPremiosCristaisService;

    @Autowired
    HistoricoGanhadoresPremiosOfensivaService historicoGanhadoresPremiosOfensivaService;

    @Autowired
    CristalService cristalService;

    @RequestMapping(method = RequestMethod.POST, value = "/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity autenticar(@RequestBody Administrador adm) {

        Administrador admAuth = admnistradorService.autenticarAdministrador(adm);

        if (admAuth == null || admAuth.getLogin().equals("") || admAuth.getSenha().equals("")) {
            return new ResponseEntity<>(admAuth, HttpStatus.FORBIDDEN);
        }

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(admAuth.getLogin());
        jwtBuilder.claim("adm", true);
        jwtBuilder.signWith(Autenticacao.key);

        String token = jwtBuilder.compact();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return new ResponseEntity<>(headers, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/todosPremiosCristal", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Cliente>> buscaTodosPremiosCristais() {

        List<PremiosCristal> premiosCristals;
        System.out.println("kkkkkkkkkkkkkkkkk");

        try {

            premiosCristals = premiosCristalService.buscaTodos();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(premiosCristals, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/todosPremiosOfensiva", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Cliente>> buscaTodosPremiosOfensiva() {

        List<PremiosOfensiva> premiosOfensivas;
        try {
            premiosOfensivas = premiosOfensivaService.buscaTodos();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(premiosOfensivas, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/editarPremiosCristal/", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity editarPremiosCristal(@RequestBody PremiosCristal premiosCristal) {

        try {

            premiosCristalService.editaPremiosCristal(premiosCristal);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/editarPremiosOfensiva/", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity editarPremiosOfensiva(@RequestBody PremiosOfensiva premiosOfensiva) {

        try {

            premiosOfensivaService.editaPremiosOfensiva(premiosOfensiva);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/HistoricoGanhadoresPremiosCristais", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Cliente>> buscaHistoricoGanhadoresPremiosCristais() {

        List<HistoricoGanhadoresPremiosCristais> listHistoricoPremiosCristals;
        System.out.println("kkkkkkkkkkkkkkkkk");

        try {

            listHistoricoPremiosCristals = historicoGanhadoresPremiosCristaisService.buscarTodos();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(listHistoricoPremiosCristals, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/HistoricoGanhadoresPremiosOfensiva", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Cliente>> buscaHistoricoGanhadoresPremiosOfensiva() {

        List<HistoricoGanhadoresPremiosOfensiva> listHistoricoPremiosOfensiva;
        System.out.println("kkkkkkkkkkkkkkkkk");

        try {

            listHistoricoPremiosOfensiva = historicoGanhadoresPremiosOfensivaService.buscarTodos();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(listHistoricoPremiosOfensiva, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editarGanhadorCristal", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity editarGanhadorCristal(@RequestBody HistoricoGanhadoresPremiosCristais historicoGanhadoresPremiosCristais) {

        System.out.println(historicoGanhadoresPremiosCristais.isPremioResgatado());
        try {

            historicoGanhadoresPremiosCristaisService.editarGanhadorPremioCristal(historicoGanhadoresPremiosCristais);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/editarGanhadorOfensiva", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity editarGanhadorOfensiva(@RequestBody HistoricoGanhadoresPremiosOfensiva historicoGanhadoresPremiosOfensiva) {

        try {

            historicoGanhadoresPremiosOfensivaService.editarGanhadorPremioOfensival(historicoGanhadoresPremiosOfensiva);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "admin/editarValorDoCristal", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cristal> editarValorDoCristal(@RequestBody Cristal cristal) {

        Cristal c;

        try {

            c = cristalService.editarValorCristal(cristal);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(c, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/buscarValorDoCristal", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Cristal>> buscarValorDoCristal() {

        List<Cristal> cristals;

        try {

            cristals = cristalService.buscarValorCristal();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        
        return new ResponseEntity(cristals, HttpStatus.OK);

    }
}
