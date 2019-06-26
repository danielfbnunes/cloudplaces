/**
 * Projeto Open source
 */
package cloudplaces.webapp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representa a lista de casas desejadas por um utilizador.
*/
@Entity
public class Wishlist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long whishListId;

    public Wishlist() {
    }

    public Long getWhishListId() {
        return whishListId;
    }
}
