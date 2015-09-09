/**
 * 
 */
package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaReceber;

/**
 * @author Henrique
 *
 */
public interface IContaReceberRepository extends JpaRepository<ContaReceber, Long>
{

}
