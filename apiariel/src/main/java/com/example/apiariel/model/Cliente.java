package com.example.apiariel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

/**
 *
 * @author arielmota
 */
@Entity
public class Cliente {

    private Long id;
    private Imagem imagem;
    private String nome;
    private String senha;
    private String login;
    private String telefone;
    private String cpf;
    private String sexo;
    private Endereco endereco;
    private List<PontosCristal> pontocristal;
    private PontosOfensiva pontosOfensiva;
    private String nome_do_melhor_amigo_de_infancia;
    private String genero_de_filme_preferido;
    private String nome_do_primeiro_cachorro;
    private Boolean ofensiva_diaria_concluida;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "imagem_id")
    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Column(unique = true)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public List<PontosCristal> getPontocristal() {
        return pontocristal;
    }

    public void setPontocristal(List<PontosCristal> pontocristal) {
        this.pontocristal = pontocristal;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ponto_ofensiva")
    public PontosOfensiva getPontosOfensiva() {
        return pontosOfensiva;
    }

    public void setPontosOfensiva(PontosOfensiva pontosOfensiva) {
        this.pontosOfensiva = pontosOfensiva;
    }

    public String getNome_do_melhor_amigo_de_infancia() {
        return nome_do_melhor_amigo_de_infancia;
    }

    public void setNome_do_melhor_amigo_de_infancia(String nome_do_melhor_amigo_de_infancia) {
        this.nome_do_melhor_amigo_de_infancia = nome_do_melhor_amigo_de_infancia;
    }

    public String getGenero_de_filme_preferido() {
        return genero_de_filme_preferido;
    }

    public void setGenero_de_filme_preferido(String genero_de_filme_preferido) {
        this.genero_de_filme_preferido = genero_de_filme_preferido;
    }

    public String getNome_do_primeiro_cachorro() {
        return nome_do_primeiro_cachorro;
    }

    public void setNome_do_primeiro_cachorro(String nome_do_primeiro_cachorro) {
        this.nome_do_primeiro_cachorro = nome_do_primeiro_cachorro;
    }

    public Boolean getOfensiva_diaria_concluida() {
        return ofensiva_diaria_concluida;
    }

    public void setOfensiva_diaria_concluida(Boolean ofensiva_diaria_concluida) {
        this.ofensiva_diaria_concluida = ofensiva_diaria_concluida;
    }

}
