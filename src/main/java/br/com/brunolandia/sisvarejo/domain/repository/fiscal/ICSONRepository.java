package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.CSON;
import org.springframework.stereotype.Repository;

/**
 * @author Henrique
 *
 */
public interface ICSONRepository extends CrudRepository<CSON, Long>
{
	
	/**
	 * 
	 * @param codigo
	 * @param descricao
	 * @param Porcentagem
	 * @return
	 */
	@Query("SELECT new CSON( cson.id, cson.codigo, cson.descricao ) "
			+ "FROM CSON cson "
				+ "WHERE FILTER(cson.codigo, :codigo) = TRUE "
					+ "AND FILTER(cson.descricao, :descricao) = TRUE ")
	public List<CSON> listByFilters(@Param("codigo") String codigo, @Param("descricao") String descricao);

}
