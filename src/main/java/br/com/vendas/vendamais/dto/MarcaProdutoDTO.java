package br.com.vendas.vendamais.dto;

import java.util.List;
public class MarcaProdutoDTO {
    private Long id;
    private String nome;
    private List<ProdutoDTO> produtos;

    public MarcaProdutoDTO() {}

    public MarcaProdutoDTO(String nome, List<ProdutoDTO> produtos) {
        this.nome = nome;
        this.produtos = produtos;
    }

    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public List<ProdutoDTO> getProdutos() { return produtos; }
    public void setProdutos(List<ProdutoDTO> produtos) { this.produtos = produtos; }
}