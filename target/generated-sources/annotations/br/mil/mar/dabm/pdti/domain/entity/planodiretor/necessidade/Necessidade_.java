package br.mil.mar.dabm.pdti.domain.entity.planodiretor.necessidade;

import br.mil.mar.dabm.pdti.domain.entity.elementosgerais.CriterioPriorizacao;
import br.mil.mar.dabm.pdti.domain.entity.planodiretor.ItemAnaliseSwot;
import br.mil.mar.dabm.pdti.domain.entity.planodiretor.ItemDocumentoReferencia;
import br.mil.mar.dabm.pdti.domain.entity.planodiretor.ItemEstrategia;
import br.mil.mar.dabm.pdti.domain.entity.planodiretor.ItemPrincipioDiretriz;
import br.mil.mar.dabm.pdti.domain.entity.planodiretor.PlanoDiretor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Necessidade.class)
public abstract class Necessidade_ extends br.com.eits.common.domain.entity.AbstractEntity_ {

	public static volatile SingularAttribute<Necessidade, String> codigo;
	public static volatile ListAttribute<Necessidade, ItemPrincipioDiretriz> itensPrincipioDiretriz;
	public static volatile SingularAttribute<Necessidade, PlanoDiretor> planoDiretor;
	public static volatile SingularAttribute<Necessidade, CriterioPriorizacao> tendencia;
	public static volatile ListAttribute<Necessidade, ItemAnaliseSwot> itensAnaliseSwot;
	public static volatile SingularAttribute<Necessidade, ItemDocumentoReferencia> origem;
	public static volatile SingularAttribute<Necessidade, TipoNecessidade> tipoNecessidade;
	public static volatile ListAttribute<Necessidade, ItemEstrategia> itensEstrategia;
	public static volatile SingularAttribute<Necessidade, CriterioPriorizacao> gravidade;
	public static volatile SingularAttribute<Necessidade, CriterioPriorizacao> urgencia;
	public static volatile SingularAttribute<Necessidade, String> descricao;

}

