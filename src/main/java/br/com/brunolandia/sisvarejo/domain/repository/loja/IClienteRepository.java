package br.com.brunolandia.sisvarejo.domain.repository.loja;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.loja.Cliente;
import org.springframework.stereotype.Repository;

/**
 * @author Henrique Lobato Zago
 *
 */
public interface IClienteRepository extends CrudRepository<Cliente, Long>
{

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public Cliente findByCodigo(String codigo);
	
	/**
	 * 
	 * @param nome
	 * @param apelido
	 * @param telefone
	 * @param cpf
	 * @param rg
	 * @return
	 */
	@Query("SELECT new Cliente(cliente.id, cliente. codigo, cliente.nome, cliente.tipoPessoa, cliente.apelido, cliente.email, cliente.telefone, cliente.bairro, cliente.complemento, cliente.rg, cliente.cpf, cliente.endereco, cliente.numero, cliente.cidade, cliente.condicaoPagamento) "
				+ "FROM Cliente cliente "
					+ "WHERE (FILTER(cliente.nome, :nome) = TRUE "
						+ "AND FILTER(cliente.apelido, :apelido) = TRUE "
						+ "AND FILTER(cliente.cpf, :cpf) = TRUE "
						+ "AND FILTER(cliente.rg, :rg) = TRUE ) ")
	public List<Cliente> listByFilters( @Param("nome") String nome, @Param("apelido") String apelido,
			@Param("cpf") String cpf, @Param("rg") String rg );
	
}
