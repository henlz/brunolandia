package br.com.brunolandia.sisvarejo.domain.repository.caracteristicas;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Cor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICorRepository extends CrudRepository<Cor, Long> {

    /**
     * @param codigo
     * @return
     */
    public Cor findByCodigo(String codigo);

    /**
     * @param codigo
     * @param cor
     * @return
     */
    @Query("SELECT new Cor(cor.id, cor.nome, cor.codigo) "
            + "FROM Cor cor "
            + "WHERE FILTER(cor.nome, :cor) = TRUE "
            + "AND FILTER(cor.codigo, :codigo) = TRUE ")
    public List<Cor> listByFilters(@Param("codigo") String codigo, @Param("cor") String cor);

}
