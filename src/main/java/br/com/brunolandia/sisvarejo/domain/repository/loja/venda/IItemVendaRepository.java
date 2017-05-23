/**
 *
 */
package br.com.brunolandia.sisvarejo.domain.repository.loja.venda;

import org.springframework.data.repository.CrudRepository;

import br.com.brunolandia.sisvarejo.domain.entity.loja.venda.ItemVenda;
import org.springframework.stereotype.Repository;

/**
 * @author Henrique
 */
@Repository
public interface IItemVendaRepository extends CrudRepository<ItemVenda, Long> {

}
