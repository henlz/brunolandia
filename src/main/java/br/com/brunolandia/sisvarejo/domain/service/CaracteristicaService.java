package br.com.brunolandia.sisvarejo.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Cor;
import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Tamanho;
import br.com.brunolandia.sisvarejo.domain.repository.caracteristicas.ICorRepository;
import br.com.brunolandia.sisvarejo.domain.repository.caracteristicas.ITamanhoRepository;

@Service
@Transactional
@RemoteProxy(name = "caracteristicaService")
public class CaracteristicaService
{

	/**
	 * 
	 */
	@Autowired
	private ICorRepository corRepository;
	
	/**
	 * 
	 */
	@Autowired
	private ITamanhoRepository tamanhoRepository;

	/**
	 * 
	 * @param cor
	 * @return
	 */
	public Cor insertCor(Cor cor)
	{
		return this.corRepository.save(cor);
	}

	/**
	 * 
	 * @param cor
	 * @return
	 */
	public Cor updateCor(Cor cor)
	{
		return this.corRepository.save(cor);
	}

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Cor> listCores()
	{
		return this.corRepository.findAll();
	}

	/**
	 * 
	 * @param cor
	 */
	public void removeCores(List<Cor> cor)
	{
		this.corRepository.delete(cor);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Cor findCorById(Long id)
	{
		final Cor cor = this.corRepository.findOne(id);
		Assert.notNull(cor, "Não foi possível achar a cor com o ID " + id);
		return cor;
	}
	
	/**
	 * 
	 * @param tamanho
	 * @return
	 */
	public Tamanho insertTamanho(Tamanho tamanho)
	{
		return this.tamanhoRepository.save(tamanho);
	}
	
	/**
	 * 
	 * @param tamanho
	 * @return
	 */
	public Tamanho updateTamanho(Tamanho tamanho)
	{
		return this.tamanhoRepository.save(tamanho);
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Tamanho> listTamanhos()
	{
		return this.tamanhoRepository.findAll();
	}
	
	/**
	 * 
	 * @param tamanho
	 */
	public void removeTamanhos(List<Tamanho> tamanho)
	{
		this.tamanhoRepository.delete(tamanho);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Tamanho findTamanhoById(Long id)
	{
		final Tamanho tamanho = this.tamanhoRepository.findOne(id);
		Assert.notNull(tamanho, "Não foi possível achar o tamanho com o ID " + id);
		return tamanho;
	}
	
}
