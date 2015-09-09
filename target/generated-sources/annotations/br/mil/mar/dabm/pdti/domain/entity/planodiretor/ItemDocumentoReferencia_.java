package br.mil.mar.dabm.pdti.domain.entity.planodiretor;

import br.mil.mar.dabm.pdti.domain.entity.elementosgerais.DocumentoReferencia;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemDocumentoReferencia.class)
public abstract class ItemDocumentoReferencia_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<ItemDocumentoReferencia, String> codigo;
	public static volatile SingularAttribute<ItemDocumentoReferencia, Secao> secao;
	public static volatile SingularAttribute<ItemDocumentoReferencia, DocumentoReferencia> documentoReferencia;

}

