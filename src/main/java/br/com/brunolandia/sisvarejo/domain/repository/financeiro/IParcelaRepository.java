package br.com.brunolandia.sisvarejo.domain.repository.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Parcela;

public interface IParcelaRepository extends JpaRepository<Parcela, Long>
{

}
