package br.com.brunolandia.sisvarejo.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Produto;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaReceber;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.StatusConta;
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
	private EstoqueService estoqueService;

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Cliente> listClientes()
	{
		return this.clienteRepository.findAll();
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
	 * @param codigo
	 * @return
	 */
	@Transactional(readOnly = true)
	public Cliente findClienteByCodigo( String codigo )
	{
		return this.clienteRepository.findByCodigo( codigo );
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
		 * Atualiza a quantidade dos produtos comprados
		 */
		for ( ItemVenda itemVenda : venda.getItensVenda() )
		{
			Produto produto = this.estoqueService.findProdutoById( itemVenda.getProduto().getId() );
			produto.setQuantidade( produto.getQuantidade() - itemVenda.getQuantidade() );
			this.estoqueService.updateProduto( produto );
		}
		
		venda = this.vendaRepository.save( venda );
		for (ContaReceber contaReceber: venda.getContasAReceber())
		{
			contaReceber.setVenda( venda );
		}
		
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
	@Transactional(readOnly = true)
	public List<Venda> listVendas( PageRequest pageRequest )
	{
		return this.vendaRepository.findAll();
	}

	/**
	 * 
	 * @param vendaId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Venda findVendaById( final Long vendaId )
	{
		return this.vendaRepository.findOne( vendaId );
	}
	
	/**
	 * 
	 * @param venda
	 * @return
	 */
	public Venda cancelarVenda(Venda venda)
	{
		Venda vendaBanco = this.vendaRepository.findOne( venda.getId() );
		
		Assert.notNull( venda.getObservacao(), "A observação não pode estar vazia!" );
		vendaBanco.setObservacao( venda.getObservacao() );
		vendaBanco.setCancelada( true );
		
		for (ContaReceber contaReceber: venda.getContasAReceber())
		{
			Assert.isTrue( contaReceber.getStatusConta() != StatusConta.PAGA, "Não é possível cancelar a venda, pois há um pagamento registrado!" );
			contaReceber.setStatusConta( StatusConta.CANCELADA );
		}
		
		for (ItemVenda itemVenda: venda.getItensVenda())
		{
			Produto produto = itemVenda.getProduto();
			produto.setQuantidade( produto.getQuantidade() + itemVenda.getQuantidade() );
			this.estoqueService.updateProduto( produto );
		}
		
		venda = this.vendaRepository.save( vendaBanco );
		return venda;
	}

	/**
	 * 
	 * @param numeroNfe
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean verificarNfe( String numeroNfe )
	{
		List<Venda> vendas = this.vendaRepository.findByNumeroNfe( numeroNfe );
		if ( vendas.size() > 0 )
		{
			return false;
		}
		return true;
	}
}
