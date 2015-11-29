package br.com.brunolandia.sisvarejo.domain.entity.fiscal;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique
 *
 */
@Entity
@DataTransferObject(javascript = "CSON")
public class CSON extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2064448064081944961L;

	/**
	 * 
	 */
	@Column(nullable = false)
	@NotEmpty
	private String codigo;

	/**
	 * 
	 */
	@Column(nullable = false)
	@NotEmpty
	private String descricao;

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

	/**
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	/**
	 * 
	 */
	public CSON()
	{
		super();
	}

	/**
	 * @param codigo
	 * @param descricao
	 * @param possuiIpi
	 */
	public CSON( Long id, String codigo, String descricao )
	{
		super( id );
		this.codigo = codigo;
		this.descricao = descricao;
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
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
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
		CSON other = ( CSON ) obj;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		if ( descricao == null )
		{
			if ( other.descricao != null ) return false;
		}
		return true;
	}
}
