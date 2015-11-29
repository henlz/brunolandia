package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.NCM;

/**
 * @author Henrique
 *
 */
public interface INCMRepository extends JpaRepository<NCM, Long>
{

	/**
	 * 
	 * @param codigo
	 * @param descricao
	 * @param Porcentagem
	 * @return
	 */
	@Query("SELECT new NCM( ncm.id, ncm.codigo, ncm.descricao, ncm.possuiIpi ) "
			+ "FROM NCM ncm "
				+ "WHERE FILTER(ncm.codigo, :codigo) = TRUE "
					+ "AND FILTER(ncm.descricao, :descricao) = TRUE "
					+ "AND FILTER(ncm.possuiIpi, :possuiIpi) = TRUE")
	public List<NCM> listByFilters(@Param("codigo") String codigo, @Param("descricao") String descricao, @Param("possuiIpi") Boolean possuiIpi);
	
}
