package br.com.brunolandia.sisvarejo.domain.repository.localizacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Pais;

/**
 * 
 * @author Henrique
 *
 */

public interface IPaisRepository extends JpaRepository<Pais, Long>
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
