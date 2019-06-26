/**
 * Projeto Open source
 */
package cloudplaces.webapp.mappings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *Classe responsável pelo mapeamento das chamadas à API relacionadas com os 
 * utilizadores.
 */
//Todo create logs when pages are accessed.
@RestController
@Api(value = "User Resources", description = "Shows user resources")
public class UserResources {
    /**
     * Obtém uma lista de propriedades associadas a um utilizador
     * 
     * @param user_id Id do utilizador
     * @return Lista de Casas
     */
    @ApiOperation("Returns a list of properties")
    @GetMapping("get_wishlist/{user_id}")
    public ArrayList<Object> getWishlist(
            @PathVariable("user_id") final long user_id
            ){
        return new ArrayList<>();
    }
    
    /**
     * Adiciona uma propriedade à wishlist
     * 
     * @param user_id Id do utilizador
     * @param property_id Id da casa
     * @return True of False de acordo com o successo da query.
     */
    @ApiOperation("Inserts a property into a wishlist")
    @PostMapping("add_to_wishlist/{user_id}/{property_id}")
    public boolean addToWishlist(
            @PathVariable("user_id") final long user_id,
            @PathVariable("property_id") final long property_id
            ){
        return true;
    }
    
   /**
    * Remove uma propriedade da wishlist
    * 
    * @param user_id Id do utilizador
    * @param property_id Id da Casa
    * @return True ou false de acordo com o sucesso da query.
    */
    @ApiOperation("Deletes a property from a wishlist")
    @DeleteMapping("delete_from_wishlist/{user_id}/{property_id}")
    public boolean deleteFromWishlist(
            @PathVariable("user_id") final long user_id,
            @PathVariable("property_id") final long property_id
            ){
        return true;
    }
    
}