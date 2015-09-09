package br.com.brunolandia.sisvarejo.domain.entity.financeiro;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;

@Entity
@DataTransferObject
public class FormaPagamento extends AbstractEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006593921690000636L;
	
	/**
	 * 
	 */
	@Column
	@NotEmpty
	private String tipo;

	/**
	 * 
	 */
	public FormaPagamento()
	{
		super();
	}

	/**
	 * 
	 * @param id
	 * @param tipo
	 */
	public FormaPagamento(Long id, String tipo)
	{
		super(id);
		this.tipo = tipo;
	}

	/**
	 * 
	 */
	public String getTipo()
	{
		return tipo;
	}
	
	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		FormaPagamento other = (FormaPagamento) obj;
		if (tipo == null)
		{
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
