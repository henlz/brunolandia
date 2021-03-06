package br.com.brunolandia.sisvarejo.domain.repository.estoque;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Henrique
 */
public interface IProdutoRepository extends CrudRepository<Produto, Long> {

    /**
     * @param descricao
     * @param pageable
     * @return
     */
    @Query("SELECT new Produto (produto.id, produto.descricao, produto.codigoBarra, "
            + "produto.precoVenda, produto.quantidade, produto.pesoLiquido, produto.pesoBruto, produto.cor, "
            + "produto.tamanho, produto.genero, produto.fornecedor, produto.icms, produto.ncm, produto.unidade) "
            + "FROM Produto produto "
            + "WHERE ( FILTER(produto.id, :id) = TRUE " + "AND FILTER(produto.descricao, :descricao) = TRUE )")
    public Page<Produto> listByFilters(@Param("id") Long id, @Param("descricao") String descricao, Pageable pageable);
}
