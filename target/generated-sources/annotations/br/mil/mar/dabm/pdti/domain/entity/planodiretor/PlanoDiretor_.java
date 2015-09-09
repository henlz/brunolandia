package br.mil.mar.dabm.pdti.domain.entity.planodiretor;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PlanoDiretor.class)
public abstract class PlanoDiretor_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<PlanoDiretor, Calendar> anoTermino;
	public static volatile SingularAttribute<PlanoDiretor, Calendar> anoInicio;
	public static volatile SingularAttribute<PlanoDiretor, PlanoDiretor> versaoAnterior;
	public static volatile SingularAttribute<PlanoDiretor, StatusDocumento> statusDocumento;
	public static volatile SingularAttribute<PlanoDiretor, Calendar> dataPublicacao;
	public static volatile SingularAttribute<PlanoDiretor, String> versao;
	public static volatile SingularAttribute<PlanoDiretor, String> descricao;

}

