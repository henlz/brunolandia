package br.mil.mar.dabm.pdti.domain.entity.elementosgerais;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DocumentoReferencia.class)
public abstract class DocumentoReferencia_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<DocumentoReferencia, String> sigla;
	public static volatile SingularAttribute<DocumentoReferencia, Calendar> dataFim;
	public static volatile SingularAttribute<DocumentoReferencia, String> titulo;
	public static volatile SingularAttribute<DocumentoReferencia, Calendar> dataInicio;

}

