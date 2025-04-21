package br.com.vendas.vendamais.dto;


public class ProdutoDTO {
    private Long id;
    private String nome;
    private int quantidade;

    public ProdutoDTO() {}

    public ProdutoDTO( String nome, int quantidade) {
        
        this.nome = nome;
        this.quantidade = quantidade;
    }


    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}