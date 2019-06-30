/**
 * Projeto Open source
 */
package cloudplaces.webapp.entities;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interface de acesso às propriedades presentes na base de dados.
 * 
 */
@Repository
@Transactional
public interface PropertyRepository extends JpaRepository<House, Long> {
  @Query("SELECT h FROM House h WHERE h.name = ?1")
  Optional<House> findByName(String name);
  
  @Query("DELETE FROM House h WHERE h.name = ?1")
  Optional<House> deleteByName(String name);
}