package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaPagar;

/**
 * @author Henrique
 *
 */
public interface IContaPagarRepository extends JpaRepository<ContaPagar, Long>
{

}
