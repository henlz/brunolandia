/**
 * 
 */
package br.com.brunolandia.sisvarejo.domain.entity.estoque.compra;

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
	@Column
	private String ncm;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String cst;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String unidade;

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
	public ItemCompra( Integer quantidade, String ncm, String cst, String unidade, Produto produto )
	{
		super();
		this.quantidade = quantidade;
		this.ncm = ncm;
		this.cst = cst;
		this.unidade = unidade;
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

	public String getNcm()
	{
		return ncm;
	}

	public void setNcm( String ncm )
	{
		this.ncm = ncm;
	}

	public String getCst()
	{
		return cst;
	}

	public void setCst( String cst )
	{
		this.cst = cst;
	}

	public String getUnidade()
	{
		return unidade;
	}

	public void setUnidade( String unidade )
	{
		this.unidade = unidade;
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
		result = prime * result + ( ( cst == null ) ? 0 : cst.hashCode() );
		result = prime * result + ( ( ncm == null ) ? 0 : ncm.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
		result = prime * result + ( ( unidade == null ) ? 0 : unidade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		ItemCompra other = ( ItemCompra ) obj;
		if ( cst == null )
		{
			if ( other.cst != null ) return false;
		}
		else if ( !cst.equals( other.cst ) ) return false;
		if ( ncm == null )
		{
			if ( other.ncm != null ) return false;
		}
		else if ( !ncm.equals( other.ncm ) ) return false;
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
		if ( unidade == null )
		{
			if ( other.unidade != null ) return false;
		}
		else if ( !unidade.equals( other.unidade ) ) return false;
		return true;
	}
	
	
}
