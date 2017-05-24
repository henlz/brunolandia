/**
 *
 */
package br.com.brunolandia.sisvarejo.domain.repository.estoque.compra;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.compra.ItemCompra;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Henrique
 */
public interface IItemCompraRepository extends CrudRepository<ItemCompra, Long> {

}
