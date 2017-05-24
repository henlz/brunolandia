/**
 *
 */
package br.com.brunolandia.sisvarejo.domain.repository.caracteristicas;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Genero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Henrique
 */
public interface IGeneroRepository extends CrudRepository<Genero, Long> {

    /**
     * @param genero
     * @return
     */
    @Query("SELECT new Genero( genero.id, genero.genero) "
            + "FROM Genero genero "
            + "WHERE FILTER(genero.genero, :genero) = TRUE "
            + "AND FILTER(genero.id, :id) = TRUE ")
    public List<Genero> listByFilters(@Param("genero") String genero, @Param("id") Long id);

}
