package br.com.brunolandia.sisvarejo.domain.entity.caracteristicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique
 *
 */
@Entity
@DataTransferObject(javascript = "Genero")
public class Genero extends AbstractEntity
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4563333329712815503L;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String genero;
	
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String codigo;

	/**
	 * 
	 */
	public Genero()
	{
	}

	/**
	 * @param id
	 */
	public Genero( Long id )
	{
		super( id );
	}

	/**
	 * @param genero
	 */
	public Genero( Long id, String genero, String codigo )
	{
		super( id );
		this.genero = genero;
		this.codigo = codigo;
	}

	/**
	 * @return the genero
	 */
	public String getGenero()
	{
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero( String genero )
	{
		this.genero = genero;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( genero == null ) ? 0 : genero.hashCode() );
		result = prime * result + ( ( codigo == null ) ? 0 : genero.hashCode() );
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Genero other = ( Genero ) obj;
		if ( genero == null )
		{
			if ( other.genero != null ) return false;
		}
		else if ( !genero.equals( other.genero ) ) return false;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		return true;
	}

}
