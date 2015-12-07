package br.com.brunolandia.sisvarejo.domain.entity.financeiro;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject(type = "enum")
public enum StatusConta
{
	CANCELADA, PAGA, PENDENTE;
}
