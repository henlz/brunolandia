package br.com.brunolandia.sisvarejo.domain.entity.loja;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.TipoPessoa;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Condicao;
import br.com.brunolandia.sisvarejo.domain.entity.localizacao.Cidade;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Henrique Lobato Zago
 */
@Entity
@DataTransferObject(javascript = "Cliente")
public class Cliente extends AbstractEntity {

    /**
     *
     */
    private static final long serialVersionUID = -3088114456774333186L;

    /**
     *
     */
    @NotEmpty
    @Column(nullable = false)
    private String nome;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private TipoPessoa tipoPessoa;

    /**
     *
     */
    @Column
    private String apelido;

    /**
     *
     */
    @Column
    @Email
    private String email;

    /**
     *
     */
    @Column
    private String telefone;

    /**
     *
     */
    @Column
    private String bairro;

    /**
     *
     */
    @Column
    private String complemento;

    /**
     *
     */
    @Column
    private String rg;

    /**
     *
     */
    @Column
    private String cpf;

    /**
     *
     */
    @Column
    private String endereco;

    /**
     *
     */
    @Column
    private Integer numero;

    /**
     *
     */
    @ManyToOne
    private Cidade cidade;

    /**
     *
     */
    @ManyToOne
    private Condicao condicaoPagamento;

    /**
     *
     */
    public Cliente() {
        super();
    }

    /**
     *
     */
    public Cliente(Long id) {
        super(id);
    }

    /**
     * @param nome
     * @param tipoPessoa
     * @param apelido
     * @param email
     * @param telefone
     * @param bairro
     * @param complemento
     * @param rg
     * @param cpf
     * @param endereco
     * @param numero
     * @param cidade
     * @param condicaoPagamento
     */
    public Cliente(Long id, String nome, TipoPessoa tipoPessoa, String apelido, String email, String telefone, String bairro, String complemento, String rg, String cpf, String endereco, Integer numero, Cidade cidade, Condicao condicaoPagamento) {
        super(id);
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.apelido = apelido;
        this.email = email;
        this.telefone = telefone;
        this.bairro = bairro;
        this.complemento = complemento;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
        this.numero = numero;
        this.cidade = cidade;
        this.condicaoPagamento = condicaoPagamento;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
        result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
        result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
        result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
        result = prime * result + ((condicaoPagamento == null) ? 0 : condicaoPagamento.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        result = prime * result + ((rg == null) ? 0 : rg.hashCode());
        result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
        result = prime * result + ((tipoPessoa == null) ? 0 : tipoPessoa.hashCode());
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
        Cliente other = (Cliente) obj;
        if (apelido == null) {
            if (other.apelido != null) return false;
        } else if (!apelido.equals(other.apelido)) return false;
        if (bairro == null) {
            if (other.bairro != null) return false;
        } else if (!bairro.equals(other.bairro)) return false;
        if (cidade == null) {
            if (other.cidade != null) return false;
        } else if (!cidade.equals(other.cidade)) return false;
        if (complemento == null) {
            if (other.complemento != null) return false;
        } else if (!complemento.equals(other.complemento)) return false;
        if (condicaoPagamento == null) {
            if (other.condicaoPagamento != null) return false;
        } else if (!condicaoPagamento.equals(other.condicaoPagamento)) return false;
        if (cpf == null) {
            if (other.cpf != null) return false;
        } else if (!cpf.equals(other.cpf)) return false;
        if (email == null) {
            if (other.email != null) return false;
        } else if (!email.equals(other.email)) return false;
        if (endereco == null) {
            if (other.endereco != null) return false;
        } else if (!endereco.equals(other.endereco)) return false;
        if (nome == null) {
            if (other.nome != null) return false;
        } else if (!nome.equals(other.nome)) return false;
        if (numero == null) {
            if (other.numero != null) return false;
        } else if (!numero.equals(other.numero)) return false;
        if (rg == null) {
            if (other.rg != null) return false;
        } else if (!rg.equals(other.rg)) return false;
        if (telefone == null) {
            if (other.telefone != null) return false;
        } else if (!telefone.equals(other.telefone)) return false;
        if (tipoPessoa != other.tipoPessoa) return false;
        return true;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the tipoPessoa
     */
    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * @param tipoPessoa the tipoPessoa to set
     */
    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    /**
     * @return the apelido
     */
    public String getApelido() {
        return apelido;
    }

    /**
     * @param apelido the apelido to set
     */
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return the cidade
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the condicaoPagamento
     */
    public Condicao getCondicaoPagamento() {
        return condicaoPagamento;
    }

    /**
     * @param condicaoPagamento the condicaoPagamento to set
     */
    public void setCondicaoPagamento(Condicao condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

}
