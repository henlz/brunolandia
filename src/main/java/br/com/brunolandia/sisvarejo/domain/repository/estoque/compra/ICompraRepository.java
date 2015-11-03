package br.com.brunolandia.sisvarejo.domain.repository.estoque.compra;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.compra.Compra;

/**
 * @author henriquelobatozago
 *
 */
public interface ICompraRepository extends JpaRepository<Compra, Long>
{

}
