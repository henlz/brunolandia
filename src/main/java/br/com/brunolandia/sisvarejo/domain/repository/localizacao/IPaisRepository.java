package br.com.brunolandia.sisvarejo.domain.repository.localizacao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Pais;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Henrique
 *
 */
public interface IPaisRepository extends CrudRepository<Pais, Long>
{

	/**
	 * 
	 * @param filter
	 * @return
	 */
//	@Query("SELECT new Pais(pais.id, pais.nome) "
//			+ "FROM Pais pais "
//			+ "WHERE FILTER(pais.nome, :filter)")
//	@Query(value="from Pais where FILTER(pais.nome, :filter)")
//	public List<Pais> listByFilter(@Param("filter") String filter);
	
	/**
	 * 
	 * @param nome
	 * @return
	 */
	public List<Pais> findByNome(String nome);
	
}
