/**
 * 
 */
package br.com.brunolandia.sisvarejo.domain.entity.estoque.compra;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Produto;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique Lobato
 *
 */
@Entity
@DataTransferObject(javascript = "ItemCompra")
public class ItemCompra extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4448434488618600449L;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Integer quantidade;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal precoCompra;

	/**
	 * 
	 */
	@ManyToOne
	private Produto produto;

	/**
	 * 
	 */
	public ItemCompra()
	{
		super();
	}

	/**
	 * 
	 * @param quantidade
	 * @param ncm
	 * @param cst
	 * @param unidade
	 * @param produto
	 */
	public ItemCompra( Integer quantidade, BigDecimal precoCompra, Produto produto )
	{
		super();
		this.quantidade = quantidade;
		this.precoCompra = precoCompra;
		this.produto = produto;
	}

	public Integer getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade( Integer quantidade )
	{
		this.quantidade = quantidade;
	}

	public Produto getProduto()
	{
		return produto;
	}

	public void setProduto( Produto produto )
	{
		this.produto = produto;
	}

	/**
	 * @return the precoCompra
	 */
	public BigDecimal getPrecoCompra()
	{
		return precoCompra;
	}

	/**
	 * @param precoCompra the precoCompra to set
	 */
	public void setPrecoCompra( BigDecimal precoCompra )
	{
		this.precoCompra = precoCompra;
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
		result = prime * result + ( ( precoCompra == null ) ? 0 : precoCompra.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
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
		ItemCompra other = ( ItemCompra ) obj;
		if ( precoCompra == null )
		{
			if ( other.precoCompra != null ) return false;
		}
		else if ( !precoCompra.equals( other.precoCompra ) ) return false;
		if ( produto == null )
		{
			if ( other.produto != null ) return false;
		}
		else if ( !produto.equals( other.produto ) ) return false;
		if ( quantidade == null )
		{
			if ( other.quantidade != null ) return false;
		}
		else if ( !quantidade.equals( other.quantidade ) ) return false;
		return true;
	}

}
