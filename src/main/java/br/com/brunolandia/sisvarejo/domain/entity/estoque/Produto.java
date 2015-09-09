package br.com.brunolandia.sisvarejo.domain.entity.estoque;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.brunolandia.sisvarejo.domain.entity.Genero;
import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Cor;
import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Tamanho;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique
 *
 */
@Entity
@DataTransferObject(javascript = "Produto")
public class Produto extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5414307464251462660L;

	/**
	 * 
	 */
	@Column
	@NotEmpty
	private String descricao;
	
	/**
	 * 
	 */
	@Column
	@NotEmpty
	private String codigo;
	
	/**
	 * 
	 */
	@Column
	private String codigoBarra;
	
	/**
	 * 
	 */
	@Column
	@NotNull
	private BigDecimal precoCusto;
	
	/**
	 * 
	 */
	@Column
	@NotNull
	private BigDecimal precoVenda;
	
	/**
	 * 
	 */
	@Column
	@NotNull
	private Integer quantidade;
	
	/**
	 * 
	 */
	@ManyToOne(cascade=CascadeType.DETACH)
	private Cor cor;
	
	/**
	 * 
	 */
	@ManyToOne(cascade=CascadeType.DETACH)
	private Tamanho tamanho;
	
	/**
	 * 
	 */
	@Column
	private Genero grupo;
	
	/**
	 * 
	 */
	@ManyToOne(cascade=CascadeType.DETACH)
	private Fornecedor fornecedor;

	/**
	 * 
	 */
	public Produto()
	{
		super();
	}
	
	/**
	 * 
	 * @param id
	 */
	public Produto(Long id)
	{
		super(id);
	}
			
	/**
	 * 
	 * @param id
	 * @param descricao
	 * @param codigo
	 * @param precoCusto
	 * @param precoVenda
	 * @param cor
	 * @param tamanho
	 * @param grupo
	 * @param fornecedor
	 * @param codigoBarra
	 */
	public Produto(Long id, String descricao, String codigo, BigDecimal precoCusto,
			BigDecimal precoVenda, Cor cor, Tamanho tamanho, Genero grupo,
			Fornecedor fornecedor, String codigoBarra, Integer quantidade)
	{
		super(id);
		this.descricao = descricao;
		this.codigo = codigo;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.cor = cor;
		this.tamanho = tamanho;
		this.grupo = grupo;
		this.fornecedor = fornecedor;
		this.codigoBarra = codigoBarra;
		this.quantidade = quantidade;
	}
	
	/**
	 * 	
	 * @param id
	 * @param descricao
	 * @param codigo
	 * @param precoCusto
	 * @param precoVenda
	 * @param codigoBarra
	 * @param quantidade
	 */
	public Produto( Long id, String descricao, String codigo, BigDecimal precoCusto, BigDecimal precoVenda, String codigoBarra, Integer quantidade )
	{
		super(id);
		this.descricao = descricao;
		this.codigo = codigo;
		this.codigoBarra = codigoBarra;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	public BigDecimal getPrecoCusto()
	{
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto)
	{
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoVenda()
	{
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda)
	{
		this.precoVenda = precoVenda;
	}

	public Cor getCor()
	{
		return cor;
	}

	public void setCor(Cor cor)
	{
		this.cor = cor;
	}

	public Tamanho getTamanho()
	{
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho)
	{
		this.tamanho = tamanho;
	}

	public Genero getGrupo()
	{
		return grupo;
	}

	public void setGrupo(Genero grupo)
	{
		this.grupo = grupo;
	}

	public Fornecedor getFornecedor()
	{
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor)
	{
		this.fornecedor = fornecedor;
	}

	public String getCodigoBarra()
	{
		return codigoBarra;
	}

	public void setCodigoBarra( String codigoBarra )
	{
		this.codigoBarra = codigoBarra;
	}
	
	public Integer getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade( Integer quantidade )
	{
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( codigoBarra == null ) ? 0 : codigoBarra.hashCode() );
		result = prime * result + ( ( cor == null ) ? 0 : cor.hashCode() );
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( fornecedor == null ) ? 0 : fornecedor.hashCode() );
		result = prime * result + ( ( grupo == null ) ? 0 : grupo.hashCode() );
		result = prime * result + ( ( precoCusto == null ) ? 0 : precoCusto.hashCode() );
		result = prime * result + ( ( precoVenda == null ) ? 0 : precoVenda.hashCode() );
		result = prime * result + ( ( tamanho == null ) ? 0 : tamanho.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Produto other = ( Produto ) obj;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		if ( codigoBarra == null )
		{
			if ( other.codigoBarra != null ) return false;
		}
		else if ( !codigoBarra.equals( other.codigoBarra ) ) return false;
		if ( cor == null )
		{
			if ( other.cor != null ) return false;
		}
		else if ( !cor.equals( other.cor ) ) return false;
		if ( descricao == null )
		{
			if ( other.descricao != null ) return false;
		}
		else if ( !descricao.equals( other.descricao ) ) return false;
		if ( fornecedor == null )
		{
			if ( other.fornecedor != null ) return false;
		}
		else if ( !fornecedor.equals( other.fornecedor ) ) return false;
		if ( grupo != other.grupo ) return false;
		if ( precoCusto == null )
		{
			if ( other.precoCusto != null ) return false;
		}
		else if ( !precoCusto.equals( other.precoCusto ) ) return false;
		if ( precoVenda == null )
		{
			if ( other.precoVenda != null ) return false;
		}
		else if ( !precoVenda.equals( other.precoVenda ) ) return false;
		if ( quantidade == null )
		{
			if ( other.quantidade != null ) return false;
		}
		else if ( !quantidade.equals( other.quantidade ) ) return false;
		if ( tamanho == null )
		{
			if ( other.tamanho != null ) return false;
		}
		else if ( !tamanho.equals( other.tamanho ) ) return false;
		return true;
	}

	
	
	
}
