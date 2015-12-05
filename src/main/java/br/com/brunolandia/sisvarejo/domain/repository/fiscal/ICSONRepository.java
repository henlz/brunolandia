package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.CSON;

/**
 * @author Henrique
 *
 */
public interface ICSONRepository extends JpaRepository<CSON, Long>
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
