package br.com.webapi.pessoaapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_pessoa;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dataNascimento", nullable = false)
    @JsonFormat(pattern="dd/mm/yyyy")
    private Date dataNascimento;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa", foreignKey = @ForeignKey(name = "fk_endereco_pessoa"))
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();

    public Long getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(Long id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }
}
