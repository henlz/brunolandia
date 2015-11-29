package br.com.brunolandia.sisvarejo.domain.entity.fiscal;

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
	@Column(nullable=false)
	@NotEmpty
	private String codigo;
	
	/**
	 * 
	 */
	@Column(nullable=false)
	@NotEmpty
	private String descricao;
	
	/**
	 * 
	 */
	@Column(nullable=false)
	@NotNull
	private Boolean possuiIpi;

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
	 * @return the possuiIpi
	 */
	public Boolean getPossuiIpi()
	{
		return possuiIpi;
	}

	/**
	 * @param possuiIpi the possuiIpi to set
	 */
	public void setPossuiIpi( Boolean possuiIpi )
	{
		this.possuiIpi = possuiIpi;
	}

	/**
	 * 
	 */
	public NCM()
	{
		super();
	}
	
	/**
	 * @param codigo
	 * @param descricao
	 * @param possuiIpi
	 */
	public NCM( String codigo, String descricao, Boolean possuiIpi )
	{
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.possuiIpi = possuiIpi;
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
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( possuiIpi == null ) ? 0 : possuiIpi.hashCode() );
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
		NCM other = ( NCM ) obj;
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
		if ( possuiIpi == null )
		{
			if ( other.possuiIpi != null ) return false;
		}
		else if ( !possuiIpi.equals( other.possuiIpi ) ) return false;
		return true;
	}
}
