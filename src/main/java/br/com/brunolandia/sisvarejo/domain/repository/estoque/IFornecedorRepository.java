package br.com.brunolandia.sisvarejo.domain.repository.estoque;

import br.com.brunolandia.sisvarejo.domain.entity.estoque.Fornecedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFornecedorRepository extends CrudRepository<Fornecedor, Long> {

    /**
     * @return
     */
    @Query("SELECT new Fornecedor(fornecedor.id, fornecedor.razaoSocial, fornecedor.tipoPessoa, fornecedor.nomeFantasia, fornecedor.inscricaoEstadual, fornecedor.email, fornecedor.telefone, fornecedor.fax, fornecedor.cep, fornecedor.bairro, fornecedor.complemento, fornecedor.cnpj, fornecedor.uf, fornecedor.endereco, fornecedor.numero, fornecedor.cidade, fornecedor.transportadora, fornecedor.condicaoPagamento) "
            + "FROM Fornecedor fornecedor "
            + "WHERE FILTER(fornecedor.id, :id) = TRUE "
            + "AND FILTER(fornecedor.transportadora, :transportadora) = TRUE ")
    public Fornecedor findByIdTransportadora(@Param("id") Long id, @Param("transportadora") Boolean transportadora);

    /**
     * @param razaoSocial
     * @param nomeFantasia
     * @param telefone
     * @param cnpj
     * @return
     */
    @Query("SELECT new Fornecedor(fornecedor.id, fornecedor.razaoSocial, fornecedor.tipoPessoa, fornecedor.nomeFantasia, fornecedor.inscricaoEstadual, fornecedor.email, fornecedor.telefone, fornecedor.fax, fornecedor.cep, fornecedor.bairro, fornecedor.complemento, fornecedor.cnpj, fornecedor.uf, fornecedor.endereco, fornecedor.numero, fornecedor.cidade, fornecedor.transportadora, fornecedor.condicaoPagamento) "
            + "FROM Fornecedor fornecedor "
            + "WHERE FILTER(fornecedor.razaoSocial, :razaoSocial) = TRUE "
            + "AND FILTER(fornecedor.nomeFantasia, :nomeFantasia) = TRUE "
            + "AND FILTER(fornecedor.telefone, :telefone) = TRUE "
            + "AND FILTER(fornecedor.transportadora, :transportadora) = TRUE "
            + "AND FILTER(fornecedor.cnpj, :cnpj) = TRUE ")
    public List<Fornecedor> listByFilters(@Param("razaoSocial") String razaoSocial, @Param("nomeFantasia") String nomeFantasia, @Param("telefone") String telefone, @Param("cnpj") String cnpj, @Param("transportadora") Boolean transportadora);

}
