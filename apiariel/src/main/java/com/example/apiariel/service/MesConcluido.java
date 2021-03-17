/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.service;

import com.example.apiariel.model.Cliente;
import com.example.apiariel.model.HistoricoGanhadoresPremiosCristais;
import com.example.apiariel.model.HistoricoGanhadoresPremiosOfensiva;
import com.example.apiariel.model.PontosCristal;
import com.example.apiariel.model.PremiosCristal;
import com.example.apiariel.model.PremiosOfensiva;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author arielmota
 */
@Component
public class MesConcluido {

    @Autowired
    PontosCristalService pontosCristalService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    PontosOfensivaService pontosOfensivaService;

    @Autowired
    HistoricoGanhadoresPremiosCristaisService historicoGanhadoresPremiosCristaisService;

    @Autowired
    HistoricoGanhadoresPremiosOfensivaService historicoGanhadoresPremiosOfensivaService;

    @Autowired
    PremiosCristalService premiosCristalService;

    @Autowired
    PremiosOfensivaService premiosOfensivaService;

    @Scheduled(cron = "01 00 00 01 * *")
    public void inicioDeNovoMes() {

        //E deverá ser salvo os ganhadores e seus respectivos premios do mês terminado
        salvarTresPrimeirosColocadosRankingCristais();
        salvarTresPrimeirosColocadosRankingOfensiva();

        zerarCristaisEofensivasInicioDeMes();

    }

    public void salvarTresPrimeirosColocadosRankingCristais() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMMMMM/yyyy");
        sdf1.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Sao_Paulo")));
        Date y = new Date();
        y.setMonth(y.getMonth() - 1);
        String dataValor = sdf1.format(y);

        List<PremiosCristal> listPremioCristal;
        List<Cliente> listCliente;

        if (historicoGanhadoresPremiosCristaisService != null) {

            listPremioCristal = premiosCristalService.buscaTodos();
            listCliente = clienteService.buscaTodosOrdenadoPorMaiorPontuacao();

            HistoricoGanhadoresPremiosCristais ganhadorUm = new HistoricoGanhadoresPremiosCristais(dataValor,
                    listCliente.get(0),
                    listPremioCristal.get(0).getTitle(),
                    listPremioCristal.get(0).getDescricao(),
                    1,
                    listPremioCristal.get(0).getUrlImagem(),
                    false,
                    pontosCristalService.valorTotalPontosCristalCliente(listCliente.get(0).getId())
            );

            HistoricoGanhadoresPremiosCristais ganhadorDois = new HistoricoGanhadoresPremiosCristais(dataValor,
                    listCliente.get(1),
                    listPremioCristal.get(1).getTitle(),
                    listPremioCristal.get(1).getDescricao(),
                    2,
                    listPremioCristal.get(1).getUrlImagem(),
                    false,
                    pontosCristalService.valorTotalPontosCristalCliente(listCliente.get(1).getId())
            );

            HistoricoGanhadoresPremiosCristais ganhadorTres = new HistoricoGanhadoresPremiosCristais(dataValor,
                    listCliente.get(2),
                    listPremioCristal.get(2).getTitle(),
                    listPremioCristal.get(2).getDescricao(),
                    3,
                    listPremioCristal.get(2).getUrlImagem(),
                    false,
                    pontosCristalService.valorTotalPontosCristalCliente(listCliente.get(2).getId())
            );

            historicoGanhadoresPremiosCristaisService.cadastrarGanhadorPremioCristal(ganhadorUm);
            historicoGanhadoresPremiosCristaisService.cadastrarGanhadorPremioCristal(ganhadorDois);
            historicoGanhadoresPremiosCristaisService.cadastrarGanhadorPremioCristal(ganhadorTres);

        } else {

            System.out.println("headHunterService Object is null");
        }
    }

    public void salvarTresPrimeirosColocadosRankingOfensiva() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMMMMM/yyyy");
        sdf1.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Sao_Paulo")));
        Date y = new Date();
        y.setMonth(y.getMonth() - 1);
        String dataValor = sdf1.format(y);

        List<PremiosOfensiva> listPremioOfensiva;
        List<Cliente> listCliente;

        if (historicoGanhadoresPremiosCristaisService != null) {

            listPremioOfensiva = premiosOfensivaService.buscaTodos();
            listCliente = clienteService.buscaTodosOrdenadoPorMaiorOfensiva();

            HistoricoGanhadoresPremiosOfensiva ganhadorUm = new HistoricoGanhadoresPremiosOfensiva(dataValor,
                    listCliente.get(0),
                    listPremioOfensiva.get(0).getTitle(),
                    listPremioOfensiva.get(0).getDescricao(),
                    1,
                    listPremioOfensiva.get(0).getUrlImagem(),
                    false,
                    clienteService.buscarTotalPontosOfensivaCliente(listCliente.get(0).getLogin())
            );

            HistoricoGanhadoresPremiosOfensiva ganhadorDois = new HistoricoGanhadoresPremiosOfensiva(dataValor,
                    listCliente.get(1),
                    listPremioOfensiva.get(1).getTitle(),
                    listPremioOfensiva.get(1).getDescricao(),
                    2,
                    listPremioOfensiva.get(1).getUrlImagem(),
                    false,
                    clienteService.buscarTotalPontosOfensivaCliente(listCliente.get(1).getLogin())
            );

            HistoricoGanhadoresPremiosOfensiva ganhadorTres = new HistoricoGanhadoresPremiosOfensiva(dataValor,
                    listCliente.get(2),
                    listPremioOfensiva.get(2).getTitle(),
                    listPremioOfensiva.get(2).getDescricao(),
                    3,
                    listPremioOfensiva.get(2).getUrlImagem(),
                    false,
                    clienteService.buscarTotalPontosOfensivaCliente(listCliente.get(2).getLogin())
            );

            historicoGanhadoresPremiosOfensivaService.cadastrarGanhadorPremioOfensiva(ganhadorUm);
            historicoGanhadoresPremiosOfensivaService.cadastrarGanhadorPremioOfensiva(ganhadorDois);
            historicoGanhadoresPremiosOfensivaService.cadastrarGanhadorPremioOfensiva(ganhadorTres);

        } else {

            System.out.println("headHunterService Object is null");
        }
    }

    public void zerarCristaisEofensivasInicioDeMes() {
        //Será zerada todas as pontuações no primeiro dia de todo mês
        if (pontosCristalService != null) {
            pontosCristalService.deletarTodosPontosCristais();

        } else {

            System.out.println("headHunterService Object is null");
        }
        if (clienteService != null) {
            clienteService.zerarOfensivaDiariaConcluida();

        } else {

            System.out.println("headHunterService Object is null");
        }

        if (pontosOfensivaService != null) {
            pontosOfensivaService.zerarTodosPontosOfensiva();

        } else {

            System.out.println("headHunterService Object is null");
        }
    }

}
