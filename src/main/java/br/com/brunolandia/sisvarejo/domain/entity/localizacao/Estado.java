package br.com.brunolandia.sisvarejo.domain.entity.localizacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

@Entity
@DataTransferObject(javascript="Estado")
public class Estado extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -850191619941728962L;

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
	private Pais pais;

	/**
	 * 
	 */
	@NotEmpty
	@Column
	private String nome;

	/**
	 * 
	 */
	public Estado()
	{
	}

	/**
	 * 
	 * @param nome
	 */
	public Estado(Long id, String codigo, String nome)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
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
		result = prime * result + ( ( pais == null ) ? 0 : pais.hashCode() );
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
		Estado other = ( Estado ) obj;
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
		if ( pais == null )
		{
			if ( other.pais != null ) return false;
		}
		else if ( !pais.equals( other.pais ) ) return false;
		return true;
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 */
	public Estado(Long id, String nome)
	{
		super(id);
		this.nome = nome;
	}

	public Pais getPais()
	{
		return pais;
	}

	public void setPais(Pais pais)
	{
		this.pais = pais;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
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
