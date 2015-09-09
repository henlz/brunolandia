package br.com.brunolandia.sisvarejo.domain.entity.caracteristicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;

@Entity
@DataTransferObject(javascript = "Tamanho")
public class Tamanho extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7178149500896455125L;
	
	/**
	 * 
	 */
	@NotNull
	@Column
	private String sigla;
	
	/**
	 * 
	 */
	@NotNull
	@Column
	private String nome;

	/**
	 * 
	 */
	public Tamanho()
	{

	}

	/**
	 * 
	 * @param id
	 * @param nome
	 */
	public Tamanho(Long id, String nome, String sigla)
	{
		super(id);
		this.nome = nome;
		this.sigla = sigla;
	}

	/**
	 * 
	 * @return
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * 
	 * @return
	 */
	public String getSigla()
	{
		return sigla;
	}

	/**
	 * 
	 * @param sigla
	 */
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}
}
