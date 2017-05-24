package br.com.brunolandia.sisvarejo.domain.repository.caracteristicas;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Tamanho;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITamanhoRepository extends CrudRepository<Tamanho, Long> {

    /**
     * @param tamanho
     * @return
     */
    @Query("SELECT new Tamanho(tamanho.id, tamanho.nome, tamanho.sigla) "
            + "FROM Tamanho tamanho "
            + "WHERE FILTER(tamanho.nome, :tamanho) = TRUE "
            + "AND FILTER(tamanho.sigla, :sigla) = TRUE "
            + "AND FILTER(tamanho.id, :id) = TRUE ")
    public List<Tamanho> listByFilters(@Param("id") Long id, @Param("tamanho") String tamanho, @Param("sigla") String sigla);

}
