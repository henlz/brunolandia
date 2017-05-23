package br.com.brunolandia.sisvarejo.domain.repository.caracteristicas;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Tamanho;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ITamanhoRepository extends CrudRepository<Tamanho, Long> {

    /**
     * @param codigo
     * @return
     */
    public Tamanho findByCodigo(String codigo);

    /**
     * @param codigo
     * @param tamanho
     * @return
     */
    @Query("SELECT new Tamanho(tamanho.id, tamanho.nome, tamanho.sigla, tamanho.codigo) "
            + "FROM Tamanho tamanho "
            + "WHERE FILTER(tamanho.nome, :tamanho) = TRUE "
            + "AND FILTER(tamanho.sigla, :sigla) = TRUE "
            + "AND FILTER(tamanho.codigo, :codigo) = TRUE ")
    public List<Tamanho> listByFilters(@Param("codigo") String codigo, @Param("tamanho") String tamanho, @Param("sigla") String sigla);

}
