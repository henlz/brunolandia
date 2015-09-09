package br.mil.mar.dabm.pdti.domain.entity.planodiretor;

import br.mil.mar.dabm.pdti.domain.entity.elementosgerais.Estrategia;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemEstrategia.class)
public abstract class ItemEstrategia_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<ItemEstrategia, String> codigo;
	public static volatile SingularAttribute<ItemEstrategia, Estrategia> estrategia;
	public static volatile SingularAttribute<ItemEstrategia, Secao> secao;

}

