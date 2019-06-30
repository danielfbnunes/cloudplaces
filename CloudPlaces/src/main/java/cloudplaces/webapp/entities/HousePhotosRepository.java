package cloudplaces.webapp.entities;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de acesso às fotos das propriedades presentes na base de dados.
 * 
 */
@Repository
@Transactional
public interface HousePhotosRepository extends JpaRepository<HousePhotos, Long> {
  
}
