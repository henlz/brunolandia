package br.com.brunolandia.sisvarejo.domain.entity.financeiro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Henrique
 */
@Entity
@DataTransferObject(javascript = "Condicao")
public class Condicao extends AbstractEntity {

    /**
     *
     */
    private static final long serialVersionUID = -4405911703875645412L;

    /**
     *
     */
    @Column
    @NotNull
    private String descricao;

    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Parcela> parcelas = new ArrayList<Parcela>();

    /**
     *
     */
    public Condicao() {
        super();
    }

    /**
     * @param id
     */
    public Condicao(Long id) {
        super(id);
    }

    /**
     * Full constructor
     *
     * @param id
     * @param descricao
     */
    public Condicao(Long id, String descricao) {
        super(id);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((parcelas == null) ? 0 : parcelas.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Condicao other = (Condicao) obj;
        if (descricao == null) {
            if (other.descricao != null) return false;
        } else if (!descricao.equals(other.descricao)) return false;
        if (parcelas == null) {
            if (other.parcelas != null) return false;
        } else if (!parcelas.equals(other.parcelas)) return false;
        return true;
    }

}
