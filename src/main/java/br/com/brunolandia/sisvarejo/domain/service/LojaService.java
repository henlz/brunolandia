package br.com.brunolandia.sisvarejo.domain.service;

import java.util.Date;
import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Produto;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaReceber;
import br.com.brunolandia.sisvarejo.domain.entity.loja.Cliente;
import br.com.brunolandia.sisvarejo.domain.entity.loja.venda.ItemVenda;
import br.com.brunolandia.sisvarejo.domain.entity.loja.venda.Venda;
import br.com.brunolandia.sisvarejo.domain.repository.loja.IClienteRepository;
import br.com.brunolandia.sisvarejo.domain.repository.loja.venda.IVendaRepository;

/**
 * @author Henrique Lobato Zago
 *
 */
@Service
@RemoteProxy(name = "lojaService")
@Transactional
public class LojaService
{
	/**
	 * 
	 */
	@Autowired
	private IClienteRepository clienteRepository;

	/**
	 * 
	 */
	@Autowired
	private IVendaRepository vendaRepository;

	/**
	 * 
	 */
	@Autowired
	private FinanceiroService financeiroService;

	/**
	 * 
	 */
	@Autowired
	private EstoqueService estoqueService;

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Cliente> listClientesByStatus( Boolean status )
	{
		if ( status == null )
		{
			return this.clienteRepository.findAll();
		}
		return this.clienteRepository.findByAtivo( status );
	}

	/**
	 * 
	 * @param razaoSocial
	 * @param nomeFantasia
	 * @param telefone
	 * @param cnpj
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Cliente> listClientesByFilters( String nome, String apelido, String cpf, String rg )
	{
		return this.clienteRepository.listByFilters( nome, apelido, cpf, rg );
	}

	/**
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente insertCliente( Cliente cliente )
	{
		Assert.notNull( cliente, "O objeto não pode ser nulo!" );
		cliente.setAtivo( true );
		return this.clienteRepository.save( cliente );
	}

	/**
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente updateCliente( Cliente cliente )
	{
		return this.clienteRepository.save( cliente );
	}

	/**
	 * 
	 * @param cliente
	 * @return
	 */
	public void removeCliente( Cliente cliente )
	{
		this.clienteRepository.delete( cliente );
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Cliente findClienteById( Long id )
	{
		return this.clienteRepository.findOne( id );
	}

	/**
	 * 
	 * @param venda
	 * @return
	 */
	public Venda insertVenda( Venda venda )
	{
		Assert.notNull( venda, "Erro! A venda não pode estar nula!" );

		/**
		 * Cadastra uma conta a receber
		 */
		ContaReceber contaReceberDaVenda = new ContaReceber();
		contaReceberDaVenda.setVenda( venda );
		financeiroService.insertContaReceber( contaReceberDaVenda );
		
		/**
		 * Atualiza a quantidade dos produtos comprados
		 */
		for (ItemVenda itemVenda: venda.getItensVenda())
		{
			Produto produto = this.estoqueService.findProdutoById( itemVenda.getProduto().getId() );
			produto.setQuantidade( produto.getQuantidade() - itemVenda.getQuantidade() );
			this.estoqueService.updateProduto( produto );
		}
		
		venda.setDataVenda( new Date());
		return this.vendaRepository.save( venda );
	}

	/**
	 * 
	 * @param venda
	 * @return
	 */
	public Venda updateVenda( Venda venda )
	{
		return this.vendaRepository.save( venda );
	}

	/**
	 * 
	 * @param venda
	 * @return
	 */
	public void removeVenda( Venda venda )
	{
		this.vendaRepository.delete( venda );
	}

	/**
	 * 
	 * @param venda
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Venda> listVendas( PageRequest pageRequest )
	{
		return this.vendaRepository.findAll();
	}
	
	/**
	 * 
	 * @param vendaId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Venda findVendaById(final Long vendaId) 
	{
		return this.vendaRepository.findOne( vendaId );
	}

}
