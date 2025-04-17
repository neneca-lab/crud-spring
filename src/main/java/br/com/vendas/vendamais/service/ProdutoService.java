package br.com.vendas.vendamais.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vendas.vendamais.entity.Produto;
import br.com.vendas.vendamais.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listaProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscaProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto inserirProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto editarProduto(Long id, Produto produtoAlterado){
        Produto produtoAtual = buscaProdutoPorId(id);
        produtoAtual.setNome(produtoAlterado.getNome());
        produtoAtual.setQuantidade(produtoAlterado.getQuantidade());

        return produtoRepository.save(produtoAtual);
    }

    public Boolean deletarProduto(Long id) {
        Produto produto = buscaProdutoPorId(id);
        if (produto == null) {
            return false;
        }else {
            produtoRepository.delete(produto);
        }
        return true;
    }   

}
