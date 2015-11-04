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
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Fornecedor> listFornecedoresByStatus( Boolean status )
	{
		if ( status == null )
		{
			return this.fornecedorRepository.findAll();
		}
		return this.fornecedorRepository.findByAtivo( status );
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
	public List<Fornecedor> listFornecedoresByFilters( String razaoSocial, String nomeFantasia, String telefone, String cnpj, String representante )
	{
		return this.fornecedorRepository.listByFilters( razaoSocial, nomeFantasia, telefone, cnpj, representante );
	}

	/**
	 * 
	 * @param fornecedor
	 * @return
	 */
	public Fornecedor insertFornecedor( Fornecedor fornecedor )
	{
		Assert.notNull( fornecedor, "O objeto não pode ser nulo!" );
		fornecedor.setAtivo( true );
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
	public void removeFornecedor(final Fornecedor fornecedor )
	{
		this.fornecedorRepository.delete( fornecedor );
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Fornecedor findFornecedorById(final Long id )
	{
		return this.fornecedorRepository.findOne( id );
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
	public Compra insertCompra(final Compra compra )
	{
		for (ItemCompra itemCompra: compra.getItensCompra()) 
		{
			final Produto produto = itemCompra.getProduto();
			produto.setQuantidade( produto.getQuantidade() + itemCompra.getQuantidade() );
			itemCompra.setProduto( this.produtoRepository.save( produto ) );
			this.itemCompraRepository.save( itemCompra );
		}
		
		return this.compraRepository.save( compra );
	}
	
	/**
	 * 
	 * @param compra
	 * @return
	 */
	public Compra updateCompra(final Compra compra)
	{
		return this.compraRepository.save( compra );
	}
	
	/**
	 * 
	 * @param compra
	 */
	public void removeCompra(final Compra compra)
	{
		this.compraRepository.delete( compra );
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Compra findCompraById(final Long id)
	{
		return this.compraRepository.findOne( id );
	}
}
