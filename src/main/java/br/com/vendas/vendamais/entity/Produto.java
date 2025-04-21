package br.com.vendas.vendamais.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    @JsonBackReference
    private MarcaProduto marca;

    public void setNome(String nome) {
        this.nome = nome;   
    }

    public void setMarca(MarcaProduto marcaProduto) {
        this.marca = marcaProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getId() {
        return id;
    }
}