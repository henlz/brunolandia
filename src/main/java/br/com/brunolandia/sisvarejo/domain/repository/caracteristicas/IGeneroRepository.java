/**
 * 
 */
package br.com.brunolandia.sisvarejo.domain.repository.caracteristicas;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Genero;
import org.springframework.stereotype.Repository;

/**
 * @author Henrique
 *
 */
public interface IGeneroRepository extends CrudRepository<Genero, Long>
{

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public Genero findByCodigo(String codigo);
	
	/**
	 * 
	 * @param genero
	 * @param codigo
	 * @return
	 */
	@Query("SELECT new Genero( genero.id, genero.genero, genero.codigo) "
			+ "FROM Genero genero "
			+ "WHERE FILTER(genero.genero, :genero) = TRUE "
			+ "AND FILTER(genero.codigo, :codigo) = TRUE ")
	public List<Genero> listByFilters( @Param("genero" ) String genero, @Param("codigo" ) String codigo);

}
