package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.CSON;

/**
 * @author Henrique
 *
 */
public interface ICSONRepository extends JpaRepository<CSON, Long>
{

}
