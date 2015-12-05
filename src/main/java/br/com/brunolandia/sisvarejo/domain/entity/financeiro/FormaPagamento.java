package br.com.brunolandia.sisvarejo.domain.entity.financeiro;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

@Entity
@DataTransferObject
public class FormaPagamento extends AbstractEntity
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6962290461102457605L;

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
	private String tipo;

	/**
	 * 
	 */
	public FormaPagamento()
	{
		super();
	}

	/**
	 * 
	 * @param id
	 * @param tipo
	 */
	public FormaPagamento(Long id, String tipo, String codigo)
	{
		super(id);
		this.tipo = tipo;
		this.codigo = codigo;
	}

	/**
	 * 
	 */
	public String getTipo()
	{
		return tipo;
	}
	
	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
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
		result = prime * result + ( ( tipo == null ) ? 0 : tipo.hashCode() );
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
		FormaPagamento other = ( FormaPagamento ) obj;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		if ( tipo == null )
		{
			if ( other.tipo != null ) return false;
		}
		else if ( !tipo.equals( other.tipo ) ) return false;
		return true;
	}

}
