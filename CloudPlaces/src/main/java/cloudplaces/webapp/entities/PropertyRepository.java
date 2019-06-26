/**
 * Projeto Open source
 */
package cloudplaces.webapp.entities;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de acesso às propriedades presentes na base de dados.
 * 
 */
@Repository
@Transactional
public interface PropertyRepository extends JpaRepository<House, Long>  {
    
}