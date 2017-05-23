package br.com.brunolandia.sisvarejo.domain.repository.loja.venda;

import br.com.brunolandia.sisvarejo.domain.entity.loja.venda.Venda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henrique
 */
@Repository
public interface IVendaRepository extends CrudRepository<Venda, Long> {
    /**
     * @param numeroNfe
     * @return
     */
    public List<Venda> findByNumeroNfe(String numeroNfe);

    /**
     * @param serie
     * @return
     */
    public List<Venda> findBySerie(String serie);

    /**
     * @param modelo
     * @return
     */
    public List<Venda> findByModelo(String modelo);

}
