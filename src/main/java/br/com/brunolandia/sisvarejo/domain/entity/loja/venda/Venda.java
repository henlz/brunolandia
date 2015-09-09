package br.com.brunolandia.sisvarejo.domain.entity.loja.venda;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.brunolandia.sisvarejo.domain.entity.loja.Cliente;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique
 *
 */
@Entity
@DataTransferObject(javascript = "Venda")
public class Venda extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5581320165499880928L;

	/**
	 * 
	 */
	@ManyToOne
	@NotNull
	private Cliente cliente;

	/**
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotEmpty
	private List<ItemVenda> itensVenda;
	
	/**
	 * 
	 */
	@Column(nullable=false)
	private Date dataVenda;

	/**
	 * 
	 */
	public Venda()
	{
		super();
	}

	/**
	 * 
	 * @param cliente
	 * @param itensVenda
	 * @param dataVenda
	 */
	public Venda( Cliente cliente, List<ItemVenda> itensVenda, Date dataVenda )
	{
		super();
		this.cliente = cliente;
		this.itensVenda = itensVenda;
		this.dataVenda = dataVenda;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	/**
	 * 
	 * @param cliente
	 */
	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	public List<ItemVenda> getItensVenda()
	{
		return itensVenda;
	}

	public void setItensVenda( List<ItemVenda> itensVenda )
	{
		this.itensVenda = itensVenda;
	}

	public Date getDataVenda()
	{
		return dataVenda;
	}

	public void setDataVenda( Date dataVenda )
	{
		this.dataVenda = dataVenda;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( cliente == null ) ? 0 : cliente.hashCode() );
		result = prime * result + ( ( dataVenda == null ) ? 0 : dataVenda.hashCode() );
		result = prime * result + ( ( itensVenda == null ) ? 0 : itensVenda.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Venda other = ( Venda ) obj;
		if ( cliente == null )
		{
			if ( other.cliente != null ) return false;
		}
		else if ( !cliente.equals( other.cliente ) ) return false;
		if ( dataVenda == null )
		{
			if ( other.dataVenda != null ) return false;
		}
		else if ( !dataVenda.equals( other.dataVenda ) ) return false;
		if ( itensVenda == null )
		{
			if ( other.itensVenda != null ) return false;
		}
		else if ( !itensVenda.equals( other.itensVenda ) ) return false;
		return true;
	}

}
