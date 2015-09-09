package br.com.brunolandia.sisvarejo.domain.repository.estoque;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Fornecedor;

public interface IFornecedorRepository extends JpaRepository<Fornecedor, Long>
{

	/**
	 * 
	 * @param ativo
	 * @return
	 */
	public List<Fornecedor> findByAtivo( Boolean ativo );

	/**
	 * 
	 * @param razaoSocial
	 * @param nomeFantasia
	 * @param telefone
	 * @param cnpj
	 * @return
	 */
	@Query("SELECT new Fornecedor(fornecedor.id, fornecedor.razaoSocial, fornecedor.nomeFantasia, fornecedor.representante, fornecedor.email, fornecedor.telefone, fornecedor.cep, fornecedor.bairro, fornecedor.complemento, fornecedor.cnpj, fornecedor.site, fornecedor.endereco, fornecedor.cidade, fornecedor.ativo, fornecedor.numero) "
			+ "FROM Fornecedor fornecedor "
			+ "WHERE (FILTER(fornecedor.razaoSocial, :razaoSocial) = TRUE "
			+ "AND FILTER(fornecedor.nomeFantasia, :nomeFantasia) = TRUE "
			+ "AND FILTER(fornecedor.representante, :representante) = TRUE "
			+ "AND FILTER(fornecedor.telefone, :telefone) = TRUE "
			+ "AND FILTER(fornecedor.cnpj, :cnpj) = TRUE ) ")
	public List<Fornecedor> listByFilters( @Param("razaoSocial") String razaoSocial, @Param("nomeFantasia") String nomeFantasia, 
			@Param("telefone") String telefone, @Param("cnpj") String cnpj, @Param("representante") String representante );

}
