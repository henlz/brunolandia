package br.com.brunolandia.sisvarejo.domain.entity.loja.venda;

import java.math.BigDecimal;
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
	private static final long serialVersionUID = -8782870129844177610L;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal valorFrete;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal valorSeguro;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal desconto;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal outrasDespesas;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String numeroNfe;

	/**
	 * 
	 */
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemVenda> itensVenda;

	/**
	 * 
	 */
	@Column(nullable = false)
	private Date dataVenda;

	/**
	 * 
	 */
	@Column(nullable = false)
	private Date dataEmissao;

	/**
	 * 
	 */
	@NotNull
	@ManyToOne
	private Cliente cliente;

	/**
	 * 
	 */
	public Venda()
	{
		super();
	}

	/**
	 * @param valorFrete
	 * @param valorSeguro
	 * @param desconto
	 * @param outrasDespesas
	 * @param numeroNfe
	 * @param itensVenda
	 * @param dataVenda
	 * @param dataEmissao
	 * @param cliente
	 */
	public Venda( Long id, BigDecimal valorFrete, BigDecimal valorSeguro, BigDecimal desconto, BigDecimal outrasDespesas, String numeroNfe, List<ItemVenda> itensVenda, Date dataVenda, Date dataEmissao, Cliente cliente )
	{
		super( id );
		this.valorFrete = valorFrete;
		this.valorSeguro = valorSeguro;
		this.desconto = desconto;
		this.outrasDespesas = outrasDespesas;
		this.numeroNfe = numeroNfe;
		this.itensVenda = itensVenda;
		this.dataVenda = dataVenda;
		this.dataEmissao = dataEmissao;
		this.cliente = cliente;
	}

	/**
	 * @return the valorFrete
	 */
	public BigDecimal getValorFrete()
	{
		return valorFrete;
	}

	/**
	 * @param valorFrete the valorFrete to set
	 */
	public void setValorFrete( BigDecimal valorFrete )
	{
		this.valorFrete = valorFrete;
	}

	/**
	 * @return the valorSeguro
	 */
	public BigDecimal getValorSeguro()
	{
		return valorSeguro;
	}

	/**
	 * @param valorSeguro the valorSeguro to set
	 */
	public void setValorSeguro( BigDecimal valorSeguro )
	{
		this.valorSeguro = valorSeguro;
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

	/**
	 * @return the outrasDespesas
	 */
	public BigDecimal getOutrasDespesas()
	{
		return outrasDespesas;
	}

	/**
	 * @param outrasDespesas the outrasDespesas to set
	 */
	public void setOutrasDespesas( BigDecimal outrasDespesas )
	{
		this.outrasDespesas = outrasDespesas;
	}

	/**
	 * @return the numeroNfe
	 */
	public String getNumeroNfe()
	{
		return numeroNfe;
	}

	/**
	 * @param numeroNfe the numeroNfe to set
	 */
	public void setNumeroNfe( String numeroNfe )
	{
		this.numeroNfe = numeroNfe;
	}

	/**
	 * @return the itensVenda
	 */
	public List<ItemVenda> getItensVenda()
	{
		return itensVenda;
	}

	/**
	 * @param itensVenda the itensVenda to set
	 */
	public void setItensVenda( List<ItemVenda> itensVenda )
	{
		this.itensVenda = itensVenda;
	}

	/**
	 * @return the dataVenda
	 */
	public Date getDataVenda()
	{
		return dataVenda;
	}

	/**
	 * @param dataVenda the dataVenda to set
	 */
	public void setDataVenda( Date dataVenda )
	{
		this.dataVenda = dataVenda;
	}

	/**
	 * @return the dataEmissao
	 */
	public Date getDataEmissao()
	{
		return dataEmissao;
	}

	/**
	 * @param dataEmissao the dataEmissao to set
	 */
	public void setDataEmissao( Date dataEmissao )
	{
		this.dataEmissao = dataEmissao;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente()
	{
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
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
		result = prime * result + ( ( dataVenda == null ) ? 0 : dataVenda.hashCode() );
		result = prime * result + ( ( dataEmissao == null ) ? 0 : dataEmissao.hashCode() );
		result = prime * result + ( ( desconto == null ) ? 0 : desconto.hashCode() );
		result = prime * result + ( ( cliente == null ) ? 0 : cliente.hashCode() );
		result = prime * result + ( ( itensVenda == null ) ? 0 : itensVenda.hashCode() );
		result = prime * result + ( ( numeroNfe == null ) ? 0 : numeroNfe.hashCode() );
		result = prime * result + ( ( outrasDespesas == null ) ? 0 : outrasDespesas.hashCode() );
		result = prime * result + ( ( valorFrete == null ) ? 0 : valorFrete.hashCode() );
		result = prime * result + ( ( valorSeguro == null ) ? 0 : valorSeguro.hashCode() );
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
		Venda other = ( Venda ) obj;
		if ( dataVenda == null )
		{
			if ( other.dataVenda != null ) return false;
		}
		else if ( !dataVenda.equals( other.dataVenda ) ) return false;
		if ( dataEmissao == null )
		{
			if ( other.dataEmissao != null ) return false;
		}
		else if ( !dataEmissao.equals( other.dataEmissao ) ) return false;
		if ( desconto == null )
		{
			if ( other.desconto != null ) return false;
		}
		else if ( !desconto.equals( other.desconto ) ) return false;
		if ( cliente == null )
		{
			if ( other.cliente != null ) return false;
		}
		else if ( !cliente.equals( other.cliente ) ) return false;
		if ( itensVenda == null )
		{
			if ( other.itensVenda != null ) return false;
		}
		else if ( !itensVenda.equals( other.itensVenda ) ) return false;
		if ( numeroNfe == null )
		{
			if ( other.numeroNfe != null ) return false;
		}
		else if ( !numeroNfe.equals( other.numeroNfe ) ) return false;
		if ( outrasDespesas == null )
		{
			if ( other.outrasDespesas != null ) return false;
		}
		else if ( !outrasDespesas.equals( other.outrasDespesas ) ) return false;
		if ( valorFrete == null )
		{
			if ( other.valorFrete != null ) return false;
		}
		else if ( !valorFrete.equals( other.valorFrete ) ) return false;
		if ( valorSeguro == null )
		{
			if ( other.valorSeguro != null ) return false;
		}
		else if ( !valorSeguro.equals( other.valorSeguro ) ) return false;
		return true;
	}

}
