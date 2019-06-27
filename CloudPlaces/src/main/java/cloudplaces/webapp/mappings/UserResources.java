/**
 * Projeto Open source
 */
package cloudplaces.webapp.mappings;

import cloudplaces.webapp.databaseQueries.UserQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responsável pelo mapeamento das chamadas à API relacionadas com os 
 * utilizadores.
 */
//Todo create logs when pages are accessed.
@RestController
@Api(value = "User Resources", description = "Shows user resources")
public class UserResources {
    
    @Autowired
    UserQueries query;
    
    @ApiOperation("Inserts a user in database")
    @PostMapping("api/add_user/{name}/{email}/{pw}/{cellphone}/{photo}")
    public User addUser(
            @PathVariable("name") final String name,
            @PathVariable("email") final String email,
            @PathVariable("pw") final String pw,
            @PathVariable("cellphone") final String cellphone,
            @PathVariable("photo") final byte[] photo
            ){
        return query.addUser(name, email, pw, cellphone, photo);
    }
    
    @ApiOperation("Get a user from database")
    @GetMapping("api/get_user/{user_id}")
    public User getUser(
            @PathVariable("user_id") final long user_id
            ){
        return query.getUser(user_id);
    }
    
    /**
     * Obtém uma lista de propriedades associadas a um utilizador
     * 
     * @param user_id Id do utilizador
     * @return Lista de Casas
     */
    @ApiOperation("Returns a list of properties")
    @GetMapping("api/get_wishlist/{user_id}")
    public ArrayList<Object> getWishlist(
            @PathVariable("user_id") final long user_id
            ){
        return query.getWishlist();
    }
    
    /**
     * Adiciona uma propriedade à wishlist
     * 
     * @param user_id Id do utilizador
     * @param property_id Id da casa
     * @return True of False de acordo com o successo da query.
     */
    @ApiOperation("Inserts a property into a wishlist")
    @PostMapping("api/add_to_wishlist/{user_id}/{property_id}")
    public boolean addToWishlist(
            @PathVariable("user_id") final long user_id,
            @PathVariable("property_id") final long property_id
            ){
        return query.addToWishlist();
    }
    
   /**
    * Remove uma propriedade da wishlist
    * 
    * @param user_id Id do utilizador
    * @param property_id Id da Casa
    * @return True ou false de acordo com o sucesso da query.
    */
    @ApiOperation("Deletes a property from a wishlist")
    @DeleteMapping("api/delete_from_wishlist/{user_id}/{property_id}")
    public boolean deleteFromWishlist(
            @PathVariable("user_id") final long user_id,
            @PathVariable("property_id") final long property_id
            ){
        return query.deleteFromWishlist();
    }
    
}
