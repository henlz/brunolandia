package br.com.brunolandia.sisvarejo.domain.entity.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

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
	private static final long serialVersionUID = -496453736580794359L;

	/**
	 * 
	 */
	@NotEmpty
	@Column
	private String razaoSocial;

	/**
	 * 
	 */
	@Column
	private String nomeFantasia;

	/**
	 * 
	 */
	@Column
	private String representante;
	
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
	private String site;

	/**
	 * 
	 */
	@Column
	private String endereco;

	/**
	 * 
	 */
	@Column
	private Integer numero;

	/**
	 * 
	 */
	@ManyToOne
	private Cidade cidade;

	/**
	 * 
	 */
	@Column
	private Boolean ativo;

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
	 * 
	 * @param razaoSocial
	 * @param nomeFantasia
	 * @param email
	 * @param telefone
	 * @param cep
	 * @param bairro
	 * @param complemento
	 * @param cnpj
	 * @param site
	 * @param endereco
	 * @param cidade
	 * @param ativo
	 */
	public Fornecedor( Long id, String razaoSocial, String nomeFantasia, String representante, String email, String telefone, String cep, String bairro, String complemento, String cnpj, String site, String endereco, Cidade cidade, Boolean ativo, Integer numero )
	{
		super( id );
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.representante = representante;
		this.email = email;
		this.telefone = telefone;
		this.cep = cep;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cnpj = cnpj;
		this.site = site;
		this.endereco = endereco;
		this.cidade = cidade;
		this.ativo = ativo;
		this.numero = numero;
	}

	public String getRazaoSocial()
	{
		return razaoSocial;
	}

	public void setRazaoSocial( String razaoSocial )
	{
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia()
	{
		return nomeFantasia;
	}

	public void setNomeFantasia( String nomeFantasia )
	{
		this.nomeFantasia = nomeFantasia;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public String getTelefone()
	{
		return telefone;
	}

	public void setTelefone( String telefone )
	{
		this.telefone = telefone;
	}

	public String getCep()
	{
		return cep;
	}

	public void setCep( String cep )
	{
		this.cep = cep;
	}

	public String getBairro()
	{
		return bairro;
	}

	public void setBairro( String bairro )
	{
		this.bairro = bairro;
	}

	public String getComplemento()
	{
		return complemento;
	}

	public void setComplemento( String complemento )
	{
		this.complemento = complemento;
	}

	public String getCnpj()
	{
		return cnpj;
	}

	public void setCnpj( String cnpj )
	{
		this.cnpj = cnpj;
	}

	public String getSite()
	{
		return site;
	}

	public void setSite( String site )
	{
		this.site = site;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco( String endereco )
	{
		this.endereco = endereco;
	}

	public Cidade getCidade()
	{
		return cidade;
	}

	public void setCidade( Cidade cidade )
	{
		this.cidade = cidade;
	}

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}

	public Integer getNumero()
	{
		return numero;
	}

	public void setNumero( Integer numero )
	{
		this.numero = numero;
	}
	
	public String getRepresentante()
	{
		return representante;
	}

	public void setRepresentante( String representante )
	{
		this.representante = representante;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( ativo == null ) ? 0 : ativo.hashCode() );
		result = prime * result + ( ( bairro == null ) ? 0 : bairro.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( cnpj == null ) ? 0 : cnpj.hashCode() );
		result = prime * result + ( ( complemento == null ) ? 0 : complemento.hashCode() );
		result = prime * result + ( ( nomeFantasia == null ) ? 0 : nomeFantasia.hashCode() );
		result = prime * result + ( ( representante == null ) ? 0 : representante.hashCode() );
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( endereco == null ) ? 0 : endereco.hashCode() );
		result = prime * result + ( ( razaoSocial == null ) ? 0 : razaoSocial.hashCode() );
		result = prime * result + ( ( site == null ) ? 0 : site.hashCode() );
		result = prime * result + ( ( telefone == null ) ? 0 : telefone.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Fornecedor other = ( Fornecedor ) obj;
		if ( ativo == null )
		{
			if ( other.ativo != null ) return false;
		}
		else if ( !ativo.equals( other.ativo ) ) return false;
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
		if ( complemento == null )
		{
			if ( other.complemento != null ) return false;
		}
		else if ( !complemento.equals( other.complemento ) ) return false;
		if ( nomeFantasia == null )
		{
			if ( other.nomeFantasia != null ) return false;
		}
		else if ( !nomeFantasia.equals( other.nomeFantasia ) ) return false;
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
		if ( razaoSocial == null )
		{
			if ( other.razaoSocial != null ) return false;
		}
		else if ( !razaoSocial.equals( other.razaoSocial ) ) return false;
		if ( site == null )
		{
			if ( other.site != null ) return false;
		}
		else if ( !site.equals( other.site ) ) return false;
		if ( telefone == null )
		{
			if ( other.telefone != null ) return false;
		}
		else if ( !telefone.equals( other.telefone ) ) return false;
		if ( numero == null )
		{
			if ( other.numero != null ) return false;
		}
		else if ( !numero.equals( other.numero ) ) return false;
		if ( representante == null )
		{
			if ( other.representante != null ) return false;
		}
		else if ( !representante.equals( other.representante ) ) return false;
		return true;
	}

}
