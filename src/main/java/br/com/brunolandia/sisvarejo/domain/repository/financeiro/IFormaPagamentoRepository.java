package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.FormaPagamento;

public interface IFormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>
{

	/**
	 * 
	 * @param filters
	 * @return
	 */
	@Query("SELECT new FormaPagamento(formaPagamento.id, formaPagamento.tipo) "
			+ "FROM FormaPagamento formaPagamento "
			+ "WHERE FILTER(formaPagamento.tipo, :filters) = TRUE")
	public List<FormaPagamento> listByFilters(@Param("filters") String filters);
	
}
