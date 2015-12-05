package br.com.brunolandia.sisvarejo.domain.entity.localizacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

@Entity
@DataTransferObject(javascript="Cidade")
public class Cidade extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 24812678148630181L;

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
	@ManyToOne
	private Estado estado;

	/**
	 * 
	 */
	@NotEmpty
	@Column
	private String nome;

	/**
	 * 
	 */
	public Cidade()
	{
	}

	/**
	 * 
	 * @param nome
	 */
	public Cidade( String nome )
	{
		super();
		this.nome = nome;
	}
	
	/**
	 * 
	 * @param id
	 * @param nome
	 * @param estado
	 */
	public Cidade( Long id, String nome, Estado estado, String codigo )
	{
		super(id);
		this.nome = nome;
		this.estado = estado;
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
		result = prime * result + ( ( estado == null ) ? 0 : estado.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
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
		Cidade other = ( Cidade ) obj;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		if ( estado == null )
		{
			if ( other.estado != null ) return false;
		}
		else if ( !estado.equals( other.estado ) ) return false;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		return true;
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 */
	public Cidade( Long id, String nome )
	{
		super( id );
		this.nome = nome;
	}

	public Estado getEstado()
	{
		return estado;
	}

	public void setEstado( Estado estado )
	{
		this.estado = estado;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
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

}
