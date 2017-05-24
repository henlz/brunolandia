package br.com.brunolandia.sisvarejo.domain.service;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.*;
import br.com.brunolandia.sisvarejo.domain.repository.financeiro.ICondicaoRepository;
import br.com.brunolandia.sisvarejo.domain.repository.financeiro.IContaPagarRepository;
import br.com.brunolandia.sisvarejo.domain.repository.financeiro.IContaReceberRepository;
import br.com.brunolandia.sisvarejo.domain.repository.financeiro.IFormaPagamentoRepository;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @author Henrique
 */
@Service
@RemoteProxy(name = "financeiroService")
@Transactional
public class FinanceiroService {

    /**
     *
     */
    @Autowired
    private IFormaPagamentoRepository formaPagamentoRepository;

    /**
     *
     */
    @Autowired
    private ICondicaoRepository condicaoRepository;

    /**
     *
     */
    @Autowired
    private IContaReceberRepository contaReceberRepository;

    /**
     *
     */
    @Autowired
    private IContaPagarRepository contaPagarRepository;

    /**
     *
     * @param id
     * @param tipo
     * @return
     */
    @Transactional(readOnly = true)
    public List<FormaPagamento> listFormasPagamentoByFilters(Long id, String tipo) {
        return this.formaPagamentoRepository.listByFilters(id, tipo);
    }

    /**
     * @param formaPagamneto
     * @return
     */
    public FormaPagamento insertFormaPagamento(FormaPagamento formaPagamneto) {
        return this.formaPagamentoRepository.save(formaPagamneto);
    }

    /**
     * @param formaPagamneto
     * @return
     */
    public FormaPagamento updateFormaPagamento(FormaPagamento formaPagamneto) {
        return this.formaPagamentoRepository.save(formaPagamneto);
    }

