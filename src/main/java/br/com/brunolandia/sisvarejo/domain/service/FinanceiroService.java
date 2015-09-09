package br.com.brunolandia.sisvarejo.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brunolandia.sisvarejo.domain.entity.financeiro.Condicao;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaPagar;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaReceber;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.FormaPagamento;
import br.com.brunolandia.sisvarejo.domain.repository.financeiro.ICondicaoRepository;
import br.com.brunolandia.sisvarejo.domain.repository.financeiro.IContaPagarRepository;
import br.com.brunolandia.sisvarejo.domain.repository.financeiro.IContaReceberRepository;
import br.com.brunolandia.sisvarejo.domain.repository.financeiro.IFormaPagamentoRepository;

/**
 * @author Henrique
 *
 */
@Service
@RemoteProxy(name = "financeiroService")
@Transactional
public class FinanceiroService
{

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
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<FormaPagamento> listFormasPagamentoByFilters(String filters)
	{
//		return this.formaPagamentoRepository.listByFilters(filters);
		return this.formaPagamentoRepository.findAll();
	}

	/**
	 * 
	 * @param formaPagamneto
	 * @return
	 */
	public FormaPagamento insertFormaPagamento(FormaPagamento formaPagamneto)
	{
		return this.formaPagamentoRepository.save(formaPagamneto);
	}

	/**
	 * 
	 * @param formaPagamneto
	 * @return
	 */
	public FormaPagamento updateFormaPagamento(FormaPagamento formaPagamneto)
	{
		return this.formaPagamentoRepository.save(formaPagamneto);
	}

	/**
	 * 
	 * @param formasPagamento
	 */
	public void removeFormasPagamento(List<FormaPagamento> formasPagamento)
	{
		this.formaPagamentoRepository.delete(formasPagamento);
	}
	
	/**
	 * 
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Condicao> listCondicoesByFilters(String filters)
	{
//		return this.condicaoRepository.listByFilters(filters);
		return this.condicaoRepository.findAll();
	}
	
	/**
	 * 
	 * @param formaPagamneto
	 * @return
	 */
	public Condicao insertCondicao(Condicao formaPagamneto)
	{
		return this.condicaoRepository.save(formaPagamneto);
	}
	
	/**
	 * 
	 * @param formaPagamneto
	 * @return
	 */
	public Condicao updateCondicao(Condicao formaPagamneto)
	{
		return this.condicaoRepository.save(formaPagamneto);
	}
	
	/**
	 * 
	 * @param formaPagamneto
	 * @return
	 */
	public Condicao findCondicaoById(Long id)
	{
		return this.condicaoRepository.findOne(id);
	}
	
	/**
	 * 
	 * @param condicoes
	 */
	public void removeCondicoes(List<Condicao> condicoes)
	{
		this.condicaoRepository.delete(condicoes);
	}
	
	/**
	 * 
	 * @param contaPagar
	 * @return
	 */
	public ContaPagar insertContaPagar(ContaPagar contaPagar)
	{
		return this.contaPagarRepository.save( contaPagar );
	}
	
	/**
	 * 
	 * @param contaPagar
	 * @return
	 */
	public ContaPagar updateContaPagar(ContaPagar contaPagar)
	{
		return this.contaPagarRepository.save( contaPagar );
	}
	
	/**
	 * 
	 * @param contaPagar
	 * @return
	 */
	public void removeContaPagar(ContaPagar contaPagar)
	{
		this.contaPagarRepository.delete( contaPagar );
	}
	
	/**
	 * 
	 * @param contaPagar
	 * @return
	 */
	public List<ContaPagar> listContasPagar()
	{
		return this.contaPagarRepository.findAll();
	}
	
	/**
	 * 
	 * @param contaReceber
	 * @return
	 */
	public ContaReceber insertContaReceber(ContaReceber contaReceber)
	{
		return this.contaReceberRepository.save( contaReceber );
	}
	
	/**
	 * 
	 * @param contaReceber
	 * @return
	 */
	public ContaReceber updateContaReceber(ContaReceber contaReceber)
	{
		return this.contaReceberRepository.save( contaReceber );
	}
	
	/**
	 * 
	 * @param contaReceber
	 * @return
	 */
	public void removeContaReceber(ContaReceber contaReceber)
	{
		this.contaReceberRepository.delete( contaReceber );
	}
	
	/**
	 * 
	 * @param contaReceber
	 * @return
	 */
	public List<ContaReceber> listContasReceber()
	{
		return this.contaReceberRepository.findAll();
	}
}
