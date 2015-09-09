package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Condicao;

/**
 * @author Henrique
 *
 */
public interface ICondicaoRepository extends JpaRepository<Condicao, Long>
{

}
