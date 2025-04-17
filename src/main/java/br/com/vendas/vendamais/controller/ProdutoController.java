package br.com.vendas.vendamais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vendas.vendamais.entity.Produto;
import br.com.vendas.vendamais.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listaProdutos() {
        List<Produto> produtos = produtoService.listaProdutos();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscaProdutoPorId(id);
        if(produto == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(produto);
        }
    }

    @PostMapping("/inserir")
    public ResponseEntity<Produto> inserirProduto(@RequestBody Produto produto){
        Produto produtoInserido = produtoService.inserirProduto(produto);
        return ResponseEntity.ok().body(produtoInserido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> editarProduto(@PathVariable Long id, @RequestBody Produto produtoAlterado){
        Produto produtoAtualizado = produtoService.editarProduto(id, produtoAlterado);
        if(produtoAtualizado == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(produtoAtualizado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        Boolean produtoDeletado = produtoService.deletarProduto(id);
        if(produtoDeletado == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
