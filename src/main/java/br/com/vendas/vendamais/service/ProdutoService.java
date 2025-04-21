package br.com.vendas.vendamais.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vendas.vendamais.dto.MarcaProdutoDTO;
import br.com.vendas.vendamais.dto.ProdutoDTO;
import br.com.vendas.vendamais.entity.MarcaProduto;
import br.com.vendas.vendamais.entity.Produto;
import br.com.vendas.vendamais.repository.MarcaProdutoRepository;
import br.com.vendas.vendamais.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private MarcaProdutoRepository marcaProdutoRepository;

    public List<Produto> listaProdutos() {
        return produtoRepository.findAll();
    }

    public MarcaProduto salvarMarcaProduto(MarcaProdutoDTO dto){
        MarcaProduto marcaProduto = new MarcaProduto();
        marcaProduto.setNome(dto.getNome());

        marcaProduto = marcaProdutoRepository.save(marcaProduto);

        List<Produto> produtos = new ArrayList<>();
        for (ProdutoDTO produtoDTO : dto.getProdutos()) {
            Produto produto = new Produto();
            produto.setNome(produtoDTO.getNome());
            produto.setQuantidade(produtoDTO.getQuantidade());
            produto.setMarca(marcaProduto); // Associa a marca ao produto
            produtos.add(produto);
        }

        produtoRepository.saveAll(produtos);
        marcaProduto.setProdutos(produtos);
        return marcaProduto;
    }

    public Produto buscaProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto inserirProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> editarProduto(Long id, Produto produtoAlterado){
        Produto produtoAtual = (Produto) buscaProdutoPorId(id);
        produtoAtual.setNome(produtoAlterado.getNome());
        produtoAtual.setQuantidade(produtoAlterado.getQuantidade());

        return (List<Produto>) produtoRepository.save(produtoAtual);
    }

    public Boolean deletarProduto(Long id) {
        Produto produto = (Produto) buscaProdutoPorId(id);
        if (produto == null) {
            return false;
        }else {
            produtoRepository.delete(produto);
        }
        return true;
    }

}
