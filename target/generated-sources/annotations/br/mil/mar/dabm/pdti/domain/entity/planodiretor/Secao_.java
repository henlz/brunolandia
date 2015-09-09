package br.mil.mar.dabm.pdti.domain.entity.planodiretor;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Secao.class)
public abstract class Secao_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<Secao, Secao> secaoSuperior;
	public static volatile SingularAttribute<Secao, String> conteudo;
	public static volatile SingularAttribute<Secao, Long> posicao;
	public static volatile SingularAttribute<Secao, PlanoDiretor> planoDiretor;
	public static volatile SingularAttribute<Secao, String> titulo;
	public static volatile SingularAttribute<Secao, TipoSecao> tipoSecao;
	public static volatile SingularAttribute<Secao, String> posConteudo;

}

