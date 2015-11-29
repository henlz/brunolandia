package br.com.brunolandia.sisvarejo.domain.repository.fiscal;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.NCM;

/**
 * @author Henrique
 *
 */
public interface INCMRepository extends JpaRepository<NCM, Long>
{

}
