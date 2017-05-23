package br.com.brunolandia.sisvarejo.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Cidade;
import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Estado;
import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Pais;
import br.com.brunolandia.sisvarejo.domain.repository.localizacao.ICidadeRepository;
import br.com.brunolandia.sisvarejo.domain.repository.localizacao.IEstadoRepository;
import br.com.brunolandia.sisvarejo.domain.repository.localizacao.IPaisRepository;

@Service
@Transactional
@RemoteProxy(name = "localizacaoService")
public class LocalizacaoService
{

	/**
	 * 
	 */
	@Autowired
	private IPaisRepository paisRepository;

	/**
	 * 
	 */
	@Autowired
	private IEstadoRepository estadoRepository;

	/**
	 * 
	 */
	@Autowired
	private ICidadeRepository cidadeRepository;

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Iterable<Pais> listPaises()
	{
		final Iterable<Pais> paises = this.paisRepository.findAll();
		return paises;
	}

	/**
	 * 
	 * @param pais
	 * @return
	 */
	public Pais insertPais( Pais pais )
	{
		return this.paisRepository.save( pais );
	}

	/**
	 * 
	 * @param pais
	 * @return
	 */
	public Pais updatePais( Pais pais )
	{
		return this.paisRepository.save( pais );
	}

	/**
	 * 
	 * @param paises
	 */
	public void removePais( List<Pais> paises )
	{
		this.paisRepository.delete( paises );
	}

	/**
	 * 
	 * @param filter
	 * @return
	 */
	public List<Pais> listPaisesByFilter( String filter )
	{
		return this.paisRepository.findByNome( filter );
	}

	/**
	 * 
	 * @param estado
	 * @return
	 */
	public Estado insertEstado( Estado estado )
	{
		return this.estadoRepository.save( estado );
	}

	/**
	 * 
	 * @param estado
	 * @return
	 */
	public Estado updateEstado( Estado estado )
	{
		return this.estadoRepository.save( estado );
	}

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Iterable<Estado> listEstados()
	{
		return this.estadoRepository.findAll();
	}

	/*
	 * 
	 */
	public void removeEstados( List<Estado> estados )
	{
		this.estadoRepository.delete( estados );
	}

	/**
	 * 
	 * @param cidade
	 * @return
	 */
	public Cidade insertCidade( Cidade cidade )
	{
		return this.cidadeRepository.save( cidade );
	}

	/**
	 * 
	 * @param cidade
	 * @return
	 */
	public Cidade updateCidade( Cidade cidade )
	{
		return this.cidadeRepository.save( cidade );
	}

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Iterable<Cidade> listCidades()
	{
		return this.cidadeRepository.findAll();
	}

	/*
	 * 
	 */
	public void removeCidades( List<Cidade> cidades )
	{
		this.cidadeRepository.delete( cidades );
	}

	/**
	 * 
	 * @param estado
	 * @return
	 */
	public List<Cidade> listCidadesByEstado( Estado estado )
	{
		return this.cidadeRepository.findByEstado( estado );
	}

	public Cidade findCidadeByCodigo( String codigo )
	{
		return this.cidadeRepository.findByCodigo( codigo );
	}

	/**
	 * 
	 * @param filter
	 * @return
	 */
	public List<Cidade> listCidadesByFilters( String nome, Estado estado, String codigo )
	{
		return this.cidadeRepository.listByFilters( nome, estado != null ? estado.getId() : null, codigo );
	}

	/**
	 * 
	 * @param pais
	 * @return
	 */
	public List<Estado> listEstadosByPais( Pais pais )
	{
		return this.estadoRepository.findByPais( pais );
	}
}
