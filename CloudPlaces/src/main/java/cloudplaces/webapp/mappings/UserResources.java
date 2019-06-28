/**
 * Projeto Open source
 */
package cloudplaces.webapp.mappings;

import cloudplaces.webapp.database_queries.UserQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.entities.Wishlist;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Api(value = "User Resources")
public class UserResources {
    
    Map<String, String> error = new HashMap<>();
    
    @Autowired
    UserQueries query;
    
    private final String errorMessage = "Error";
    
    @ApiOperation("Inserts a user in database")
    @PostMapping("api/add_user/{name}/{email}/{pw}/{cellphone}/{photo}")
    public Object addUser(
            @PathVariable("name") final String name,
            @PathVariable("email") final String email,
            @PathVariable("pw") final String pw,
            @PathVariable("cellphone") final String cellphone,
            @PathVariable("photo") final String photo
            ){
        User u = query.addUser(name, email, pw, cellphone, photo);
        if(u != null)
            return u;
        error.put(errorMessage, "User with the given email already in database");
        return error;
    }
    
    @ApiOperation("Get a user from database")
    @GetMapping("api/get_user/{email}")
    public Object getUser(
            @PathVariable("email") final String email
            ){
        User u = query.getUser(email);
        if(u != null)
            return u;
        error.put(errorMessage, "User not found");
        return error;
    }
    
    /**
     * Obtém uma lista de propriedades associadas a um utilizador
     * 
     * @param user_id Id do utilizador
     * @return Lista de Casas
     */
    @ApiOperation("Returns a list of properties")
    @GetMapping("api/get_wishlist/{user_email}")
    public Object getWishlist(
            @PathVariable("user_email") final String user_email
            ){
      List<House> h = query.getWishlist(user_email);
      if (!h.isEmpty()){
        return h;
      }
      error.put(errorMessage, "User not found with wishlist");
      return error;
    }
    
    /**
     * Adiciona uma propriedade à wishlist
     * 
     * @param user_id Id do utilizador
     * @param property_id Id da casa
     * @return True of False de acordo com o successo da query.
     */
    @ApiOperation("Inserts a property into a wishlist")
    @PostMapping("api/add_to_wishlist/{user_email}/{property_id}")
    public Object addToWishlist(
            @PathVariable("user_id") final String user_email,
            @PathVariable("property_id") final long property_id
            ){
      Wishlist w = query.addToWishlist(user_email, property_id);
      if (w != null){
        return w;
      }
      error.put(errorMessage, "House or User in question not found");
      return error;
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
