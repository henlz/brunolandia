package br.com.brunolandia.sisvarejo.domain.repository.localizacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Estado;
import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Pais;

/**
 * 
 * @author Henrique
 *
 */

public interface IEstadoRepository extends JpaRepository<Estado, Long>
{

	public List<Estado> findByPais(Pais pais);
	
}
