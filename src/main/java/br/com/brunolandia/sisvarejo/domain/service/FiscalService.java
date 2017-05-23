package br.com.brunolandia.sisvarejo.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunolandia.sisvarejo.domain.entity.fiscal.CSON;
import br.com.brunolandia.sisvarejo.domain.entity.fiscal.ICMS;
import br.com.brunolandia.sisvarejo.domain.entity.fiscal.NCM;
import br.com.brunolandia.sisvarejo.domain.repository.fiscal.ICSONRepository;
import br.com.brunolandia.sisvarejo.domain.repository.fiscal.IICMSRepository;
import br.com.brunolandia.sisvarejo.domain.repository.fiscal.INCMRepository;

/**
 * @author Henrique
 *
 */
@Service
@RemoteProxy(name="fiscalService")
public class FiscalService
{

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
	 * 
	 * @param icms
	 * @return
	 */
	public ICMS insertICMS(ICMS icms)
	{
		return this.icmsRepository.save( icms );
	}
	
	/**
	 * 
	 * @param icms
	 * @return
	 */
	public ICMS updateICMS(ICMS icms)
	{
		return this.icmsRepository.save( icms );
	}
	
	/**
	 * 
	 * @param icms
	 */
	public void removeICMS(List<ICMS> icms)
	{
		this.icmsRepository.delete( icms );
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterable<ICMS> listICMS()
	{
		return this.icmsRepository.findAll();
	}
	
	/**
	 * 
	 * @param codigo
	 * @param descricao
	 * @param porcentagem
	 * @return
	 */
	public List<ICMS> listICMSByFilters(String codigo, String descricao, BigDecimal porcentagem) 
	{
		return this.icmsRepository.listByFilters( codigo, descricao, porcentagem );
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public ICMS findICMSByCodigo(String codigo)
	{
		return this.icmsRepository.findByCodigo( codigo );
	}
	
	/**
	 * 
	 * @param cson
	 * @return
	 */
	public CSON insertCSON(CSON cson)
	{
		return this.csonRepository.save( cson );
	}
	
	/**
	 * 
	 * @param cson
	 * @return
	 */
	public CSON updateCSON(CSON cson)
	{
		return this.csonRepository.save( cson );
	}
	
	/**
	 * 
	 * @param cson
	 */
	public void removeCSON(List<CSON> cson)
	{
		this.csonRepository.delete( cson );
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterable<CSON> listCSON()
	{
		return this.csonRepository.findAll();
	}
	
	/**
	 * 
	 * @param codigo
	 * @param descricao
	 * @return
	 */
	public List<CSON> listCSONByFilters(String codigo, String descricao)
	{
		return this.csonRepository.listByFilters( codigo, descricao );
	}
	
	/**
	 * 
	 * @param ncm
	 * @return
	 */
	public NCM insertNCM(NCM ncm)
	{
		return this.ncmRepository.save( ncm );
	}
	
	/**
	 * 
	 * @param icms
	 * @return
	 */
	public NCM updateNCM(NCM ncm)
	{
		return this.ncmRepository.save( ncm );
	}
	
	/**
	 * 
	 * @param icms
	 */
	public void removeNCM(List<NCM> ncm)
	{
		this.ncmRepository.delete( ncm );
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterable<NCM> listNCM()
	{
		return this.ncmRepository.findAll();
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public NCM findNCMByCodigo(String codigo)
	{
		return this.ncmRepository.findByCodigo( codigo );
	}
	
	/**
	 * 
	 * @param codigo
	 * @param descricao
	 * @param possuiIpi
	 * @return
	 */
	public List<NCM> listNCMByFilters(String codigo, String descricao, BigDecimal IPI)
	{
		return this.ncmRepository.listByFilters( codigo, descricao, IPI );
	}
	
}
