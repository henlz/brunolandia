package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaPagar;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Henrique
 *
 */
public interface IContaPagarRepository extends CrudRepository<ContaPagar, Long>
{

}
