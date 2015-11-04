/**
 * 
 */
package br.com.brunolandia.sisvarejo.domain.repository.estoque.compra;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.compra.ItemCompra;

/**
 * @author Henrique
 *
 */
public interface IItemCompraRepository extends JpaRepository<ItemCompra, Long>
{

}
