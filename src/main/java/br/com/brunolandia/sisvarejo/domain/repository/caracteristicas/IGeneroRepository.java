/**
 * 
 */
package br.com.brunolandia.sisvarejo.domain.repository.caracteristicas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Genero;

/**
 * @author Henrique
 *
 */
public interface IGeneroRepository extends JpaRepository<Genero, Long>
{

	@Query("SELECT new Genero( genero.id, genero.genero) "
			+ "FROM Genero genero "
			+ "WHERE FILTER(genero.genero, :genero) = TRUE")
	public List<Genero> listByFilters( @Param("genero" ) String genero);

}
