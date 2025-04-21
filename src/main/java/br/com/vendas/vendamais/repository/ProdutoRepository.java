package br.com.vendas.vendamais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vendas.vendamais.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
