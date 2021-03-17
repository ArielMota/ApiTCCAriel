package com.example.apiariel;

import com.example.apiariel.model.Cliente;
import com.example.apiariel.model.Cristal;
import com.example.apiariel.model.PremiosCristal;
import com.example.apiariel.service.ClienteService;
import com.example.apiariel.service.CristalService;
import com.example.apiariel.service.FiltroAdministrador;
import com.example.apiariel.service.FiltroCliente;
import com.example.apiariel.service.PremiosCristalService;
import com.example.apiariel.service.PremiosOfensivaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiarielApplication {

    @Autowired
    PremiosCristalService premiosCristalService;
    @Autowired
    PremiosOfensivaService premiosOfensivaService;
    @Autowired
    CristalService cristalService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        if (premiosCristalService != null && premiosCristalService.buscaTodos().isEmpty()) {
            premiosCristalService.cadastrarTresPosicoesVazias();

        }
        
        if(premiosOfensivaService != null && premiosOfensivaService.buscaTodos().isEmpty()){
           premiosOfensivaService.cadastrarTresPosicoesVazias();

        }
         if(cristalService != null && cristalService.buscarValorCristal().isEmpty()){
           Cristal c = new Cristal();
           c.setValorDoCristal(1.50);
           
           cristalService.editarValorCristal(c);

        }


    }

    @Bean
    public FilterRegistrationBean filtroJwtAdministrador() {
        FilterRegistrationBean fr = new FilterRegistrationBean();
        fr.setFilter(new FiltroAdministrador());
        fr.addUrlPatterns("/admin/*");
        return fr;

    }

    @Bean
    public FilterRegistrationBean filtroJwtCliente() {
        FilterRegistrationBean fr = new FilterRegistrationBean();
        fr.setFilter(new FiltroCliente());
        fr.addUrlPatterns("/auth/*");
        return fr;
    }

    public static void main(String[] args) {

        SpringApplication.run(ApiarielApplication.class, args);

    }

}
