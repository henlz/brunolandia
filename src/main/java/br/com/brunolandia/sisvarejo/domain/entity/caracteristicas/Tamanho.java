package br.com.brunolandia.sisvarejo.domain.entity.caracteristicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;

@Entity
@DataTransferObject(javascript = "Tamanho")
public class Tamanho extends AbstractEntity
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5976162240149042116L;

	/**
	 * 
	 */
	@NotNull
	@Column
	private String codigo;
	
	/**
	 * 
	 */
	@NotNull
	@Column
	private String sigla;
	
	/**
	 * 
	 */
	@NotNull
	@Column
	private String nome;

	/**
	 * 
	 */
	public Tamanho()
	{

	}

	/**
	 * 
	 * @param id
	 * @param nome
	 */
	public Tamanho(Long id, String nome, String sigla, String codigo)
	{
		super(id);
		this.nome = nome;
		this.sigla = sigla;
	}

	/**
	 * 
	 * @return
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * 
	 * @return
	 */
	public String getSigla()
	{
		return sigla;
	}

	/**
	 * 
	 * @param sigla
	 */
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( sigla == null ) ? 0 : sigla.hashCode() );
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
		Tamanho other = ( Tamanho ) obj;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		if ( sigla == null )
		{
			if ( other.sigla != null ) return false;
		}
		else if ( !sigla.equals( other.sigla ) ) return false;
		return true;
	}
	
}
