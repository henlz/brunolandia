package br.com.brunolandia.sisvarejo.domain.entity.localizacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;

@Entity
@DataTransferObject(javascript="Pais")
public class Pais extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5860778329513837543L;

	/**
	 * 
	 */
	@NotNull
	@Column
	private String nome;

	/**
	 * 
	 */
	public Pais()
	{
		super();
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 */
	public Pais( Long id, String nome )
	{
		super( id );
		this.nome = nome;
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
	public void setNome( String nome )
	{
		this.nome = nome;
	}

}
