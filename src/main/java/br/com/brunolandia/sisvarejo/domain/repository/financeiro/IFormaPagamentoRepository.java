package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.FormaPagamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFormaPagamentoRepository extends CrudRepository<FormaPagamento, Long> {

    /**
     * @param filters
     * @return
     */
    @Query("SELECT new FormaPagamento(formaPagamento.id, formaPagamento.tipo) "
            + "FROM FormaPagamento formaPagamento "
            + "WHERE FILTER(formaPagamento.tipo, :tipo) = TRUE "
            + "AND FILTER(formaPagamento.id, :id) = TRUE")
    public List<FormaPagamento> listByFilters(@Param("id") Long id, @Param("tipo") String tipo);

}
