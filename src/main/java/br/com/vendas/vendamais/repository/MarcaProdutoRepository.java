package br.com.vendas.vendamais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vendas.vendamais.entity.MarcaProduto;

public interface MarcaProdutoRepository extends JpaRepository<MarcaProduto, Long>{
    @EntityGraph(attributePaths = {"produtos"})
    @Override
    List<MarcaProduto> findAll();
}
