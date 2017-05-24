package br.com.brunolandia.sisvarejo.domain.repository.localizacao;

import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Cidade;
import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Estado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Henrique
 */
public interface ICidadeRepository extends CrudRepository<Cidade, Long> {

    /**
     * @return
     */
    public List<Cidade> findByEstado(Estado estado);

    /**
     * @param nome
     * @return
     */
    public List<Cidade> findByNome(String nome);

    /**
     * @param filters
     * @return
     */
    @Query("SELECT new Cidade(cidade.id, cidade.nome, cidade.estado) "
            + "FROM Cidade cidade "
            + "WHERE FILTER(cidade.nome, :filters) = TRUE "
            + "AND FILTER(cidade.estado.id, :estadoId) = TRUE "
            + "AND FILTER(cidade.id, :id) = TRUE ")
    public List<Cidade> listByFilters(@Param("filters") String filters, @Param("estadoId") Long estadoId, @Param("id") Long id);

}
