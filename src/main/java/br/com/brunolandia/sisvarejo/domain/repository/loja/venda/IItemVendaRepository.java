/**
 * 
 */
package br.com.brunolandia.sisvarejo.domain.repository.loja.venda;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.loja.venda.ItemVenda;

/**
 * @author Henrique
 *
 */
public interface IItemVendaRepository extends JpaRepository<ItemVenda, Long>
{

}
