/**
 * Projeto Open source
 */

package cloudplaces.webapp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Esta classe representa uma avaliação feita por um utilizador
 * a uma casa.
 * 
 */
@Entity
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;
    
    private String comment;
    
    private int quotation;

    public Review() {
    }

    public Review(String comment, int quotation) {
        this.comment = comment;
        this.quotation = quotation;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
