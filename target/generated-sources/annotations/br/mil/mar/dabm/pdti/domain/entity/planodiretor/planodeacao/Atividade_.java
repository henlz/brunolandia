package br.mil.mar.dabm.pdti.domain.entity.planodiretor.planodeacao;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Atividade.class)
public abstract class Atividade_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<Atividade, String> codigo;
	public static volatile SingularAttribute<Atividade, Meta> meta;
	public static volatile SingularAttribute<Atividade, Calendar> inicio;
	public static volatile SingularAttribute<Atividade, Calendar> termino;
	public static volatile SingularAttribute<Atividade, String> descricao;

}

