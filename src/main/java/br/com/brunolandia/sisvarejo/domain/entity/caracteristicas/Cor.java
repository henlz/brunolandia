package br.com.brunolandia.sisvarejo.domain.entity.caracteristicas;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

@Entity
@DataTransferObject(javascript = "Cor")
public class Cor extends AbstractEntity
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2693041616074081510L;
	/**
	 * 
	 */
	@NotEmpty
	@Column
	private String nome;

	/**
	 * 
	 */
	public Cor()
	{
		super();
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 */
	public Cor(Long id, String nome)
	{
		super();
		this.nome = nome;
	}

	/**
	 * 
	 * @param nome
	 */
	public Cor(String nome)
	{
		super();
		this.nome = nome;
	}

	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cor other = (Cor) obj;
		if (nome == null)
		{
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
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

}
