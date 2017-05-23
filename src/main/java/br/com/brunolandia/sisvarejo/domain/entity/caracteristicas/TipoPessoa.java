package br.com.brunolandia.sisvarejo.domain.entity.caracteristicas;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author Henrique
 */
@DataTransferObject(type = "enum")
public enum TipoPessoa {

    FISICA, //0
    JURIDICA; //1

}
