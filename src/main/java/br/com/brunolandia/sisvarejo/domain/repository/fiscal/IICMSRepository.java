package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.ICMS;
import org.springframework.stereotype.Repository;

/**
 * @author Henrique
 */
public interface IICMSRepository extends CrudRepository<ICMS, Long> {

    /**
     * @param descricao
     * @param Porcentagem
     * @return
     */
    @Query("SELECT new ICMS( icms.id, icms.descricao, icms.porcentagem ) "
            + "FROM ICMS icms "
            + "WHERE FILTER(icms.id, :id) = TRUE "
            + "AND FILTER(icms.descricao, :descricao) = TRUE "
            + "AND FILTER(icms.porcentagem, :porcentagem) = TRUE")
    public List<ICMS> listByFilters(@Param("id") Long id, @Param("descricao") String descricao, @Param("porcentagem") BigDecimal porcentagem);

}
