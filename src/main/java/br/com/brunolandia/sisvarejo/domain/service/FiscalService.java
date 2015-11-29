package br.com.brunolandia.sisvarejo.domain.service;

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
	public void removeICMS(ICMS icms)
	{
		this.icmsRepository.delete( icms );
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ICMS> listICMS()
	{
		return this.icmsRepository.findAll();
	}
	
	/**
	 * 
	 * @param icms
	 * @return
	 */
	public CSON insertCSON(CSON cson)
	{
		return this.csonRepository.save( cson );
	}
	
	/**
	 * 
	 * @param icms
	 * @return
	 */
	public CSON updateCSON(CSON cson)
	{
		return this.csonRepository.save( cson );
	}
	
	/**
	 * 
	 * @param icms
	 */
	public void removeCSON(CSON cson)
	{
		this.csonRepository.delete( cson );
	}
	
	/**
	 * 
	 * @return
	 */
	public List<CSON> listCSON()
	{
		return this.csonRepository.findAll();
	}
	
	/**
	 * 
	 * @param icms
	 * @return
	 */
	public NCM insertCSON(NCM ncm)
	{
		return this.ncmRepository.save( ncm );
	}
	
	/**
	 * 
	 * @param icms
	 * @return
	 */
	public NCM updateCSON(NCM ncm)
	{
		return this.ncmRepository.save( ncm );
	}
	
	/**
	 * 
	 * @param icms
	 */
	public void removeCSON(NCM ncm)
	{
		this.ncmRepository.delete( ncm );
	}
	
	/**
	 * 
	 * @return
	 */
	public List<NCM> listNCM()
	{
		return this.ncmRepository.findAll();
	}
	
}
