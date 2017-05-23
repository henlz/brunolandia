package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import org.springframework.data.repository.CrudRepository;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Parcela;
import org.springframework.stereotype.Repository;

public interface IParcelaRepository extends CrudRepository<Parcela, Long>
{

}
