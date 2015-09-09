package br.com.brunolandia.sisvarejo.domain.repository.caracteristicas;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Cor;

public interface ICorRepository extends JpaRepository<Cor, Long> {

}
