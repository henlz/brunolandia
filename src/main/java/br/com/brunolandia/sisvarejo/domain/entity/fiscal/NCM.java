package br.com.brunolandia.sisvarejo.domain.entity.fiscal;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique
 *
 */
@Entity
@DataTransferObject(javascript = "NCM")
public class NCM extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7091223163150736615L;

	/**
	 * 
	 */
	@Column(nullable = false)
	@NotEmpty
	private String descricao;

	/**
	 * 
	 */
	@Column(nullable = false)
	@NotNull
	private BigDecimal IPI;

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
	 * @return the possuiIpi
	 */
	public BigDecimal getIPI()
	{
		return IPI;
	}

	/**
	 * @param possuiIpi the possuiIpi to set
	 */
	public void setIPI( BigDecimal possuiIpi )
	{
		this.IPI = possuiIpi;
	}

	/**
	 * 
	 */
	public NCM()
	{
		super();
	}

	/**
	 * @param descricao
	 * @param possuiIpi
	 */
	public NCM( Long id, String descricao, BigDecimal IPI )
	{
		super( id );
		this.descricao = descricao;
		this.IPI = IPI;
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
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( IPI == null ) ? 0 : IPI.hashCode() );
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
		NCM other = ( NCM ) obj;
		if ( descricao == null )
		{
			if ( other.descricao != null ) return false;
		}
		else if ( !descricao.equals( other.descricao ) ) return false;
		if ( IPI == null )
		{
			if ( other.IPI != null ) return false;
		}
		else if ( !IPI.equals( other.IPI ) ) return false;
		return true;
	}
}
