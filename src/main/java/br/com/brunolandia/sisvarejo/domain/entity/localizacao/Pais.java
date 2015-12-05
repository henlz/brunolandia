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
	private static final long serialVersionUID = 5814338121472444496L;

	/**
	 * 
	 */
	@NotNull
	@Column
	private String codigo;
	
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
	public Pais( Long id, String codigo, String nome )
	{
		super( id );
		this.codigo = codigo;
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

	/**
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Pais other = ( Pais ) obj;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		return true;
	}
}
