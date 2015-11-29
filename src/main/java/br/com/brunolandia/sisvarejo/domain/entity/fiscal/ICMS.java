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
@DataTransferObject(javascript = "ICMS")
public class ICMS extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6283698592221702769L;

	/**
	 * 
	 */
	@Column(nullable = false, unique = true)
	@NotEmpty
	private String codigo;

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
	private BigDecimal porcentagem;

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
	 * @return the porcentagem
	 */
	public BigDecimal getPorcentagem()
	{
		return porcentagem;
	}

	/**
	 * @param porcentagem the porcentagem to set
	 */
	public void setPorcentagem( BigDecimal porcentagem )
	{
		this.porcentagem = porcentagem;
	}

	/**
	 * 
	 */
	public ICMS()
	{
		super();
	}

	/**
	 * @param codigo
	 * @param descricao
	 * @param porcentagem
	 */
	public ICMS( String codigo, String descricao, BigDecimal porcentagem )
	{
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.porcentagem = porcentagem;
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
		result = prime * result + ( ( porcentagem == null ) ? 0 : porcentagem.hashCode() );
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
		ICMS other = ( ICMS ) obj;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		if ( descricao == null )
		{
			if ( other.descricao != null ) return false;
		}
		else if ( !descricao.equals( other.descricao ) ) return false;
		if ( porcentagem == null )
		{
			if ( other.porcentagem != null ) return false;
		}
		else if ( !porcentagem.equals( other.porcentagem ) ) return false;
		return true;
	}
}
