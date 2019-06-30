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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
   * @param min_price
   * @param max_price
   * @param min_n_rooms
   * @param max_n_rooms
   * @param min_hab_space
   * @param max_hab_space
   * @param availability
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
  ) {
    List<House> h = query.getProperties(name, location, min_price, max_price, min_n_rooms, max_n_rooms, min_hab_space, max_hab_space, availability);
    
    if(!h.isEmpty()) {
      return h;
    }
    
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
  @GetMapping("api/get_property")
  public Object getProperty(@RequestParam("id") final long id) {
    House h = query.getProperty(id);
    
    if (h != null) {
      return h;
    }
    
    error.put(errorMessage, "No house with the id = " + id);
    return error;
  }

  /**
   * Adiciona uma nova propriedade de acordo com os valores recebidos.
   * 
   * @param house
   * @return True ou False mediante o successo ou não da adição da propriedade
   */
  @ApiOperation("Adds a property")
  @PostMapping("api/add_property")
  public House addProperty(@RequestBody HousePOJO house) {
    return query.addProperty(house.getName(), house.getAddress(), house.getPrice(), house.getNRooms(), house.getUser().getEmail(), house.getHabSpace(), house.getNBathrooms(), house.getGarage(), house.getDescription(), house.getPropertyFeatures(), house.getAvailability(), house.getPhotos());
  }

  /**
   * Edita a propriedade com o id correspondente
   * 
   * @param house
   * @return 
   */
  @ApiOperation("Edits a property")
  @PutMapping("api/edit_property")
  public House editProperty(@RequestBody HousePOJO house) {
    return query.editProperty(house.getName(), house.getAddress(), house.getPrice(), house.getNRooms(), house.getUser().getEmail(), house.getHabSpace(), house.getNBathrooms(), house.getGarage(), house.getDescription(), house.getPropertyFeatures(), house.getAvailability(), house.getPhotos());
  }

  /**
   * Remove uma propriedade.
   * 
   * @param name
   * @return 
   */
  @ApiOperation("Deletes a property")
  @DeleteMapping("api/delete_property")
  public Object deleteProperty(@RequestParam(name = "name", required = true) String name) {
    if (query.removeProperty(name) != 0) {
      return "true";
    }

    error.put(errorMessage, "House to remove not found");
    return error;
  }

  /**
   * Adiciona uma review
   * 
   * 
   * @param reviewInfo
   * @return 
   */
  @ApiOperation("Adds a review")
  @PostMapping("api/add_review")
  public Object addReview(@RequestBody Map<String, Object> reviewInfo) {
    Review review = (Review) reviewInfo.get("review");
    review = query.addReview((String) reviewInfo.get("user_email"), (Long) reviewInfo.get("property_id"), review.getComment(), review.getQuotation());
    
    if (review != null) {
      return review;
    }
    
    error.put(errorMessage, "House or User in question not found");
    return error;
  }

  /**
   * Edita uma dada review
   * 
   * @param review
   * @return True ou False consoante o resultado.
   */
  @ApiOperation("Edits a review")
  @PutMapping("api/edit_review")
  public Object editReview(@RequestBody Review review) {
    Review r = query.editReview(review.getReviewId(), review.getComment(), review.getQuotation());
    
    if (r != null) {
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
  @ApiOperation("Deletes a review")//TODO Fix api call
  @DeleteMapping("api/delete_review")
  public Object deleteReview(@RequestParam("review_id") final long review_id) {
    if(query.deleteReview(review_id)) {
      return true;
    }
    
    error.put(errorMessage, "Review not found");
    return error;
  }
}
