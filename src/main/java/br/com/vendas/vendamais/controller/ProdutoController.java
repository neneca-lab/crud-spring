package br.com.vendas.vendamais.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import br.com.vendas.vendamais.dto.MarcaProdutoDTO;
import br.com.vendas.vendamais.dto.ProdutoDTO;
import br.com.vendas.vendamais.entity.MarcaProduto;
import br.com.vendas.vendamais.entity.Produto;
import br.com.vendas.vendamais.repository.MarcaProdutoRepository;
import br.com.vendas.vendamais.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private MarcaProdutoRepository marcaProdutoRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listaProdutos() {
        List<Produto> produtos = produtoService.listaProdutos();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaProdutoPorId(@PathVariable Long id) {
        Produto produto = (Produto) produtoService.buscaProdutoPorId(id);
        if(produto == null){
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/marca/{id}")
    public ResponseEntity<MarcaProdutoDTO> buscarMarca(@PathVariable Long id) {
        MarcaProduto marca = marcaProdutoRepository.findById(id).orElse(null);
        if(marca == null) {
            return ResponseEntity.notFound().build();
        }
        
        List<ProdutoDTO> produtosDTO = marca.getProdutos().stream()
            .map(p -> new ProdutoDTO(p.getNome(), p.getQuantidade()))
            .collect(Collectors.toList());
            
        MarcaProdutoDTO response = new MarcaProdutoDTO( marca.getNome(), produtosDTO);
        return ResponseEntity.ok(response);
    }

     @GetMapping("/listar-por-marca")
    public ResponseEntity<List<MarcaProdutoDTO>> listarMarcasComProdutos() {
        List<MarcaProduto> marcas = marcaProdutoRepository.findAll();
        
        List<MarcaProdutoDTO> response = marcas.stream()
            .map(marca -> {
                List<ProdutoDTO> produtosDTO = marca.getProdutos().stream()
                    .map(p -> new ProdutoDTO( p.getNome(), p.getQuantidade()))
                    .collect(Collectors.toList());
                return new MarcaProdutoDTO( marca.getNome(), produtosDTO);
            })
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }


    @PostMapping("/inserir")
    public ResponseEntity<Produto> inserirProduto(@RequestBody Produto produto){
        Produto produtoInserido = produtoService.inserirProduto(produto);
        return ResponseEntity.ok().body(produtoInserido);
    }

    @PostMapping("/marcaProduto")
    public MarcaProduto criarMarcaProduto(@RequestBody MarcaProdutoDTO marcaProdutoDTO) {
        return produtoService.salvarMarcaProduto(marcaProdutoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> editarProduto(@PathVariable Long id, @RequestBody Produto produtoAlterado){
        Produto produtoAtualizado = (Produto) produtoService.editarProduto(id, produtoAlterado);
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
