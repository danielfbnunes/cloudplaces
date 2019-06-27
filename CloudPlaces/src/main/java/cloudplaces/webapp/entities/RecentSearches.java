/**
 * Projeto Open source
 */
package cloudplaces.webapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representa uma procura realizada por um utilizador.
 * 
 */
@Entity
public class RecentSearches {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long searchId;

    public RecentSearches() {
        //Method is empty because JPA needs an empty constructor
    }

    public Long getSearchId() {
        return searchId;
    }
    
}