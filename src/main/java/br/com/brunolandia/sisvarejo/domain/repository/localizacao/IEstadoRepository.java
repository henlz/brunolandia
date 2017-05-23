package br.com.brunolandia.sisvarejo.domain.repository.localizacao;

import java.util.List;

import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Estado;
import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Pais;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Henrique
 */
public interface IEstadoRepository extends CrudRepository<Estado, Long> {

    public List<Estado> findByPais(Pais pais);

}
