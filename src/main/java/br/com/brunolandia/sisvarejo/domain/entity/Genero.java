package br.com.brunolandia.sisvarejo.domain.entity;

import org.directwebremoting.annotations.DataTransferObject;


/**
 * @author Henrique
 *
 */
@DataTransferObject(type="enum")
public enum Genero
{

	MASCULINO, //0
	FEMININO, //1
	INFANTIL; //2
	
}
