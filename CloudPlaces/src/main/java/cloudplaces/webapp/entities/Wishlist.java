/**
 * Projeto Open source
 */
package cloudplaces.webapp.entities;

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
        //Method is empty because JPA needs an empty constructor
    }

    public Long getWhishListId() {
        return whishListId;
    }

    public void setWhishListId(Long whishListId) {
        this.whishListId = whishListId;
    }
    
}
