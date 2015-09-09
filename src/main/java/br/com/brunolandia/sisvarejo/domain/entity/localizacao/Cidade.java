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
	private static final long serialVersionUID = 7397773945411042829L;

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
	public Cidade( Long id, String nome, Estado estado )
	{
		super(id);
		this.nome = nome;
		this.estado = estado;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( estado == null ) ? 0 : estado.hashCode() );
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Cidade other = ( Cidade ) obj;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		if ( estado == null )
		{
			if ( other.estado != null ) return false;
		}
		else if ( !estado.equals( other.estado ) ) return false;
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

}
