package br.com.brunolandia.sisvarejo.domain.entity.estoque.compra;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique Lobato
 *
 */
@Entity
@DataTransferObject(javascript="Compra")
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
	private BigDecimal baseIcms;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal baseIcmsSt;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal valorIcms;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal valorIcmsSubstituicao;

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
	private BigDecimal valorTotalIpi;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String chaveAcesso;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String dadosNfe;

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
	@OneToMany
	private List<ItemCompra> itensCompra;

	/**
	 * 
	 */
	public Compra()
	{
		super();
	}

	/**
	 * 
	 * @param baseIcms
	 * @param baseIcmsSt
	 * @param valorIcms
	 * @param valorIcmsSubstituicao
	 * @param valorFrete
	 * @param valorSeguro
	 * @param desconto
	 * @param outrasDespesas
	 * @param valorTotalIpi
	 * @param chaveAcesso
	 * @param dadosNfe
	 * @param numeroNfe
	 */
	public Compra( BigDecimal baseIcms, BigDecimal baseIcmsSt, BigDecimal valorIcms, BigDecimal valorIcmsSubstituicao, BigDecimal valorFrete, BigDecimal valorSeguro, BigDecimal desconto, BigDecimal outrasDespesas, BigDecimal valorTotalIpi, String chaveAcesso, String dadosNfe, String numeroNfe, List<ItemCompra> itensCompra )
	{
		super();
		this.baseIcms = baseIcms;
		this.baseIcmsSt = baseIcmsSt;
		this.valorIcms = valorIcms;
		this.valorIcmsSubstituicao = valorIcmsSubstituicao;
		this.valorFrete = valorFrete;
		this.valorSeguro = valorSeguro;
		this.desconto = desconto;
		this.outrasDespesas = outrasDespesas;
		this.valorTotalIpi = valorTotalIpi;
		this.chaveAcesso = chaveAcesso;
		this.dadosNfe = dadosNfe;
		this.numeroNfe = numeroNfe;
		this.itensCompra = itensCompra;
	}

	public BigDecimal getBaseIcms()
	{
		return baseIcms;
	}

	public void setBaseIcms( BigDecimal baseIcms )
	{
		this.baseIcms = baseIcms;
	}

	public BigDecimal getBaseIcmsSt()
	{
		return baseIcmsSt;
	}

	public void setBaseIcmsSt( BigDecimal baseIcmsSt )
	{
		this.baseIcmsSt = baseIcmsSt;
	}

	public BigDecimal getValorIcms()
	{
		return valorIcms;
	}

	public void setValorIcms( BigDecimal valorIcms )
	{
		this.valorIcms = valorIcms;
	}

	public BigDecimal getValorIcmsSubstituicao()
	{
		return valorIcmsSubstituicao;
	}

	public void setValorIcmsSubstituicao( BigDecimal valorIcmsSubstituicao )
	{
		this.valorIcmsSubstituicao = valorIcmsSubstituicao;
	}

	public BigDecimal getValorFrete()
	{
		return valorFrete;
	}

	public void setValorFrete( BigDecimal valorFrete )
	{
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorSeguro()
	{
		return valorSeguro;
	}

	public void setValorSeguro( BigDecimal valorSeguro )
	{
		this.valorSeguro = valorSeguro;
	}

	public BigDecimal getDesconto()
	{
		return desconto;
	}

	public void setDesconto( BigDecimal desconto )
	{
		this.desconto = desconto;
	}

	public BigDecimal getOutrasDespesas()
	{
		return outrasDespesas;
	}

	public void setOutrasDespesas( BigDecimal outrasDespesas )
	{
		this.outrasDespesas = outrasDespesas;
	}

	public BigDecimal getValorTotalIpi()
	{
		return valorTotalIpi;
	}

	public void setValorTotalIpi( BigDecimal valorTotalIpi )
	{
		this.valorTotalIpi = valorTotalIpi;
	}

	public String getChaveAcesso()
	{
		return chaveAcesso;
	}

	public void setChaveAcesso( String chaveAcesso )
	{
		this.chaveAcesso = chaveAcesso;
	}

	public String getDadosNfe()
	{
		return dadosNfe;
	}

	public void setDadosNfe( String dadosNfe )
	{
		this.dadosNfe = dadosNfe;
	}

	public String getNumeroNfe()
	{
		return numeroNfe;
	}

	public void setNumeroNfe( String numeroNfe )
	{
		this.numeroNfe = numeroNfe;
	}

	public List<ItemCompra> getItensCompra()
	{
		return itensCompra;
	}

	public void setItensCompra( List<ItemCompra> itensCompra )
	{
		this.itensCompra = itensCompra;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( baseIcms == null ) ? 0 : baseIcms.hashCode() );
		result = prime * result + ( ( baseIcmsSt == null ) ? 0 : baseIcmsSt.hashCode() );
		result = prime * result + ( ( chaveAcesso == null ) ? 0 : chaveAcesso.hashCode() );
		result = prime * result + ( ( dadosNfe == null ) ? 0 : dadosNfe.hashCode() );
		result = prime * result + ( ( desconto == null ) ? 0 : desconto.hashCode() );
		result = prime * result + ( ( itensCompra == null ) ? 0 : itensCompra.hashCode() );
		result = prime * result + ( ( numeroNfe == null ) ? 0 : numeroNfe.hashCode() );
		result = prime * result + ( ( outrasDespesas == null ) ? 0 : outrasDespesas.hashCode() );
		result = prime * result + ( ( valorFrete == null ) ? 0 : valorFrete.hashCode() );
		result = prime * result + ( ( valorIcms == null ) ? 0 : valorIcms.hashCode() );
		result = prime * result + ( ( valorIcmsSubstituicao == null ) ? 0 : valorIcmsSubstituicao.hashCode() );
		result = prime * result + ( ( valorSeguro == null ) ? 0 : valorSeguro.hashCode() );
		result = prime * result + ( ( valorTotalIpi == null ) ? 0 : valorTotalIpi.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Compra other = ( Compra ) obj;
		if ( baseIcms == null )
		{
			if ( other.baseIcms != null ) return false;
		}
		else if ( !baseIcms.equals( other.baseIcms ) ) return false;
		if ( baseIcmsSt == null )
		{
			if ( other.baseIcmsSt != null ) return false;
		}
		else if ( !baseIcmsSt.equals( other.baseIcmsSt ) ) return false;
		if ( chaveAcesso == null )
		{
			if ( other.chaveAcesso != null ) return false;
		}
		else if ( !chaveAcesso.equals( other.chaveAcesso ) ) return false;
		if ( dadosNfe == null )
		{
			if ( other.dadosNfe != null ) return false;
		}
		else if ( !dadosNfe.equals( other.dadosNfe ) ) return false;
		if ( desconto == null )
		{
			if ( other.desconto != null ) return false;
		}
		else if ( !desconto.equals( other.desconto ) ) return false;
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
		if ( valorIcms == null )
		{
			if ( other.valorIcms != null ) return false;
		}
		else if ( !valorIcms.equals( other.valorIcms ) ) return false;
		if ( valorIcmsSubstituicao == null )
		{
			if ( other.valorIcmsSubstituicao != null ) return false;
		}
		else if ( !valorIcmsSubstituicao.equals( other.valorIcmsSubstituicao ) ) return false;
		if ( valorSeguro == null )
		{
			if ( other.valorSeguro != null ) return false;
		}
		else if ( !valorSeguro.equals( other.valorSeguro ) ) return false;
		if ( valorTotalIpi == null )
		{
			if ( other.valorTotalIpi != null ) return false;
		}
		else if ( !valorTotalIpi.equals( other.valorTotalIpi ) ) return false;
		return true;
	}

}
