package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.NCM;
import org.springframework.stereotype.Repository;

/**
 * @author Henrique
 *
 */
public interface INCMRepository extends CrudRepository<NCM, Long>
{

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public NCM findByCodigo(String codigo);
	
	/**
	 * 
	 * @param codigo
	 * @param descricao
	 * @param Porcentagem
	 * @return
	 */
	@Query("SELECT new NCM( ncm.id, ncm.codigo, ncm.descricao, ncm.IPI ) "
			+ "FROM NCM ncm "
				+ "WHERE FILTER(ncm.codigo, :codigo) = TRUE "
					+ "AND FILTER(ncm.descricao, :descricao) = TRUE "
					+ "AND FILTER(ncm.IPI, :IPI) = TRUE")
	public List<NCM> listByFilters(@Param("codigo") String codigo, @Param("descricao") String descricao, @Param("IPI") BigDecimal IPI);
	
}
