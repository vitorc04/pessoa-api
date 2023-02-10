package br.com.webapi.pessoaapi;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_endereco;

    @Column(name = "princiapl", nullable = false)
    private boolean principal;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "numero", nullable = false)
    private Long numero;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa", foreignKey = @ForeignKey(name = "fk_endereco_usuario"))
    @JsonBackReference
    private Pessoa pessoa;

    public Long getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(Long id_endereco) {
        this.id_endereco = id_endereco;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
