package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Condicao;

/**
 * @author Henrique
 *
 */
public interface ICondicaoRepository extends JpaRepository<Condicao, Long>
{
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public Condicao findByCodigo(String codigo);
	
	/**
	 * 
	 * @param descricao
	 * @return
	 */
	@Query("SELECT new Condicao(condicao.id, condicao.descricao, condicao.codigo) " 
			+ "FROM Condicao condicao " 
			+ "WHERE FILTER(condicao.descricao, :descricao) = TRUE")
	public List<Condicao> listByFilters( @Param("descricao" ) String descricao);

}
