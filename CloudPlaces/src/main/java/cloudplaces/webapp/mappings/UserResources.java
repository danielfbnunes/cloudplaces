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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

  /**
   * Método responsável por realizar a autenticação do utilizador.
   * @param credentials 
   * @return 
   */
  @ApiOperation("Authenticate user")
  @PostMapping("api/authenticate")
  public Object authenticateUser(@RequestBody Map<String, String> credentials) {
    return query.authenticateUser(credentials.get("email"), credentials.get("password"));
  }

  @ApiOperation("Inserts a user in database")
  @PostMapping("api/add_user")
  public Object addUser(@RequestBody User user) {
    User u = query.addUser(user.getName(), user.getEmail(), user.getPw(), user.getCellphone(), user.getPhoto());
    if(u != null)
        return u;
    error.put(errorMessage, "User with the given email already in database");
    return error;
  }

  @ApiOperation("Get a user from database")
  @GetMapping("api/get_user")
  public Object getUser(@RequestParam("email") final String email) {
    User u = query.getUser(email);
    if(u != null)
        return u;
    error.put(errorMessage, "User not found");
    return error;
  }

  /**
   * Obtém uma lista de propriedades associadas a um utilizador
   * 
   * @param user_email
   * @param user_id Id do utilizador
   * @return Lista de Casas
   */
  @ApiOperation("Returns a list of properties")
  @GetMapping("api/get_wishlist")
  public Object getWishlist(@RequestParam("user_email") final String user_email) {
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
   * @param values
   * @param user_id Id do utilizador
   * @param property_id Id da casa
   * @return True of False de acordo com o successo da query.
   */
  @ApiOperation("Inserts a property into a wishlist")
  @PostMapping("api/add_to_wishlist")
  public Object addToWishlist(@RequestBody Map<String, String> values) {
    Wishlist w = query.addToWishlist(values.get("user_email"), Long.parseLong(values.get("property_id")));
    if (w != null){
      return w;
    }
    error.put(errorMessage, "House or User in question not found");
    return error;
  }

 /**
  * Remove uma propriedade da wishlist
  * 
  * @param email  
  * @param property_id Id da Casa
  * @return True ou false de acordo com o sucesso da query.
  */
  @ApiOperation("Deletes a property from a wishlist")
  @DeleteMapping("api/delete_from_wishlist")
  public Object deleteFromWishlist(
      @RequestParam(name="email", required=true) String email,
      @RequestParam(name="property_id", required=true) long property_id
  ) {
    boolean checkVal = query.deleteFromWishlist(email, property_id);
    error.put(errorMessage, "House or User in question not found");
    return (checkVal) ? true : error;
  }
}
