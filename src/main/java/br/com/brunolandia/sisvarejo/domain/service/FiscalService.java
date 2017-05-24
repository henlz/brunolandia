package br.com.brunolandia.sisvarejo.domain.service;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.CSON;
import br.com.brunolandia.sisvarejo.domain.entity.fiscal.ICMS;
import br.com.brunolandia.sisvarejo.domain.entity.fiscal.NCM;
import br.com.brunolandia.sisvarejo.domain.repository.fiscal.ICSONRepository;
import br.com.brunolandia.sisvarejo.domain.repository.fiscal.IICMSRepository;
import br.com.brunolandia.sisvarejo.domain.repository.fiscal.INCMRepository;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Henrique
 */
@Service
@RemoteProxy(name = "fiscalService")
public class FiscalService {

    /**
     *
     */
    @Autowired
    private IICMSRepository icmsRepository;

    /**
     *
     */
    @Autowired
    private ICSONRepository csonRepository;

    /**
     *
     */
    @Autowired
    private INCMRepository ncmRepository;

    /**
     * @param icms
     * @return
     */
    public ICMS insertICMS(ICMS icms) {
        return this.icmsRepository.save(icms);
    }

    /**
     * @param icms
     * @return
     */
    public ICMS updateICMS(ICMS icms) {
        return this.icmsRepository.save(icms);
    }

    /**
     * @param icms
     */
    public void removeICMS(List<ICMS> icms) {
        this.icmsRepository.delete(icms);
    }

    /**
     * @return
     */
    public Iterable<ICMS> listICMS() {
        return this.icmsRepository.findAll();
    }

    /**
     * @param id
     * @param descricao
     * @param porcentagem
     * @return
     */
    public List<ICMS> listICMSByFilters(Long id, String descricao, BigDecimal porcentagem) {
        return this.icmsRepository.listByFilters(id, descricao, porcentagem);
    }

    /**
     * @param id
     * @return
     */
    public ICMS findICMSById(Long id) {
        return this.icmsRepository.findOne(id);
    }

    /**
     * @param cson
     * @return
     */
    public CSON insertCSON(CSON cson) {
        return this.csonRepository.save(cson);
    }

    /**
     * @param cson
     * @return
     */
    public CSON updateCSON(CSON cson) {
        return this.csonRepository.save(cson);
    }

    /**
     * @param cson
     */
    public void removeCSON(List<CSON> cson) {
        this.csonRepository.delete(cson);
    }

    /**
     * @return
     */
    public Iterable<CSON> listCSON() {
        return this.csonRepository.findAll();
    }

    /**
     * @param id
     * @param descricao
     * @return
     */
    public List<CSON> listCSONByFilters(Long id, String descricao) {
        return this.csonRepository.listByFilters(id, descricao);
    }

    /**
     * @param ncm
     * @return
     */
    public NCM insertNCM(NCM ncm) {
        return this.ncmRepository.save(ncm);
    }

    /**
     * @param ncm
     * @return
     */
    public NCM updateNCM(NCM ncm) {
        return this.ncmRepository.save(ncm);
    }

    /**
     * @param ncm
     */
    public void removeNCM(List<NCM> ncm) {
        this.ncmRepository.delete(ncm);
    }

    /**
     * @return
     */
    public Iterable<NCM> listNCM() {
        return this.ncmRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    public NCM findNCMById(Long id) {
        return this.ncmRepository.findOne(id);
    }

    /**
     * @param id
     * @param descricao
     * @param IPI
     * @return
     */
    public List<NCM> listNCMByFilters(Long id, String descricao, BigDecimal IPI) {
        return this.ncmRepository.listByFilters(id, descricao, IPI);
    }

}
