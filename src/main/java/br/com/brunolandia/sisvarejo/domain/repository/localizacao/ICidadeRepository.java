package br.com.brunolandia.sisvarejo.domain.repository.localizacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Cidade;
import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Estado;

/**
 * 
 * @author Henrique
 *
 */

public interface ICidadeRepository extends JpaRepository<Cidade, Long>
{

	/**
	 * 
	 * @return
	 */
	public List<Cidade> findByEstado( Estado estado );

	/**
	 * 
	 * @param nome
	 * @return
	 */
	public List<Cidade> findByNome( String nome );
	
	/**
	 * 
	 * @param nome
	 * @return
	 */
	public Cidade findByCodigo( String codigo );
	
	/**
	 * 
	 * @param filters
	 * @return
	 */
	@Query("SELECT new Cidade(cidade.id, cidade.nome, cidade.estado, cidade.codigo) "
			+ "FROM Cidade cidade "
			+ "WHERE FILTER(cidade.nome, :filters) = TRUE "
			+ "AND FILTER(cidade.estado.id, :estadoId) = TRUE "
			+ "AND FILTER(cidade.codigo, :codigo) = TRUE ")
	public List<Cidade> listByFilters( @Param("filters")String filters, @Param("estadoId")Long estadoId, @Param("codigo") String codigo );

}
