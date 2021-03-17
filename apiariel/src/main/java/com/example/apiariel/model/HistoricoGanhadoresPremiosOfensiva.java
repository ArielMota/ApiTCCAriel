/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.apiariel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author arielmota
 */
@Entity
public class HistoricoGanhadoresPremiosOfensiva {

    private Long id;
    private String data;
    private Cliente cliente;
    private String title;
    private String descricao;
    private int posicao;
    private String urlImagem;
    private boolean premioResgatado;
    private Long valorTotalOfensiva;

    public HistoricoGanhadoresPremiosOfensiva() {
    }
    
    

    public HistoricoGanhadoresPremiosOfensiva(String data, Cliente cliente, String title, String descricao, int posicao, String urlImagem, boolean premioResgatado, Long valorTotalOfensiva) {
        this.data = data;
        this.cliente = cliente;
        this.title = title;
        this.descricao = descricao;
        this.posicao = posicao;
        this.urlImagem = urlImagem;
        this.premioResgatado = premioResgatado;
        this.valorTotalOfensiva = valorTotalOfensiva;
    }
    
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @ManyToOne
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public boolean isPremioResgatado() {
        return premioResgatado;
    }

    public void setPremioResgatado(boolean premioResgatado) {
        this.premioResgatado = premioResgatado;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public Long getValorTotalOfensiva() {
        return valorTotalOfensiva;
    }

    public void setValorTotalOfensiva(Long valorTotalOfensiva) {
        this.valorTotalOfensiva = valorTotalOfensiva;
    }
    
    


}
