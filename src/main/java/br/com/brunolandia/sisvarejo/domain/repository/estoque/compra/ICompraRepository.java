package br.com.brunolandia.sisvarejo.domain.repository.estoque.compra;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.compra.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Henrique Lobato Zago
 *
 */
public interface ICompraRepository extends CrudRepository<Compra, Long>
{
	
	/**
	 * 
	 * @param numeroNfe
	 * @return
	 */
	public List<Compra> findByNumeroNfe(String numeroNfe);
	
	/**
	 * 
	 * @param serie
	 * @return
	 */
	public List<Compra> findBySerie( String serie );

	/**
	 * 
	 * @param modelo
	 * @return
	 */
	public List<Compra> findByModelo( String modelo );

}
