package br.com.brunolandia.sisvarejo.domain.entity.estoque.compra;

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

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Fornecedor;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique Lobato
 *
 */
@Entity
@DataTransferObject(javascript = "Compra")
public class Compra extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2315057633448230531L;

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
	@NotNull
	@Column(nullable = false)
	private String serie;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String modelo;
	
	/**
	 * 
	 */
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemCompra> itensCompra;

	/**
	 * 
	 */
	@Column(nullable = false)
	private Date dataCompra;

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
	private Fornecedor fornecedor;

	/**
	 * 
	 */
	public Compra()
	{
		super();
	}

	/**
	 * @param valorFrete
	 * @param valorSeguro
	 * @param desconto
	 * @param outrasDespesas
	 * @param numeroNfe
	 * @param itensCompra
	 * @param dataCompra
	 * @param dataEmissao
	 * @param fornecedor
	 */
	public Compra( Long id, BigDecimal valorFrete, BigDecimal valorSeguro, BigDecimal desconto, BigDecimal outrasDespesas, String numeroNfe, List<ItemCompra> itensCompra, Date dataCompra, Date dataEmissao, Fornecedor fornecedor, String modelo, String serie )
	{
		super( id );
		this.valorFrete = valorFrete;
		this.valorSeguro = valorSeguro;
		this.desconto = desconto;
		this.outrasDespesas = outrasDespesas;
		this.numeroNfe = numeroNfe;
		this.itensCompra = itensCompra;
		this.dataCompra = dataCompra;
		this.dataEmissao = dataEmissao;
		this.fornecedor = fornecedor;
		this.modelo = modelo;
		this.serie = serie;
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
	 * @return the itensCompra
	 */
	public List<ItemCompra> getItensCompra()
	{
		return itensCompra;
	}

	/**
	 * @param itensCompra the itensCompra to set
	 */
	public void setItensCompra( List<ItemCompra> itensCompra )
	{
		this.itensCompra = itensCompra;
	}

	/**
	 * @return the dataCompra
	 */
	public Date getDataCompra()
	{
		return dataCompra;
	}

	/**
	 * @param dataCompra the dataCompra to set
	 */
	public void setDataCompra( Date dataCompra )
	{
		this.dataCompra = dataCompra;
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
	 * @return the fornecedor
	 */
	public Fornecedor getFornecedor()
	{
		return fornecedor;
	}

	/**
	 * @param fornecedor the fornecedor to set
	 */
	public void setFornecedor( Fornecedor fornecedor )
	{
		this.fornecedor = fornecedor;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( dataCompra == null ) ? 0 : dataCompra.hashCode() );
		result = prime * result + ( ( dataEmissao == null ) ? 0 : dataEmissao.hashCode() );
		result = prime * result + ( ( desconto == null ) ? 0 : desconto.hashCode() );
		result = prime * result + ( ( fornecedor == null ) ? 0 : fornecedor.hashCode() );
		result = prime * result + ( ( itensCompra == null ) ? 0 : itensCompra.hashCode() );
		result = prime * result + ( ( numeroNfe == null ) ? 0 : numeroNfe.hashCode() );
		result = prime * result + ( ( outrasDespesas == null ) ? 0 : outrasDespesas.hashCode() );
		result = prime * result + ( ( valorFrete == null ) ? 0 : valorFrete.hashCode() );
		result = prime * result + ( ( valorSeguro == null ) ? 0 : valorSeguro.hashCode() );
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
		Compra other = ( Compra ) obj;
		if ( dataCompra == null )
		{
			if ( other.dataCompra != null ) return false;
		}
		else if ( !dataCompra.equals( other.dataCompra ) ) return false;
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
		if ( fornecedor == null )
		{
			if ( other.fornecedor != null ) return false;
		}
		else if ( !fornecedor.equals( other.fornecedor ) ) return false;
		if ( itensCompra == null )
		{
			if ( other.itensCompra != null ) return false;
		}
		else if ( !itensCompra.equals( other.itensCompra ) ) return false;
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
