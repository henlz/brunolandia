package br.com.brunolandia.sisvarejo.domain.repository.caracteristicas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brunolandia.sisvarejo.domain.entity.caracteristicas.Cor;

public interface ICorRepository extends JpaRepository<Cor, Long>
{

	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public Cor findByCodigo( String codigo );
	
	/**
	 * 
	 * @param codigo
	 * @param cor
	 * @return
	 */
	@Query("SELECT new Cor(cor.id, cor.nome, cor.codigo) "
			+ "FROM Cor cor "
			+ "WHERE FILTER(cor.nome, :cor) = TRUE "
			+ "AND FILTER(cor.codigo, :codigo) = TRUE ")
	public List<Cor> listByFilters( @Param("codigo" ) String codigo, @Param("cor") String cor);

}
