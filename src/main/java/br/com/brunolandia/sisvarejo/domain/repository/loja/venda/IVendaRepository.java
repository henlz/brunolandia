package br.com.brunolandia.sisvarejo.domain.repository.loja.venda;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunolandia.sisvarejo.domain.entity.loja.venda.Venda;

/**
 * @author Henrique
 *
 */
public interface IVendaRepository extends JpaRepository<Venda, Long>
{
	/**
	 * 
	 * @param numeroNfe
	 * @return
	 */
	public List<Venda> findByNumeroNfe( String numeroNfe );

	/**
	 * 
	 * @param serie
	 * @return
	 */
	public List<Venda> findBySerie( String serie );

	/**
	 * 
	 * @param modelo
	 * @return
	 */
	public List<Venda> findByModelo( String modelo );

}
