/**
 * Projeto Open source
 */
package cloudplaces.webapp.mappings;

import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.Review;
import cloudplaces.webapp.pojo.HousePOJO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responável pelo mapeamento da API para queries sobre as
 * propriedades.
 */
//Todo create logs when pages are accessed.
@RestController
@Api(value = "Property Resources")
public class PropertyResources {
    
    Map<String, String> error = new HashMap<>();

    @Autowired
    PropertyQueries query;
    
    private final String errorMessage = "Error";
    
    /**
     * Retorna uma lista de propriedades de acordo com os parâmetros definidos.
     * 
     * 
     * @param name Nome da propriedade
     * @param location Local onde se encontra a propriedade
     * @param radius Raio de exatidão da localização
     * @param price Preço de arrendamento
     * @param n_rooms Número de quartos
     * @return Lista de Casas.
     */
    @ApiOperation("Returns a list of properties")
    @GetMapping("api/get_properties")
    @ResponseBody
    public Object getProperties(
            @RequestParam(required = false) final String name,
            @RequestParam(required = false) final String location,
            @RequestParam(required = false) final Float min_price,
            @RequestParam(required = false) final Float max_price,
            @RequestParam(required = false) final Integer min_n_rooms,
            @RequestParam(required = false) final Integer max_n_rooms,
            @RequestParam(required = false) final Integer min_hab_space,
            @RequestParam(required = false) final Integer max_hab_space,
            @RequestParam(required = false) final Integer availability
            ){
        List<House> h = query.getProperties(name, location, min_price, max_price, min_n_rooms, max_n_rooms, min_hab_space, max_hab_space, availability);
        if(!h.isEmpty())
            return h;
        error.put(errorMessage, "No houses found");
        return error;
    }    
    
    /**
     * Retorna a propriedade correspondente ao id recebido
     * 
     * @param id Identificador da propriedade.
     * @return 
     */
    @ApiOperation("Returns a property")
    @GetMapping("api/get_property/{id}")
    public Object getProperty(@PathVariable("id") final long id){
      House h = query.getProperty(id);
      if(h != null)
            return h;
        error.put(errorMessage, "No house with the id = " + id);
        return error;
    }
    
    /**
     * Adiciona uma nova propriedade de acordo com os valores recebidos.
     * 
     * @param name Nome da casa
     * @param location Localização da Casa
     * @param price Preço de arrendamento
     * @param n_rooms Número de quartos
     * @return True ou False mediante o successo ou não da adição da propriedade
     */
    @ApiOperation("Adds a property")
    @PostMapping("api/add_property")
    public House addProperty(@RequestBody HousePOJO house){
        return query.addProperty(house.getName(), house.getAddress(), house.getPrice(), house.getNRooms(), house.getUser().getEmail(), house.getHabSpace(), house.getNBathrooms(), house.getGarage(), house.getDescription(), house.getPropertyFeatures(), house.getAvailability()/*, house.getPhotos(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()*/);
    }
    
    /**
     * Edita a propriedade com o id correspondente
     * 
     * @param id corresponde ao id da propriedade
     */
    @ApiOperation("Edits a property")
    @PutMapping("api/edit_property")
    public House editProperty(@RequestBody HousePOJO house) {
        return query.editProperty(house.getName(), house.getAddress(), house.getPrice(), house.getNRooms(), house.getUser().getEmail(), house.getHabSpace(), house.getNBathrooms(), house.getGarage(), house.getDescription(), house.getPropertyFeatures(), house.getAvailability()/*, house.getPhotos(), house.getWishes(), house.getReviews(), house.getSearches()*/);
    }
    
    /**
     * Remove uma propriedade.
     * 
     * @param property_id Id da propriedade
     */
    @ApiOperation("Deletes a property")
    @DeleteMapping("api/delete_property")
    public Object deleteProperty(@RequestParam(name = "houseId", required = true) long houseId) {
        if (query.removeProperty(houseId))
          return true;
        error.put(errorMessage, "House to remove not found");
        return error;
    }
    
    /**
     * Adiciona uma review
     * 
     * 
     * @param user_id Id do utilizador que fez a review
     * @param property_id Id da casa que recebeu a review
     * @param review Comentário realizado pelo utilizador
     * @param cotation Cotação atribuída de 0-5
     * 
     * @return 
     */
    @ApiOperation("Adds a review")
    @PostMapping("api/add_review/{property_id}/{user_email}/{review}/{quotation}") 
    public Object addReview(
            @PathVariable("user_id") final String user_email,
            @PathVariable("property_id") final long property_id,
            @PathVariable("review") final String review,
            @PathVariable("quotation") final int quotation
            ){
      Review r = query.addReview(user_email, property_id, review, quotation);
      if (r != null){
        return r;
      }
      error.put(errorMessage, "House or User in question not found");
      return error;
    }
    
    /**
     * Edita uma dada review
     * 
     * @param review_id Id da review
     * @return True ou False consoante o resultado.
     */
    @ApiOperation("Edits a review")
    @PutMapping("api/edit_review/{review_id}/{comment}/{quotation}")
    public Object editReview(
        @PathVariable("review_id") final long review_id,
        @PathVariable("comment") final String comment,
        @PathVariable("quotation") final int quotation
            ){
      Review r = query.editReview(review_id, comment, quotation);
      if (r != null){
        return r;
      }
      error.put(errorMessage, "Review not found");
      return error;
    }
    
    /**
     * Remove uma review.
     * 
     * @param review_id Id da review
     * @return True ou False consoante o resultado.
     */
    @ApiOperation("Deletes a review")
    @DeleteMapping("api/delete_review/{review_id}")
    public Object deleteReview(
            @PathVariable("review_id") final long review_id){
      if(query.deleteReview(review_id)){
        return true;
      }
      error.put(errorMessage, "Review not found");
      return error;
    }
}
