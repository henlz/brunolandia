package br.com.brunolandia.sisvarejo.domain.entity.estoque.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Condicao;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaPagar;
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
	@ManyToOne
	private Condicao condicaoPagamento;

	/**
	 * 
	 */
	@Column
	private String observacao;

	/**
	 * 
	 */
	@Column
	private BigDecimal frete;

	/**
	 * 
	 */
	@Column
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
	@Column
	private Date dataChegada;

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
	@ManyToOne
	private Fornecedor transportadora;

	/**
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ContaPagar> contasAPagar = new ArrayList<ContaPagar>();

	/**
	 * 
	 */
	@Column
	private Boolean cancelada;

	/**
	 * 
	 */
	public Compra()
	{
		super();
	}

	/**
	 * @param valorFrete
	 * @param condicaoPagamento
	 * @param observacao
	 * @param frete
	 * @param outrasDespesas
	 * @param numeroNfe
	 * @param serie
	 * @param modelo
	 * @param itensCompra
	 * @param dataChegada
	 * @param dataEmissao
	 * @param fornecedor
	 * @param transportadora
	 * @param contasAPagar
	 */
	public Compra( BigDecimal valorFrete, Condicao condicaoPagamento, String observacao, BigDecimal frete, BigDecimal outrasDespesas, String numeroNfe, String serie, String modelo, List<ItemCompra> itensCompra, Date dataChegada, Date dataEmissao, Fornecedor fornecedor, Fornecedor transportadora, List<ContaPagar> contasAPagar, Boolean cancelada )
	{
		super();
		this.valorFrete = valorFrete;
		this.condicaoPagamento = condicaoPagamento;
		this.observacao = observacao;
		this.frete = frete;
		this.outrasDespesas = outrasDespesas;
		this.numeroNfe = numeroNfe;
		this.serie = serie;
		this.modelo = modelo;
		this.itensCompra = itensCompra;
		this.dataChegada = dataChegada;
		this.dataEmissao = dataEmissao;
		this.fornecedor = fornecedor;
		this.transportadora = transportadora;
		this.contasAPagar = contasAPagar;
		this.cancelada = cancelada;
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
		result = prime * result + ( ( condicaoPagamento == null ) ? 0 : condicaoPagamento.hashCode() );
		result = prime * result + ( ( contasAPagar == null ) ? 0 : contasAPagar.hashCode() );
		result = prime * result + ( ( dataChegada == null ) ? 0 : dataChegada.hashCode() );
		result = prime * result + ( ( dataEmissao == null ) ? 0 : dataEmissao.hashCode() );
		result = prime * result + ( ( fornecedor == null ) ? 0 : fornecedor.hashCode() );
		result = prime * result + ( ( frete == null ) ? 0 : frete.hashCode() );
		result = prime * result + ( ( itensCompra == null ) ? 0 : itensCompra.hashCode() );
		result = prime * result + ( ( cancelada == null ) ? 0 : cancelada.hashCode() );
		result = prime * result + ( ( modelo == null ) ? 0 : modelo.hashCode() );
		result = prime * result + ( ( numeroNfe == null ) ? 0 : numeroNfe.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
		result = prime * result + ( ( outrasDespesas == null ) ? 0 : outrasDespesas.hashCode() );
		result = prime * result + ( ( serie == null ) ? 0 : serie.hashCode() );
		result = prime * result + ( ( transportadora == null ) ? 0 : transportadora.hashCode() );
		result = prime * result + ( ( valorFrete == null ) ? 0 : valorFrete.hashCode() );
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
		Compra other = ( Compra ) obj;
		if ( condicaoPagamento == null )
		{
			if ( other.condicaoPagamento != null ) return false;
		}
		else if ( !condicaoPagamento.equals( other.condicaoPagamento ) ) return false;
		if ( contasAPagar == null )
		{
			if ( other.contasAPagar != null ) return false;
		}
		else if ( !contasAPagar.equals( other.contasAPagar ) ) return false;
		if ( dataChegada == null )
		{
			if ( other.dataChegada != null ) return false;
		}
		else if ( !dataChegada.equals( other.dataChegada ) ) return false;
		if ( dataEmissao == null )
		{
			if ( other.dataEmissao != null ) return false;
		}
		else if ( !dataEmissao.equals( other.dataEmissao ) ) return false;
		if ( fornecedor == null )
		{
			if ( other.fornecedor != null ) return false;
		}
		else if ( !fornecedor.equals( other.fornecedor ) ) return false;
		if ( frete == null )
		{
			if ( other.frete != null ) return false;
		}
		else if ( !frete.equals( other.frete ) ) return false;
		if ( itensCompra == null )
		{
			if ( other.itensCompra != null ) return false;
		}
		else if ( !itensCompra.equals( other.itensCompra ) ) return false;
		if ( modelo == null )
		{
			if ( other.modelo != null ) return false;
		}
		else if ( !modelo.equals( other.modelo ) ) return false;
		if ( numeroNfe == null )
		{
			if ( other.numeroNfe != null ) return false;
		}
		else if ( !numeroNfe.equals( other.numeroNfe ) ) return false;
		if ( cancelada == null )
		{
			if ( other.cancelada != null ) return false;
		}
		else if ( !cancelada.equals( other.cancelada ) ) return false;
		if ( observacao == null )
		{
			if ( other.observacao != null ) return false;
		}
		else if ( !observacao.equals( other.observacao ) ) return false;
		if ( outrasDespesas == null )
		{
			if ( other.outrasDespesas != null ) return false;
		}
		else if ( !outrasDespesas.equals( other.outrasDespesas ) ) return false;
		if ( serie == null )
		{
			if ( other.serie != null ) return false;
		}
		else if ( !serie.equals( other.serie ) ) return false;
		if ( transportadora == null )
		{
			if ( other.transportadora != null ) return false;
		}
		else if ( !transportadora.equals( other.transportadora ) ) return false;
		if ( valorFrete == null )
		{
			if ( other.valorFrete != null ) return false;
		}
		else if ( !valorFrete.equals( other.valorFrete ) ) return false;
		return true;
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
	 * @return the condicaoPagamento
	 */
	public Condicao getCondicaoPagamento()
	{
		return condicaoPagamento;
	}

	/**
	 * @param condicaoPagamento the condicaoPagamento to set
	 */
	public void setCondicaoPagamento( Condicao condicaoPagamento )
	{
		this.condicaoPagamento = condicaoPagamento;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao()
	{
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao( String observacao )
	{
		this.observacao = observacao;
	}

	/**
	 * @return the frete
	 */
	public BigDecimal getFrete()
	{
		return frete;
	}

	/**
	 * @param frete the frete to set
	 */
	public void setFrete( BigDecimal frete )
	{
		this.frete = frete;
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
	 * @return the serie
	 */
	public String getSerie()
	{
		return serie;
	}

	/**
	 * @param serie the serie to set
	 */
	public void setSerie( String serie )
	{
		this.serie = serie;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo()
	{
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo( String modelo )
	{
		this.modelo = modelo;
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
	 * @return the dataChegada
	 */
	public Date getDataChegada()
	{
		return dataChegada;
	}

	/**
	 * @param dataChegada the dataChegada to set
	 */
	public void setDataChegada( Date dataChegada )
	{
		this.dataChegada = dataChegada;
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
	 * @return the transportadora
	 */
	public Fornecedor getTransportadora()
	{
		return transportadora;
	}

	/**
	 * @param transportadora the transportadora to set
	 */
	public void setTransportadora( Fornecedor transportadora )
	{
		this.transportadora = transportadora;
	}

	/**
	 * @return the contasAPagar
	 */
	public List<ContaPagar> getContasAPagar()
	{
		return contasAPagar;
	}

	/**
	 * @param contasAPagar the contasAPagar to set
	 */
	public void setContasAPagar( List<ContaPagar> contasAPagar )
	{
		this.contasAPagar = contasAPagar;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	/**
	 * @return the cancelada
	 */
	public Boolean getCancelada()
	{
		return cancelada;
	}

	/**
	 * @param cancelada the cancelada to set
	 */
	public void setCancelada( Boolean cancelada )
	{
		this.cancelada = cancelada;
	}

}
