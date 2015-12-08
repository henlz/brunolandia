package br.com.brunolandia.sisvarejo.domain.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Fornecedor;
import br.com.brunolandia.sisvarejo.domain.entity.estoque.Produto;
import br.com.brunolandia.sisvarejo.domain.entity.estoque.compra.Compra;
import br.com.brunolandia.sisvarejo.domain.entity.estoque.compra.ItemCompra;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.ContaPagar;
import br.com.brunolandia.sisvarejo.domain.entity.financeiro.StatusConta;
import br.com.brunolandia.sisvarejo.domain.repository.estoque.IFornecedorRepository;
import br.com.brunolandia.sisvarejo.domain.repository.estoque.IProdutoRepository;
import br.com.brunolandia.sisvarejo.domain.repository.estoque.compra.ICompraRepository;
import br.com.brunolandia.sisvarejo.domain.repository.estoque.compra.IItemCompraRepository;

/**
 * @author Henrique
 *
 */
@Service
@Transactional
@RemoteProxy(name = "estoqueService")
public class EstoqueService
{

	/**
	 * 
	 */
	@Autowired
	private IProdutoRepository produtoRepository;

	/**
	 * 
	 */
	@Autowired
	private IFornecedorRepository fornecedorRepository;

	/**
	 * 
	 */
	@Autowired
	private ICompraRepository compraRepository;

	/**
	 * 
	 */
	@Autowired
	private IItemCompraRepository itemCompraRepository;

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Produto> listProdutos()
	{
		return this.produtoRepository.findAll();
	}

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Produto> listProdutosByFilters( final String codigo, final String descricao, PageRequest pageRequest )
	{
		return this.produtoRepository.listByFilters( codigo, descricao, pageRequest );
	}

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Produto findProdutoByCodigo( final String codigo )
	{
		return this.produtoRepository.findByCodigo( codigo );
	}

	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Produto findProdutoById( final Long id )
	{
		return this.produtoRepository.findOne( id );
	}

	/**
	 * 
	 * @param produto
	 * @return
	 */
	public Produto insertProduto( final Produto produto )
	{
		produto.setQuantidade( 0 );
		return this.produtoRepository.save( produto );
	}

	/**
	 * 
	 * @param produto
	 * @return
	 */
	public Produto updateProduto( final Produto produto )
	{
		return this.produtoRepository.save( produto );
	}

	/**
	 * 
	 * @param produto
	 */
	public void removeProduto( Produto produto )
	{
		this.produtoRepository.delete( produto );
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
	public List<Fornecedor> listFornecedoresByFilters( String razaoSocial, String nomeFantasia, String telefone, String cnpj, Boolean transportadora )
	{
		return this.fornecedorRepository.listByFilters( razaoSocial, nomeFantasia, telefone, cnpj, transportadora );
	}

	/**
	 * 
	 * @param fornecedor
	 * @return
	 */
	public Fornecedor insertFornecedor( Fornecedor fornecedor )
	{
		Assert.notNull( fornecedor, "O objeto não pode ser nulo!" );
		return this.fornecedorRepository.save( fornecedor );
	}

	/**
	 * 
	 * @param fornecedor
	 * @return
	 */
	public Fornecedor updateFornecedor( final Fornecedor fornecedor )
	{
		return this.fornecedorRepository.save( fornecedor );
	}

	/**
	 * 
	 * @param fornecedor
	 * @return
	 */
	public void removeFornecedor( final Fornecedor fornecedor )
	{
		this.fornecedorRepository.delete( fornecedor );
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Fornecedor findFornecedorById( final Long id )
	{
		return this.fornecedorRepository.findOne( id );
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Fornecedor findFornecedorByCodigo( final String codigo, Boolean transportadora )
	{
		return this.fornecedorRepository.findByCodigoTransportadora( codigo, transportadora );
	}

	/**
	 * 
	 */
	@Transactional(readOnly = true)
	public List<Compra> listCompras()
	{
		return this.compraRepository.findAll();
	}

	/**
	 * 
	 * @param compra
	 * @return
	 */
	public Compra insertCompra( Compra compra )
	{
		for ( ItemCompra itemCompra : compra.getItensCompra() )
		{
			final Produto produto = itemCompra.getProduto();
			if ( produto.getQuantidade() == null )
			{
				produto.setQuantidade( 0 );
			}
			produto.setQuantidade( produto.getQuantidade() + itemCompra.getQuantidade() );
			itemCompra.setProduto( this.produtoRepository.save( produto ) );
			this.itemCompraRepository.save( itemCompra );
		}
		
		compra = this.compraRepository.save( compra );
		
		for (ContaPagar contaPagar: compra.getContasAPagar())
		{
			contaPagar.setCompra( compra );
		}
		
		compra = this.compraRepository.save( compra );
		return compra;
	}

	/**
	 * 
	 * @param compra
	 * @return
	 */
	public Compra updateCompra( final Compra compra )
	{
		return this.compraRepository.save( compra );
	}

	/**
	 * 
	 * @param compra
	 */
	public void removeCompra( final Compra compra )
	{
		this.compraRepository.delete( compra );
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Compra findCompraById( final Long id )
	{
		return this.compraRepository.findOne( id );
	}
	
	/**
	 * 
	 * @param 
	 * @return
	 */
	public Compra cancelarCompra( Compra compra )
	{
		Compra compraBanco = this.compraRepository.findOne( compra.getId() );

		compraBanco.setObservacao( compra.getObservacao() );
		compraBanco.setCancelada( true );

		for ( ContaPagar contaPagar : compraBanco.getContasAPagar() )
		{
			Assert.isTrue( contaPagar.getStatusConta() != StatusConta.PAGA, "Não é possível cancelar a compra, pois há um pagamento registrado!" );
			contaPagar.setStatusConta( StatusConta.CANCELADA );
		}

		for ( ItemCompra itemCompra : compra.getItensCompra() )
		{
			Produto produto = itemCompra.getProduto();
			produto.setQuantidade( produto.getQuantidade() - itemCompra.getQuantidade() );
			this.updateProduto( produto );
		}

		compra = this.compraRepository.save( compraBanco );
		return compra;
	}

	/**
	 * 
	 * @param numeroNfe
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean verificarNfe( String numeroNfe, String serie, String modelo )
	{
		Boolean numeroFlag = false;
		Boolean serieFlag = false;
		Boolean modeloFlag = false;
		
		Assert.isTrue(numeroNfe != null && numeroNfe.length() > 0, "O numero não pode estar vazio!");
		Assert.isTrue(serie != null && serie.length() > 0, "A série não pode estar vazia!");
		Assert.isTrue(modelo != null && modelo.length() > 0, "O modelo não pode estar vazio!");
		
		List<Compra> compras = this.compraRepository.findByNumeroNfe( numeroNfe );
		if ( compras.size() > 0 )
		{
			numeroFlag = true;
		}

		compras = this.compraRepository.findBySerie( serie );
		if ( compras.size() > 0 )
		{
			serieFlag = true;
		}

		compras = this.compraRepository.findByModelo( modelo );
		if ( compras.size() > 0 )
		{
			modeloFlag = true;
		}

		if ( numeroFlag == true && serieFlag == true && modeloFlag == true )
		{
			return false;
		}

		return true;
	}
}
