package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.FormaPagamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFormaPagamentoRepository extends CrudRepository<FormaPagamento, Long>
{

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public FormaPagamento findByCodigo(String codigo);
	
	/**
	 * 
	 * @param filters
	 * @return
	 */
	@Query("SELECT new FormaPagamento(formaPagamento.id, formaPagamento.tipo, formaPagamento.codigo) "
			+ "FROM FormaPagamento formaPagamento "
			+ "WHERE FILTER(formaPagamento.tipo, :tipo) = TRUE "
			+ "AND FILTER(formaPagamento.codigo, :codigo) = TRUE")
	public List<FormaPagamento> listByFilters( @Param("codigo") String codigo, @Param("tipo") String tipo );
	
}
