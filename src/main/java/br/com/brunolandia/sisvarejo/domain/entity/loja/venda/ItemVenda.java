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
	private static final long serialVersionUID = 5513579265939867788L;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Integer quantidade;

	/**
	 * 
	 */
	@ManyToOne
	private Produto produto;

	/**
	 * 
	 */
	@Column(precision=10, scale=2)
	private BigDecimal desconto;

	/**
	 * 
	 */
	public ItemVenda()
	{
		super();
	}

	/**
	 * @param quantidade
	 * @param produto
	 * @param desconto
	 */
	public ItemVenda( Integer quantidade, Produto produto, BigDecimal desconto )
	{
		super();
		this.quantidade = quantidade;
		this.produto = produto;
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

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade()
	{
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade( Integer quantidade )
	{
		this.quantidade = quantidade;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto()
	{
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto( Produto produto )
	{
		this.produto = produto;
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


}
