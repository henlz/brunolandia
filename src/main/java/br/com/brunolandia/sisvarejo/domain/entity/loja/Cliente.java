package br.com.brunolandia.sisvarejo.domain.entity.loja;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.brunolandia.sisvarejo.domain.entity.Genero;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Condicao;
import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Cidade;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique Lobato Zago
 *
 */
@Entity
@DataTransferObject
public class Cliente extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7805273102952062700L;

	/**
	 * 
	 */
	@NotEmpty
	@Column
	private String nome;

	/**
	 * 
	 */
	@Column
	private String apelido;
	
	/**
	 * 
	 */
	@Column
	private Date dataNascimento;

	/**
	 * 
	 */
	@Column
	@Email
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
	private String rg;

	/**
	 * 
	 */
	@Column
	private String cpf;
	
	/**
	 * 
	 */
	@Column
	private Genero sexo;

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
	@ManyToOne
	private Condicao condicao;

	/**
	 * 
	 */
	public Cliente()
	{
		super();
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 * @param apelido
	 * @param dataNascimento
	 * @param email
	 * @param telefone
	 * @param cep
	 * @param bairro
	 * @param complemento
	 * @param rg
	 * @param cpf
	 * @param endereco
	 * @param numero
	 * @param cidade
	 * @param ativo
	 */
	public Cliente( Long id, String nome, String apelido, Date dataNascimento, String email, String telefone, String cep, String bairro, String complemento, String rg, String cpf, String endereco, Integer numero, Cidade cidade, Boolean ativo, Genero sexo, Condicao condicao )
	{
		super( id );
		this.nome = nome;
		this.apelido = apelido;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.telefone = telefone;
		this.cep = cep;
		this.bairro = bairro;
		this.complemento = complemento;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numero = numero;
		this.cidade = cidade;
		this.ativo = ativo;
		this.sexo = sexo;
		this.condicao = condicao;
	}

	

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( apelido == null ) ? 0 : apelido.hashCode() );
		result = prime * result + ( ( ativo == null ) ? 0 : ativo.hashCode() );
		result = prime * result + ( ( bairro == null ) ? 0 : bairro.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( complemento == null ) ? 0 : complemento.hashCode() );
		result = prime * result + ( ( condicao == null ) ? 0 : condicao.hashCode() );
		result = prime * result + ( ( cpf == null ) ? 0 : cpf.hashCode() );
		result = prime * result + ( ( dataNascimento == null ) ? 0 : dataNascimento.hashCode() );
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( endereco == null ) ? 0 : endereco.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( rg == null ) ? 0 : rg.hashCode() );
		result = prime * result + ( ( sexo == null ) ? 0 : sexo.hashCode() );
		result = prime * result + ( ( telefone == null ) ? 0 : telefone.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Cliente other = ( Cliente ) obj;
		if ( apelido == null )
		{
			if ( other.apelido != null ) return false;
		}
		else if ( !apelido.equals( other.apelido ) ) return false;
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
		if ( complemento == null )
		{
			if ( other.complemento != null ) return false;
		}
		else if ( !complemento.equals( other.complemento ) ) return false;
		if ( condicao == null )
		{
			if ( other.condicao != null ) return false;
		}
		else if ( !condicao.equals( other.condicao ) ) return false;
		if ( cpf == null )
		{
			if ( other.cpf != null ) return false;
		}
		else if ( !cpf.equals( other.cpf ) ) return false;
		if ( dataNascimento == null )
		{
			if ( other.dataNascimento != null ) return false;
		}
		else if ( !dataNascimento.equals( other.dataNascimento ) ) return false;
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
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		if ( numero == null )
		{
			if ( other.numero != null ) return false;
		}
		else if ( !numero.equals( other.numero ) ) return false;
		if ( rg == null )
		{
			if ( other.rg != null ) return false;
		}
		else if ( !rg.equals( other.rg ) ) return false;
		if ( sexo != other.sexo ) return false;
		if ( telefone == null )
		{
			if ( other.telefone != null ) return false;
		}
		else if ( !telefone.equals( other.telefone ) ) return false;
		return true;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}

	public String getApelido()
	{
		return apelido;
	}

	public void setApelido( String apelido )
	{
		this.apelido = apelido;
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

	public static long getSerialversionuid()
	{
		return serialVersionUID;
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

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco( String endereco )
	{
		this.endereco = endereco;
	}

	public Integer getNumero()
	{
		return numero;
	}

	public void setNumero( Integer numero )
	{
		this.numero = numero;
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

	public String getRg()
	{
		return rg;
	}

	public void setRg( String rg )
	{
		this.rg = rg;
	}

	public String getCpf()
	{
		return cpf;
	}

	public void setCpf( String cpf )
	{
		this.cpf = cpf;
	}

	public Date getDataNascimento()
	{
		return dataNascimento;
	}

	public void setDataNascimento( Date dataNascimento )
	{
		this.dataNascimento = dataNascimento;
	}

	public Genero getSexo()
	{
		return sexo;
	}

	public void setSexo( Genero sexo )
	{
		this.sexo = sexo;
	}

	public Condicao getCondicao()
	{
		return condicao;
	}

	public void setCondicao( Condicao condicao )
	{
		this.condicao = condicao;
	}

	
}
