package cloudplaces.restapi.db;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String pw;
    private int cellphone;
    
    @OneToMany
    @JoinColumn
    private List<House> rentals;

    public User(){
    }
    
    public User(String name, String email, String pw, int cellphone) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.cellphone = cellphone;
    }
}
