package br.com.brunolandia.sisvarejo.domain.entity.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.TipoPessoa;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Condicao;
import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Cidade;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * 
 * @author Henrique
 *
 */
@Entity
@DataTransferObject(javascript = "Fornecedor")
public class Fornecedor extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3255907075969052447L;

	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false)
	private String codigo;

	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false)
	private String razaoSocial;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private TipoPessoa tipoPessoa;

	/**
	 * 
	 */
	@Column
	private String nomeFantasia;

	/**
	 * 
	 */
	@Column
	private String inscricaoEstadual;

	/**
	 * 
	 */
	@Column
	private String email;

	/**
	 * 
	 */
	@Column
	private String telefone;

	/**
	 * 
	 */
	@Column
	private String fax;

	/**
	 * 
	 */
	@Column
	private String cep;

	/**
	 * 
	 */
	@Column
	private String bairro;

	/**
	 * 
	 */
	@Column
	private String complemento;

	/**
	 * 
	 */
	@Column
	@CNPJ
	private String cnpj;

	/**
	 * 
	 */
	@Column
	private String uf;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private String endereco;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Integer numero;

	/**
	 * 
	 */
	@NotNull
	@ManyToOne
	private Cidade cidade;

	/**
	 * 
	 */
	@Column
	private Boolean transportadora;

	/**
	 * 
	 */
	@NotNull
	@ManyToOne
	private Condicao condicaoPagamento;

	/**
	 * 
	 */
	public Fornecedor()
	{
		super();
	}

	/**
	 * 
	 * @param id
	 */
	public Fornecedor( Long id )
	{
		super( id );
	}

	/**
	 * @param codigo
	 * @param razaoSocial
	 * @param tipoPessoa
	 * @param nomeFantasia
	 * @param representante
	 * @param email
	 * @param telefone
	 * @param fax
	 * @param cep
	 * @param bairro
	 * @param complemento
	 * @param cnpj
	 * @param uf
	 * @param endereco
	 * @param numero
	 * @param cidade
	 * @param transportadora
	 * @param condicaoPagamento
	 */
	public Fornecedor( Long id, String codigo, String razaoSocial, TipoPessoa tipoPessoa, String nomeFantasia, String representante, String email, String telefone, String fax, String cep, String bairro, String complemento, String cnpj, String uf, String endereco, Integer numero, Cidade cidade, Boolean transportadora, Condicao condicaoPagamento )
	{
		super( id );
		this.codigo = codigo;
		this.razaoSocial = razaoSocial;
		this.tipoPessoa = tipoPessoa;
		this.nomeFantasia = nomeFantasia;
		this.inscricaoEstadual = representante;
		this.email = email;
		this.telefone = telefone;
		this.fax = fax;
		this.cep = cep;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cnpj = cnpj;
		this.uf = uf;
		this.endereco = endereco;
		this.numero = numero;
		this.cidade = cidade;
		this.transportadora = transportadora;
		this.condicaoPagamento = condicaoPagamento;
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
		result = prime * result + ( ( bairro == null ) ? 0 : bairro.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( cnpj == null ) ? 0 : cnpj.hashCode() );
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( complemento == null ) ? 0 : complemento.hashCode() );
		result = prime * result + ( ( condicaoPagamento == null ) ? 0 : condicaoPagamento.hashCode() );
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( endereco == null ) ? 0 : endereco.hashCode() );
		result = prime * result + ( ( fax == null ) ? 0 : fax.hashCode() );
		result = prime * result + ( ( nomeFantasia == null ) ? 0 : nomeFantasia.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( razaoSocial == null ) ? 0 : razaoSocial.hashCode() );
		result = prime * result + ( ( inscricaoEstadual == null ) ? 0 : inscricaoEstadual.hashCode() );
		result = prime * result + ( ( telefone == null ) ? 0 : telefone.hashCode() );
		result = prime * result + ( ( tipoPessoa == null ) ? 0 : tipoPessoa.hashCode() );
		result = prime * result + ( ( transportadora == null ) ? 0 : transportadora.hashCode() );
		result = prime * result + ( ( uf == null ) ? 0 : uf.hashCode() );
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
		Fornecedor other = ( Fornecedor ) obj;
		if ( bairro == null )
		{
			if ( other.bairro != null ) return false;
		}
		else if ( !bairro.equals( other.bairro ) ) return false;
		if ( cep == null )
		{
			if ( other.cep != null ) return false;
		}
		else if ( !cep.equals( other.cep ) ) return false;
		if ( cidade == null )
		{
			if ( other.cidade != null ) return false;
		}
		else if ( !cidade.equals( other.cidade ) ) return false;
		if ( cnpj == null )
		{
			if ( other.cnpj != null ) return false;
		}
		else if ( !cnpj.equals( other.cnpj ) ) return false;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		if ( complemento == null )
		{
			if ( other.complemento != null ) return false;
		}
		else if ( !complemento.equals( other.complemento ) ) return false;
		if ( condicaoPagamento == null )
		{
			if ( other.condicaoPagamento != null ) return false;
		}
		else if ( !condicaoPagamento.equals( other.condicaoPagamento ) ) return false;
		if ( email == null )
		{
			if ( other.email != null ) return false;
		}
		else if ( !email.equals( other.email ) ) return false;
		if ( endereco == null )
		{
			if ( other.endereco != null ) return false;
		}
		else if ( !endereco.equals( other.endereco ) ) return false;
		if ( fax == null )
		{
			if ( other.fax != null ) return false;
		}
		else if ( !fax.equals( other.fax ) ) return false;
		if ( nomeFantasia == null )
		{
			if ( other.nomeFantasia != null ) return false;
		}
		else if ( !nomeFantasia.equals( other.nomeFantasia ) ) return false;
		if ( numero == null )
		{
			if ( other.numero != null ) return false;
		}
		else if ( !numero.equals( other.numero ) ) return false;
		if ( razaoSocial == null )
		{
			if ( other.razaoSocial != null ) return false;
		}
		else if ( !razaoSocial.equals( other.razaoSocial ) ) return false;
		if ( inscricaoEstadual == null )
		{
			if ( other.inscricaoEstadual != null ) return false;
		}
		else if ( !inscricaoEstadual.equals( other.inscricaoEstadual ) ) return false;
		if ( telefone == null )
		{
			if ( other.telefone != null ) return false;
		}
		else if ( !telefone.equals( other.telefone ) ) return false;
		if ( tipoPessoa != other.tipoPessoa ) return false;
		if ( transportadora == null )
		{
			if ( other.transportadora != null ) return false;
		}
		else if ( !transportadora.equals( other.transportadora ) ) return false;
		if ( uf == null )
		{
			if ( other.uf != null ) return false;
		}
		else if ( !uf.equals( other.uf ) ) return false;
		return true;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}

	/**
	 * @return the razaoSocial
	 */
	public String getRazaoSocial()
	{
		return razaoSocial;
	}

	/**
	 * @param razaoSocial the razaoSocial to set
	 */
	public void setRazaoSocial( String razaoSocial )
	{
		this.razaoSocial = razaoSocial;
	}

	/**
	 * @return the tipoPessoa
	 */
	public TipoPessoa getTipoPessoa()
	{
		return tipoPessoa;
	}

	/**
	 * @param tipoPessoa the tipoPessoa to set
	 */
	public void setTipoPessoa( TipoPessoa tipoPessoa )
	{
		this.tipoPessoa = tipoPessoa;
	}

	/**
	 * @return the nomeFantasia
	 */
	public String getNomeFantasia()
	{
		return nomeFantasia;
	}

	/**
	 * @param nomeFantasia the nomeFantasia to set
	 */
	public void setNomeFantasia( String nomeFantasia )
	{
		this.nomeFantasia = nomeFantasia;
	}

	/**
	 * @return the representante
	 */
	public String getInscricaoEstadual()
	{
		return inscricaoEstadual;
	}

	/**
	 * @param representante the representante to set
	 */
	public void setInscricaoEstadual( String inscricaoEstadual )
	{
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail( String email )
	{
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone()
	{
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone( String telefone )
	{
		this.telefone = telefone;
	}

	/**
	 * @return the fax
	 */
	public String getFax()
	{
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax( String fax )
	{
		this.fax = fax;
	}

	/**
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep( String cep )
	{
		this.cep = cep;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro()
	{
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro( String bairro )
	{
		this.bairro = bairro;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento()
	{
		return complemento;
	}

	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento( String complemento )
	{
		this.complemento = complemento;
	}

	/**
	 * @return the cnpj
	 */
	public String getCnpj()
	{
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj( String cnpj )
	{
		this.cnpj = cnpj;
	}

	/**
	 * @return the uf
	 */
	public String getUf()
	{
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public void setUf( String uf )
	{
		this.uf = uf;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco()
	{
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco( String endereco )
	{
		this.endereco = endereco;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero()
	{
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero( Integer numero )
	{
		this.numero = numero;
	}

	/**
	 * @return the cidade
	 */
	public Cidade getCidade()
	{
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade( Cidade cidade )
	{
		this.cidade = cidade;
	}

	/**
	 * @return the transportadora
	 */
	public Boolean getTransportadora()
	{
		return transportadora;
	}

	/**
	 * @param transportadora the transportadora to set
	 */
	public void setTransportadora( Boolean transportadora )
	{
		this.transportadora = transportadora;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
