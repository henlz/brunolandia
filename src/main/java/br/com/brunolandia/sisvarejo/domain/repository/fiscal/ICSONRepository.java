package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.CSON;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Henrique
 */
public interface ICSONRepository extends CrudRepository<CSON, Long> {

    /**
     * @param descricao
     * @param Porcentagem
     * @return
     */
    @Query("SELECT new CSON( cson.id, cson.descricao ) "
            + "FROM CSON cson "
            + "WHERE FILTER(cson.id, :id) = TRUE "
            + "AND FILTER(cson.descricao, :descricao) = TRUE ")
    public List<CSON> listByFilters(@Param("id") Long id, @Param("descricao") String descricao);

}
