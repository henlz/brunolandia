package br.com.brunolandia.sisvarejo.domain.entity.loja.venda;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Produto;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique
 *
 */
@Entity
@DataTransferObject(javascript = "ItemVenda")
public class ItemVenda extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9082033479670066564L;

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
	private BigDecimal precoVenda;

	/**
	 * 
	 */
	@ManyToOne
	private Produto produto;

	/**
	 * 
	 */
	@Column
	private BigDecimal desconto;

	/**
	 * 
	 */
	public ItemVenda()
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
	public ItemVenda( Integer quantidade, BigDecimal precoVenda, Produto produto, BigDecimal desconto )
	{
		super();
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.produto = produto;
		this.desconto = desconto;
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
	 * @return the precoVenda
	 */
	public BigDecimal getPrecoVenda()
	{
		return precoVenda;
	}

	/**
	 * @param precoVenda the precoVenda to set
	 */
	public void setPrecoVenda( BigDecimal precoVenda )
	{
		this.precoVenda = precoVenda;
	}

	/**
	 * @return the desconto
	 */
	public BigDecimal getDesconto()
	{
		return desconto;
	}

	/**
	 * @param desconto the desconto to set
	 */
	public void setDesconto( BigDecimal desconto )
	{
		this.desconto = desconto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( desconto == null ) ? 0 : desconto.hashCode() );
		result = prime * result + ( ( precoVenda == null ) ? 0 : precoVenda.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
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
		ItemVenda other = ( ItemVenda ) obj;
		if ( desconto == null )
		{
			if ( other.desconto != null ) return false;
		}
		else if ( !desconto.equals( other.desconto ) ) return false;
		if ( precoVenda == null )
		{
			if ( other.precoVenda != null ) return false;
		}
		else if ( !precoVenda.equals( other.precoVenda ) ) return false;
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
