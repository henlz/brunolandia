package br.mil.mar.dabm.pdti.domain.entity.planodiretor;

import br.mil.mar.dabm.pdti.domain.entity.elementosgerais.itemswot.ItemSwot;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemAnaliseSwot.class)
public abstract class ItemAnaliseSwot_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<ItemAnaliseSwot, String> codigo;
	public static volatile SingularAttribute<ItemAnaliseSwot, ItemSwot> itemSwot;
	public static volatile SingularAttribute<ItemAnaliseSwot, Secao> secao;

}