    /**
     * @param formasPagamento
     */
    public void removeFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formaPagamentoRepository.delete(formasPagamento);
    }

    /**
     * @param id
     * @return
     */
    public FormaPagamento findFormaPagamentoById(Long id) {
        return this.formaPagamentoRepository.findOne(id);
    }

    /**
     * @param descricao
     * @return
     */
    @Transactional(readOnly = true)
    public List<Condicao> listCondicoesByFilters(String descricao) {
        return this.condicaoRepository.listByFilters(descricao);
    }

    /**
     * @param formaPagamneto
     * @return
     */
    public Condicao insertCondicao(Condicao formaPagamneto) {
        return this.condicaoRepository.save(formaPagamneto);
    }

    /**
     * @param formaPagamneto
     * @return
     */
    public Condicao updateCondicao(Condicao formaPagamneto) {
        return this.condicaoRepository.save(formaPagamneto);
    }

    /**
     * @param id
     * @return
     */
    public Condicao findCondicaoById(Long id) {
        return this.condicaoRepository.findOne(id);
    }

    /**
     * @param condicoes
     */
    public void removeCondicoes(List<Condicao> condicoes) {
        this.condicaoRepository.delete(condicoes);
    }

    /**
     * @param contaPagar
     * @return
     */
    public ContaPagar insertContaPagar(ContaPagar contaPagar) {
        Assert.notNull(contaPagar, "O objeto não pode ser nulo");
        if (contaPagar.getDataPagamento() != null) {
            contaPagar.setStatusConta(StatusConta.PAGA);
        } else {
            contaPagar.setStatusConta(StatusConta.PENDENTE);
        }
        return this.contaPagarRepository.save(contaPagar);
    }

    /**
     * @param contaPagar
     * @return
     */
    public ContaPagar updateContaPagar(ContaPagar contaPagar) {
        return this.contaPagarRepository.save(contaPagar);
    }

    /**
     * @param contaPagar
     * @return
     */
    public void removeContaPagar(ContaPagar contaPagar) {
        this.contaPagarRepository.delete(contaPagar);
    }

    /**
     * @return
     */
    public Iterable<ContaPagar> listContasPagar() {
        return this.contaPagarRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public ContaPagar findContaPagarById(final Long id) {
        return this.contaPagarRepository.findOne(id);
    }

    /**
     * @param contaPagar
     * @return
     */
    public ContaPagar pagarContaPagar(ContaPagar contaPagar) {
        Assert.notNull(contaPagar, "O objeto não pode ser nulo");
        Assert.isTrue(contaPagar.getStatusConta() != StatusConta.CANCELADA, "A conta está cancelada");
        Assert.isTrue(contaPagar.getStatusConta() != StatusConta.PAGA, "A conta já está paga");
        contaPagar.setDataPagamento(new Date());
        contaPagar.setStatusConta(StatusConta.PAGA);

        return this.contaPagarRepository.save(contaPagar);
    }

    /**
     * @param contaPagar
     * @return
     */
    public ContaPagar cancelarContaPagar(ContaPagar contaPagar) {
        Assert.notNull(contaPagar, "O objeto não pode ser nulo");
        Assert.isNull(contaPagar.getCompra(), "Não é possível cancelar a conta, pois a mesma está associada à uma compra");
        Assert.isTrue(contaPagar.getStatusConta() != StatusConta.CANCELADA, "A conta está cancelada");
        Assert.isTrue(contaPagar.getStatusConta() != StatusConta.PAGA, "A conta já está paga");

        contaPagar.setStatusConta(StatusConta.CANCELADA);

        return this.contaPagarRepository.save(contaPagar);
    }

    /**
     * @param contaReceber
     * @return
     */
    public ContaReceber insertContaReceber(ContaReceber contaReceber) {
        Assert.notNull(contaReceber, "O objeto não pode ser nulo");
        if (contaReceber.getDataPagamento() != null) {
            contaReceber.setStatusConta(StatusConta.PAGA);
        } else {
            contaReceber.setStatusConta(StatusConta.PENDENTE);
        }
        return this.contaReceberRepository.save(contaReceber);
    }

    /**
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public ContaReceber findContaReceberById(final Long id) {
        return this.contaReceberRepository.findOne(id);
    }

    /**
     * @param contaReceber
     * @return
     */
    public ContaReceber pagarContaReceber(ContaReceber contaReceber) {
        Assert.notNull(contaReceber, "O objeto não pode ser nulo");
        Assert.isTrue(contaReceber.getStatusConta() != StatusConta.CANCELADA, "A conta está cancelada");
        Assert.isTrue(contaReceber.getStatusConta() != StatusConta.PAGA, "A conta já está paga");
        contaReceber.setDataPagamento(new Date());
        contaReceber.setStatusConta(StatusConta.PAGA);

        return this.contaReceberRepository.save(contaReceber);
    }

    /**
     * @param contaReceber
     * @return
     */
    public ContaReceber cancelarContaReceber(ContaReceber contaReceber) {
        Assert.notNull(contaReceber, "O objeto não pode ser nulo");
        Assert.isNull(contaReceber.getVenda(), "Não é possível cancelar a conta, pois a mesma está associada à uma venda");
        Assert.isTrue(contaReceber.getStatusConta() != StatusConta.CANCELADA, "A conta está cancelada");
        Assert.isTrue(contaReceber.getStatusConta() != StatusConta.PAGA, "A conta já está paga");

        contaReceber.setStatusConta(StatusConta.CANCELADA);

        return this.contaReceberRepository.save(contaReceber);
    }

    /**
     * @param contaReceber
     * @return
     */
    public ContaReceber updateContaReceber(ContaReceber contaReceber) {
        return this.contaReceberRepository.save(contaReceber);
    }

    /**
     * @param contaReceber
     * @return
     */
    public void removeContaReceber(ContaReceber contaReceber) {
        this.contaReceberRepository.delete(contaReceber);
    }

    /**
     * @return
     */
    public Iterable<ContaReceber> listContasReceber() {
        return this.contaReceberRepository.findAll();
    }
}
