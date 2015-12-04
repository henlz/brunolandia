package br.com.brunolandia.sisvarejo.domain.entity.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@DataTransferObject(javascript = "ContaReceber")
public class ContaReceber extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6873159201529919171L;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String numeroNota;

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
	private String numeroParcela;

	/**
	 * 
	 */
	@Column(nullable = false)
	@NotEmpty
	private BigDecimal valor;

	/**
	 * 
	 */
	@Column
	private String descricao;

	/**
	 * 
	 */
	@Column
	private String observacoes;

	/**
	 * 
	 */
	@Column
	private Date emissao;

	/**
	 * 
	 */
	@Column
	private Date vencimento;

	/**
	 * 
	 */
	@NotNull
	@ManyToOne
	private Cliente cliente;

	/**
	 * 
	 */
	@NotNull
	@ManyToOne
	private FormaPagamento formaPagamento;

	/**
	 * 
	 */
	@Column
	private Date dataPagamento;

	/**
	 * 
	 */
	@Column
	private Date dataVencimento;

	/**
	 * 
	 */
	@Column
	private Date dataEmissao;

	/**
	 * @param numeroNota
	 * @param serie
	 * @param numeroParcela
	 * @param valor
	 * @param descricao
	 * @param observacoes
	 * @param emissao
	 * @param vencimento
	 * @param cliente
	 * @param formaPagamento
	 * @param dataPagamento
	 * @param dataVencimento
	 * @param dataEmissao
	 */
	public ContaReceber( Long id, String numeroNota, String serie, String numeroParcela, BigDecimal valor, String descricao, String observacoes, Date emissao, Date vencimento, Cliente cliente, FormaPagamento formaPagamento, Date dataPagamento, Date dataVencimento, Date dataEmissao )
	{
		super( id );
		this.numeroNota = numeroNota;
		this.serie = serie;
		this.numeroParcela = numeroParcela;
		this.valor = valor;
		this.descricao = descricao;
		this.observacoes = observacoes;
		this.emissao = emissao;
		this.vencimento = vencimento;
		this.cliente = cliente;
		this.formaPagamento = formaPagamento;
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
		this.dataEmissao = dataEmissao;
	}

	/**
	 * @return the numeroNota
	 */
	public String getNumeroNota()
	{
		return numeroNota;
	}

	/**
	 * @param numeroNota the numeroNota to set
	 */
	public void setNumeroNota( String numeroNota )
	{
		this.numeroNota = numeroNota;
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
	 * @return the numeroParcela
	 */
	public String getNumeroParcela()
	{
		return numeroParcela;
	}

	/**
	 * @param numeroParcela the numeroParcela to set
	 */
	public void setNumeroParcela( String numeroParcela )
	{
		this.numeroParcela = numeroParcela;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor()
	{
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor( BigDecimal valor )
	{
		this.valor = valor;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	/**
	 * @return the observacoes
	 */
	public String getObservacoes()
	{
		return observacoes;
	}

	/**
	 * @param observacoes the observacoes to set
	 */
	public void setObservacoes( String observacoes )
	{
		this.observacoes = observacoes;
	}

	/**
	 * @return the emissao
	 */
	public Date getEmissao()
	{
		return emissao;
	}

	/**
	 * @param emissao the emissao to set
	 */
	public void setEmissao( Date emissao )
	{
		this.emissao = emissao;
	}

	/**
	 * @return the vencimento
	 */
	public Date getVencimento()
	{
		return vencimento;
	}

	/**
	 * @param vencimento the vencimento to set
	 */
	public void setVencimento( Date vencimento )
	{
		this.vencimento = vencimento;
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
	 * @return the formaPagamento
	 */
	public FormaPagamento getFormaPagamento()
	{
		return formaPagamento;
	}

	/**
	 * @param formaPagamento the formaPagamento to set
	 */
	public void setFormaPagamento( FormaPagamento formaPagamento )
	{
		this.formaPagamento = formaPagamento;
	}

	/**
	 * @return the dataPagamento
	 */
	public Date getDataPagamento()
	{
		return dataPagamento;
	}

	/**
	 * @param dataPagamento the dataPagamento to set
	 */
	public void setDataPagamento( Date dataPagamento )
	{
		this.dataPagamento = dataPagamento;
	}

	/**
	 * @return the dataVencimento
	 */
	public Date getDataVencimento()
	{
		return dataVencimento;
	}

	/**
	 * @param dataVencimento the dataVencimento to set
	 */
	public void setDataVencimento( Date dataVencimento )
	{
		this.dataVencimento = dataVencimento;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( cliente == null ) ? 0 : cliente.hashCode() );
		result = prime * result + ( ( dataEmissao == null ) ? 0 : dataEmissao.hashCode() );
		result = prime * result + ( ( dataPagamento == null ) ? 0 : dataPagamento.hashCode() );
		result = prime * result + ( ( dataVencimento == null ) ? 0 : dataVencimento.hashCode() );
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( emissao == null ) ? 0 : emissao.hashCode() );
		result = prime * result + ( ( formaPagamento == null ) ? 0 : formaPagamento.hashCode() );
		result = prime * result + ( ( numeroNota == null ) ? 0 : numeroNota.hashCode() );
		result = prime * result + ( ( numeroParcela == null ) ? 0 : numeroParcela.hashCode() );
		result = prime * result + ( ( observacoes == null ) ? 0 : observacoes.hashCode() );
		result = prime * result + ( ( serie == null ) ? 0 : serie.hashCode() );
		result = prime * result + ( ( valor == null ) ? 0 : valor.hashCode() );
		result = prime * result + ( ( vencimento == null ) ? 0 : vencimento.hashCode() );
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
		ContaReceber other = ( ContaReceber ) obj;
		if ( cliente == null )
		{
			if ( other.cliente != null ) return false;
		}
		else if ( !cliente.equals( other.cliente ) ) return false;
		if ( dataEmissao == null )
		{
			if ( other.dataEmissao != null ) return false;
		}
		else if ( !dataEmissao.equals( other.dataEmissao ) ) return false;
		if ( dataPagamento == null )
		{
			if ( other.dataPagamento != null ) return false;
		}
		else if ( !dataPagamento.equals( other.dataPagamento ) ) return false;
		if ( dataVencimento == null )
		{
			if ( other.dataVencimento != null ) return false;
		}
		else if ( !dataVencimento.equals( other.dataVencimento ) ) return false;
		if ( descricao == null )
		{
			if ( other.descricao != null ) return false;
		}
		else if ( !descricao.equals( other.descricao ) ) return false;
		if ( emissao == null )
		{
			if ( other.emissao != null ) return false;
		}
		else if ( !emissao.equals( other.emissao ) ) return false;
		if ( formaPagamento == null )
		{
			if ( other.formaPagamento != null ) return false;
		}
		else if ( !formaPagamento.equals( other.formaPagamento ) ) return false;
		if ( numeroNota == null )
		{
			if ( other.numeroNota != null ) return false;
		}
		else if ( !numeroNota.equals( other.numeroNota ) ) return false;
		if ( numeroParcela == null )
		{
			if ( other.numeroParcela != null ) return false;
		}
		else if ( !numeroParcela.equals( other.numeroParcela ) ) return false;
		if ( observacoes == null )
		{
			if ( other.observacoes != null ) return false;
		}
		else if ( !observacoes.equals( other.observacoes ) ) return false;
		if ( serie == null )
		{
			if ( other.serie != null ) return false;
		}
		else if ( !serie.equals( other.serie ) ) return false;
		if ( valor == null )
		{
			if ( other.valor != null ) return false;
		}
		else if ( !valor.equals( other.valor ) ) return false;
		if ( vencimento == null )
		{
			if ( other.vencimento != null ) return false;
		}
		else if ( !vencimento.equals( other.vencimento ) ) return false;
		return true;
	}

}
