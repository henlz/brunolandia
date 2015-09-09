package br.com.brunolandia.sisvarejo.domain.entity.loja.venda;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Produto;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique
 *
 */
@Entity
@DataTransferObject(javascript="ItemVenda")
public class ItemVenda extends AbstractEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6170635789033084737L;

	/**
	 * 
	 */
	@Column
	private Integer quantidade;
	
	/**
	 * 
	 */
	@ManyToOne
	private Produto produto;
	
	/**
	 * 
	 */
	public ItemVenda() 
	{
		super();
	}
	
	public ItemVenda(Long id)
	{
		super(id);
	}

	public ItemVenda( Integer quantidade, Produto produto )
	{
		super();
		this.quantidade = quantidade;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		ItemVenda other = ( ItemVenda ) obj;
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
