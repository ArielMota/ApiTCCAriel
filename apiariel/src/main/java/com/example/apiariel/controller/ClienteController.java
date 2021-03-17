/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.controller;

import com.example.apiariel.config.Autenticacao;
import com.example.apiariel.model.Cliente;
import com.example.apiariel.model.PontosOfensiva;
import com.example.apiariel.service.ClienteService;
import com.example.apiariel.service.PontosOfensivaService;
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
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    PontosOfensivaService pontosOfensivaService;

    @RequestMapping(method = RequestMethod.POST, value = "/cliente", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarCliente(@RequestBody Cliente cli) {

        clienteService.cadastrarCliente(cli);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/cliente/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity autenticar(@RequestBody Cliente cli) {

        Cliente cliAuth = clienteService.autenticarCliente(cli);

        if (cliAuth == null || cliAuth.getLogin().equals("") || cliAuth.getSenha().equals("")) {
            return new ResponseEntity<>(cliAuth, HttpStatus.FORBIDDEN);
        }

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(cliAuth.getNome());
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        jwtBuilder.signWith(Autenticacao.key);

        String token = jwtBuilder.compact();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("idClienteAuth", cliAuth.getId() + "");

        return new ResponseEntity<>(headers, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/auth/cliente/{id}")
    ResponseEntity removerCliente(@PathVariable Long id) {

        clienteService.excluirCliente(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/auth/cliente", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity editarCliente(@RequestBody Cliente cli) {
        clienteService.editarCliente(cli);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/auth/cliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> mostraCliente(@PathVariable Long id) {

        Cliente cli;
        try {
            cli = clienteService.buscaClienteId(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(cli, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/buscar_cliente/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> buscaClientesPorNome(@PathVariable String nome) {

        List<Cliente> listCli;
        try {
            listCli = clienteService.buscaClientesPorNome(nome);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(listCli, HttpStatus.OK);
    }
    
     @RequestMapping(method = RequestMethod.GET, value = "/sugestao_login_cliente/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> buscarSugestaoLoginClientes(@PathVariable String login) {

        List<Cliente> listCliente;
        try {
            listCliente = clienteService.buscarSugestaoLoginClientes(login);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(listCliente, HttpStatus.OK);
    }

    //busca todos clientes ranqueados por maior pontuação de cristais
    @RequestMapping(method = RequestMethod.GET, value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Cliente>> mostraTodosClientess() {

        List<Cliente> cli;
        try {
            cli = clienteService.buscaTodosOrdenadoPorMaiorPontuacao();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(cli, HttpStatus.OK);
    }

    //busca todos clientes ranqueados por maior pontuação de ofensiva
    @RequestMapping(method = RequestMethod.GET, value = "/clienteOfensiva", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Cliente>> mostraTodosClientesPorOfensiva() {

        List<Cliente> cli;
        try {
            cli = clienteService.buscaTodosOrdenadoPorMaiorOfensiva();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(cli, HttpStatus.OK);
    }

    //verifica se palavra chaves estão corretas e usa o httpStatus para informar se poderá recuperar a senha 
    @RequestMapping(method = RequestMethod.POST, value = "/cliente/recuperar_senha", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity recuperarSenha(@RequestBody Cliente cli) {




        if (clienteService.recuperarSenha(cli) == true) {
            
            Cliente cliente = clienteService.buscaClienteLogin(cli.getLogin());
            cliente.setSenha(cli.getSenha());

           clienteService.editarCliente(cliente);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }

    }
    
     //verifica se palavra chaves estão corretas e usa o httpStatus para informar se poderá recuperar a senha 
    @RequestMapping(method = RequestMethod.POST, value = "/cliente/posicao_ranking_cristais", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buscaPosicaoRankingClienteCristais(@RequestBody Cliente cli) {
        
        int valor = clienteService.buscaPosicaoRankingClienteCristais(cli);
        System.out.println(valor);

        
            return new ResponseEntity<>(valor,HttpStatus.OK);
        

        

    }

    @RequestMapping(method = RequestMethod.GET, value = "/cliente_existe/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> ClienteExiste(@PathVariable String login) {

        Cliente cli;
        try {
            cli = clienteService.buscaClienteLogin(login);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (cli != null) {
            return new ResponseEntity(cli, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/verificar_ofensiva_diaria_concluida/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> VerificarOfensivaDiariaConcluida(@PathVariable String login) {

        Cliente cli;
        try {
            cli = clienteService.buscaClienteLogin(login);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (cli != null) {
            return new ResponseEntity(cli.getOfensiva_diaria_concluida(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/concluir_ofensiva_diaria_concluida/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> concluirOfensivaDiariaConcluida(@PathVariable String login) {

        Cliente cli;
        try {
             clienteService.concluirOfensivaDiariaConcluida(login);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

            return new ResponseEntity(HttpStatus.OK);
       
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cancelar_ofensiva_diaria_concluida/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> cancelarOfensivaDiariaConcluida(@PathVariable String login) {

        Cliente cli;
        try {
             clienteService.cancelarOfensivaDiariaConcluida(login);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

            return new ResponseEntity(HttpStatus.OK);
       
    }
    
     @RequestMapping(method = RequestMethod.GET, value = "/adcionar_um_ponto_ofensiva_cliente/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> adcionarUmPontoOfensivaCliente(@PathVariable String login) {

        Cliente cli;
        try {
             clienteService.adcionarUmPontoOfensivaCliente(login);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

            return new ResponseEntity(HttpStatus.OK);
       
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/remover_um_ponto_ofensiva_cliente/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> removerUmPontoOfensivaCliente(@PathVariable String login) {

        Cliente cli;
        try {
             clienteService.removerUmPontoOfensivaCliente(login);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

            return new ResponseEntity(HttpStatus.OK);
       
    }
    
    

}
