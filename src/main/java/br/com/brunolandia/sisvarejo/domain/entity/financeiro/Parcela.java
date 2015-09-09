package br.com.brunolandia.sisvarejo.domain.entity.financeiro;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;

@Entity
@DataTransferObject(javascript = "Parcela")
public class Parcela extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3117126539355415945L;

	/**
	 * 
	 */
	@Column
	private Integer numeroDaParcela;

	/**
	 * 
	 */
	@Column
	private Integer dias;

	/**
	 * 
	 */
	@Column
	private BigDecimal percentual;

	/**
	 * 
	 */
	@ManyToOne
	private FormaPagamento formaPagamento;

	/**
	 * 
	 */
	public Parcela()
	{
		super();
	}

	/**
	 *
	 */
	public Parcela(Long id)
	{
		super(id);
	}

	/**
	 * 
	 * @param numeroDaParcela
	 * @param dias
	 * @param percentual
	 * @param formaPagamento
	 */
	public Parcela(Long id, Integer numeroDaParcela, Integer dias,
			BigDecimal percentual, FormaPagamento formaPagamento)
	{
		super(id);
		this.numeroDaParcela = numeroDaParcela;
		this.dias = dias;
		this.percentual = percentual;
		this.formaPagamento = formaPagamento;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getNumeroDaParcela()
	{
		return numeroDaParcela;
	}

	/**
	 * 
	 * @param numeroDaParcela
	 */
	public void setNumeroDaParcela(Integer numeroDaParcela)
	{
		this.numeroDaParcela = numeroDaParcela;
	}

	public Integer getDias()
	{
		return dias;
	}

	public void setDias(Integer dias)
	{
		this.dias = dias;
	}

	public BigDecimal getPercentual()
	{
		return percentual;
	}

	public void setPercentual(BigDecimal percentual)
	{
		this.percentual = percentual;
	}

	public FormaPagamento getFormaPagamento()
	{
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento)
	{
		this.formaPagamento = formaPagamento;
	}

}