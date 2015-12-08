package br.com.brunolandia.sisvarejo.domain.entity.estoque;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Cor;
import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Genero;
import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Tamanho;
import br.com.brunolandia.sisvarejo.domain.entity.fiscal.ICMS;
import br.com.brunolandia.sisvarejo.domain.entity.fiscal.NCM;
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
	@Column(nullable = false)
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
	@Column(nullable = false)
	@NotNull
	private BigDecimal precoVenda;

	/**
	 * 
	 */
	@Column
	private Integer quantidade;

	/**
	 * 
	 */
	@Column(nullable = false)
	@NotNull
	private BigDecimal pesoLiquido;

	/**
	 * 
	 */
	@Column(nullable = false)
	@NotNull
	private BigDecimal pesoBruto;

	/**
	 * 
	 */
	@NotNull
	@ManyToOne(cascade = CascadeType.DETACH)
	private Cor cor;

	/**
	 * 
	 */
	@NotNull
	@ManyToOne(cascade = CascadeType.DETACH)
	private Tamanho tamanho;

	/**
	 * 
	 */
	@NotNull
	@ManyToOne(cascade = CascadeType.DETACH)
	private Genero genero;

	/**
	 * 
	 */
	@ManyToOne(cascade = CascadeType.DETACH)
	private Fornecedor fornecedor;

	/**
	 * 
	 */
	@ManyToOne(cascade = CascadeType.DETACH)
	@NotNull
	private ICMS icms;

	/**
	 * 
	 */
	@ManyToOne(cascade = CascadeType.DETACH)
	@NotNull
	private NCM ncm;

	/**
	 * 
	 */
	@Column
	private String unidade;

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
	public Produto( Long id )
	{
		super( id );
	}

	/**
	 * @param descricao
	 * @param codigo
	 * @param codigoBarra
	 * @param precoVenda
	 * @param quantidade
	 * @param pesoLiquido
	 * @param pesoBruto
	 * @param cor
	 * @param tamanho
	 * @param grupo
	 * @param fornecedor
	 * @param icms
	 * @param ncm
	 */
	public Produto( Long id, String descricao, String codigo, String codigoBarra, BigDecimal precoVenda, Integer quantidade, BigDecimal pesoLiquido, BigDecimal pesoBruto, Cor cor, Tamanho tamanho, Genero grupo, Fornecedor fornecedor, ICMS icms, NCM ncm, String unidade )
	{
		super( id );
		this.descricao = descricao;
		this.codigo = codigo;
		this.codigoBarra = codigoBarra;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.pesoLiquido = pesoLiquido;
		this.pesoBruto = pesoBruto;
		this.cor = cor;
		this.tamanho = tamanho;
		this.genero = grupo;
		this.fornecedor = fornecedor;
		this.icms = icms;
		this.ncm = ncm;
		this.unidade = unidade;
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
	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}

	public BigDecimal getPrecoVenda()
	{
		return precoVenda;
	}

	public void setPrecoVenda( BigDecimal precoVenda )
	{
		this.precoVenda = precoVenda;
	}

	public Cor getCor()
	{
		return cor;
	}

	public void setCor( Cor cor )
	{
		this.cor = cor;
	}

	public Tamanho getTamanho()
	{
		return tamanho;
	}

	public void setTamanho( Tamanho tamanho )
	{
		this.tamanho = tamanho;
	}

	public Fornecedor getFornecedor()
	{
		return fornecedor;
	}

	public void setFornecedor( Fornecedor fornecedor )
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

	/**
	 * @return the pesoLiquido
	 */
	public BigDecimal getPesoLiquido()
	{
		return pesoLiquido;
	}

	/**
	 * @param pesoLiquido the pesoLiquido to set
	 */
	public void setPesoLiquido( BigDecimal pesoLiquido )
	{
		this.pesoLiquido = pesoLiquido;
	}

	/**
	 * @return the pesoBruto
	 */
	public BigDecimal getPesoBruto()
	{
		return pesoBruto;
	}

	/**
	 * @param pesoBruto the pesoBruto to set
	 */
	public void setPesoBruto( BigDecimal pesoBruto )
	{
		this.pesoBruto = pesoBruto;
	}

	/**
	 * @return the icms
	 */
	public ICMS getIcms()
	{
		return icms;
	}

	/**
	 * @param icms the icms to set
	 */
	public void setIcms( ICMS icms )
	{
		this.icms = icms;
	}

	/**
	 * @return the ncm
	 */
	public NCM getNcm()
	{
		return ncm;
	}

	/**
	 * @param ncm the ncm to set
	 */
	public void setNcm( NCM ncm )
	{
		this.ncm = ncm;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero()
	{
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero( Genero genero )
	{
		this.genero = genero;
	}

	/**
	 * @return the unidade
	 */
	public String getUnidade()
	{
		return unidade;
	}

	/**
	 * @param unidade the unidade to set
	 */
	public void setUnidade( String unidade )
	{
		this.unidade = unidade;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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
		result = prime * result + ( ( genero == null ) ? 0 : genero.hashCode() );
		result = prime * result + ( ( icms == null ) ? 0 : icms.hashCode() );
		result = prime * result + ( ( ncm == null ) ? 0 : ncm.hashCode() );
		result = prime * result + ( ( pesoBruto == null ) ? 0 : pesoBruto.hashCode() );
		result = prime * result + ( ( pesoLiquido == null ) ? 0 : pesoLiquido.hashCode() );
		result = prime * result + ( ( precoVenda == null ) ? 0 : precoVenda.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
		result = prime * result + ( ( tamanho == null ) ? 0 : tamanho.hashCode() );
		result = prime * result + ( ( unidade == null ) ? 0 : unidade.hashCode() );
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
		if ( genero == null )
		{
			if ( other.genero != null ) return false;
		}
		else if ( !genero.equals( other.genero ) ) return false;
		if ( icms == null )
		{
			if ( other.icms != null ) return false;
		}
		else if ( !icms.equals( other.icms ) ) return false;
		if ( ncm == null )
		{
			if ( other.ncm != null ) return false;
		}
		else if ( !ncm.equals( other.ncm ) ) return false;
		if ( pesoBruto == null )
		{
			if ( other.pesoBruto != null ) return false;
		}
		else if ( !pesoBruto.equals( other.pesoBruto ) ) return false;
		if ( pesoLiquido == null )
		{
			if ( other.pesoLiquido != null ) return false;
		}
		else if ( !pesoLiquido.equals( other.pesoLiquido ) ) return false;
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
		if ( unidade == null )
		{
			if ( other.unidade != null ) return false;
		}
		else if ( !unidade.equals( other.unidade ) ) return false;
		return true;
	}

}
