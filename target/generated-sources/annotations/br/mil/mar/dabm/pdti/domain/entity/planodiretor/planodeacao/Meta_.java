package br.mil.mar.dabm.pdti.domain.entity.planodiretor.planodeacao;

import br.mil.mar.dabm.pdti.domain.entity.planodiretor.necessidade.Necessidade;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Meta.class)
public abstract class Meta_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<Meta, String> indicador;
	public static volatile SingularAttribute<Meta, String> codigo;
	public static volatile SingularAttribute<Meta, Necessidade> necessidade;
	public static volatile SingularAttribute<Meta, String> valorIndicador;
	public static volatile SingularAttribute<Meta, Calendar> prazo;
	public static volatile SingularAttribute<Meta, String> descricao;

}

