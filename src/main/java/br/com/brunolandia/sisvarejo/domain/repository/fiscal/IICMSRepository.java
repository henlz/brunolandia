package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.ICMS;

/**
 * @author Henrique
 *
 */
public interface IICMSRepository extends JpaRepository<ICMS, Long>
{

}
