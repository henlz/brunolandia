package br.mil.mar.dabm.pdti.domain.entity.planodiretor.planodeacao;

import java.math.BigDecimal;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvestimentoCusteio.class)
public abstract class InvestimentoCusteio_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<InvestimentoCusteio, Atividade> atividade;
	public static volatile SingularAttribute<InvestimentoCusteio, BigDecimal> custeio;
	public static volatile SingularAttribute<InvestimentoCusteio, Calendar> ano;
	public static volatile SingularAttribute<InvestimentoCusteio, BigDecimal> investimento;

}

