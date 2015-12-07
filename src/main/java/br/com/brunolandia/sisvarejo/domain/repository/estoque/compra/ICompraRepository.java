package br.com.brunolandia.sisvarejo.domain.repository.estoque.compra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.compra.Compra;

/**
 * @author Henrique Lobato Zago
 *
 */
public interface ICompraRepository extends JpaRepository<Compra, Long>
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
