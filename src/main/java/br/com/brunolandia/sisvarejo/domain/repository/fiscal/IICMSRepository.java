package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.ICMS;

/**
 * @author Henrique
 *
 */
public interface IICMSRepository extends JpaRepository<ICMS, Long>
{
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public ICMS findByCodigo(String codigo);

	/**
	 * 
	 * @param codigo
	 * @param descricao
	 * @param Porcentagem
	 * @return
	 */
	@Query("SELECT new ICMS( icms.id, icms.codigo, icms.descricao, icms.porcentagem ) "
			+ "FROM ICMS icms "
				+ "WHERE FILTER(icms.codigo, :codigo) = TRUE "
					+ "AND FILTER(icms.descricao, :descricao) = TRUE "
					+ "AND FILTER(icms.porcentagem, :porcentagem) = TRUE")
	public List<ICMS> listByFilters(@Param("codigo") String codigo, @Param("descricao") String descricao, @Param("porcentagem") BigDecimal porcentagem);

}
