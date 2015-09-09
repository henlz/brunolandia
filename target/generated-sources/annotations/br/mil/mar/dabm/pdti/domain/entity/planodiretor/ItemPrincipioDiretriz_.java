package br.mil.mar.dabm.pdti.domain.entity.planodiretor;

import br.mil.mar.dabm.pdti.domain.entity.elementosgerais.PrincipioDiretriz;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemPrincipioDiretriz.class)
public abstract class ItemPrincipioDiretriz_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<ItemPrincipioDiretriz, String> codigo;
	public static volatile SingularAttribute<ItemPrincipioDiretriz, Secao> secao;
	public static volatile SingularAttribute<ItemPrincipioDiretriz, PrincipioDiretriz> principioDiretriz;

}

