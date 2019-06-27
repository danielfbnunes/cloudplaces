/**
 * Projeto Open source
 */

package cloudplaces.webapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
    
    @Lob
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

    public int getQuotation() {
        return quotation;
    }

    public void setQuotation(int quotation) {
        this.quotation = quotation;
    }
}
