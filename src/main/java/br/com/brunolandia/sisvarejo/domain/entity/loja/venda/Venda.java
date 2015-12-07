package br.com.brunolandia.sisvarejo.domain.entity.loja.venda;

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

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Condicao;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaReceber;
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
	private static final long serialVersionUID = 3024445904392216550L;

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
	@Column
	private String observacao;

	/**
	 * 
	 */
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemVenda> itensVenda;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Date dataEmissao;
	
	/**
	 * 
	 */
	@Column
	private Date dataChegada;

	/**
	 * 
	 */
	@NotNull
	@ManyToOne
	private Cliente cliente;

	/**
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ContaReceber> contasAReceber = new ArrayList<>();

	/**
	 * 
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Condicao condicaoPagamento;
	
	/**
	 * 
	 */
	@Column
	private Boolean cancelada;

	/**
	 * 
	 */
	public Venda()
	{
		super();
	}

	/**
	 * @param numeroNfe
	 * @param serie
	 * @param modelo
	 * @param observacao
	 * @param itensVenda
	 * @param dataEmissao
	 * @param cliente
	 * @param contasAReceber
	 * @param condicaoPagamento
	 * @param cancelada
	 */
	public Venda( String numeroNfe, String serie, String modelo, String observacao, List<ItemVenda> itensVenda, Date dataEmissao, Date dataChegada, Cliente cliente, List<ContaReceber> contasAReceber, Condicao condicaoPagamento, Boolean cancelada )
	{
		super();
		this.numeroNfe = numeroNfe;
		this.serie = serie;
		this.modelo = modelo;
		this.observacao = observacao;
		this.itensVenda = itensVenda;
		this.dataEmissao = dataEmissao;
		this.dataChegada = dataChegada;
		this.cliente = cliente;
		this.contasAReceber = contasAReceber;
		this.condicaoPagamento = condicaoPagamento;
		this.cancelada = cancelada;
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
	 * @return the contasAReceber
	 */
	public List<ContaReceber> getContasAReceber()
	{
		return contasAReceber;
	}

	/**
	 * @param contasAReceber the contasAReceber to set
	 */
	public void setContasAReceber( List<ContaReceber> contasAReceber )
	{
		this.contasAReceber = contasAReceber;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( cancelada == null ) ? 0 : cancelada.hashCode() );
		result = prime * result + ( ( cliente == null ) ? 0 : cliente.hashCode() );
		result = prime * result + ( ( condicaoPagamento == null ) ? 0 : condicaoPagamento.hashCode() );
		result = prime * result + ( ( contasAReceber == null ) ? 0 : contasAReceber.hashCode() );
		result = prime * result + ( ( dataChegada == null ) ? 0 : dataChegada.hashCode() );
		result = prime * result + ( ( dataEmissao == null ) ? 0 : dataEmissao.hashCode() );
		result = prime * result + ( ( itensVenda == null ) ? 0 : itensVenda.hashCode() );
		result = prime * result + ( ( modelo == null ) ? 0 : modelo.hashCode() );
		result = prime * result + ( ( numeroNfe == null ) ? 0 : numeroNfe.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
		result = prime * result + ( ( serie == null ) ? 0 : serie.hashCode() );
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
		Venda other = ( Venda ) obj;
		if ( cancelada == null )
		{
			if ( other.cancelada != null ) return false;
		}
		else if ( !cancelada.equals( other.cancelada ) ) return false;
		if ( cliente == null )
		{
			if ( other.cliente != null ) return false;
		}
		else if ( !cliente.equals( other.cliente ) ) return false;
		if ( condicaoPagamento == null )
		{
			if ( other.condicaoPagamento != null ) return false;
		}
		else if ( !condicaoPagamento.equals( other.condicaoPagamento ) ) return false;
		if ( contasAReceber == null )
		{
			if ( other.contasAReceber != null ) return false;
		}
		else if ( !contasAReceber.equals( other.contasAReceber ) ) return false;
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
		if ( itensVenda == null )
		{
			if ( other.itensVenda != null ) return false;
		}
		else if ( !itensVenda.equals( other.itensVenda ) ) return false;
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
		if ( observacao == null )
		{
			if ( other.observacao != null ) return false;
		}
		else if ( !observacao.equals( other.observacao ) ) return false;
		if ( serie == null )
		{
			if ( other.serie != null ) return false;
		}
		else if ( !serie.equals( other.serie ) ) return false;
		return true;
	}
	
}
