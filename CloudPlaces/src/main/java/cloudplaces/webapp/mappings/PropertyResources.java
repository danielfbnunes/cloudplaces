/**
 * Projeto Open source
 */
package cloudplaces.webapp.mappings;

import cloudplaces.webapp.databaseQueries.PropertyQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.PropertyRepository;
import cloudplaces.webapp.entities.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
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
@Api(value = "Property Resources", description = "Shows property resources")
public class PropertyResources {
    
    @Autowired
    PropertyQueries query;
    
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
    public List<House> getProperties(
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
        return query.getProperties(name, location, min_price, max_price, min_n_rooms, max_n_rooms, min_hab_space, max_hab_space, availability);
    }    
    
    /**
     * Retorna a propriedade correspondente ao id recebido
     * 
     * @param id Identificador da propriedade.
     * @return 
     */
    @ApiOperation("Returns a property")
    @GetMapping("api/get_property/{id}")
    public House getProperty(
            @PathVariable("id") final long id
            ){
        return query.getProperty(id);
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
    @PostMapping("api/add_property/{name}/{location}/{price}/{n_rooms}/{user_id}/{hab_space}/{n_bathrooms}/{garage}/{description}/{property_features}/{availability}")
    public House addProperty(
            @PathVariable("name") final String name,
            @PathVariable("location") final String location,
            @PathVariable("price") final float price,
            @PathVariable("n_rooms") final int n_rooms,
            @PathVariable("user_id") final long user_id,
            @PathVariable("hab_space") final int hab_space,
            @PathVariable("n_bathrooms") final int n_bathrooms,
            @PathVariable("garage") final int garage,
            @PathVariable("description") final String description,
            @PathVariable("property_features") final String property_features,
            @PathVariable("availability") final int availability
            ){
        return query.addProperty(name, location, price, n_rooms, user_id, hab_space, n_bathrooms, garage, description, property_features, availability);
    }
    
    /**
     * Edita a propriedade com o id correspondente
     * 
     * @param id corresponde ao id da propriedade
     */
    @ApiOperation("Edits a property")
    @PutMapping("api/edit_property")
    public House editProperty(@RequestBody House house) {
        return query.editProperty(house.getName(), house.getAddress(), house.getPrice(), house.getN_rooms(), house.getUser().getId(), house.getHab_space(), house.getN_bathrooms(), house.getGarage(), house.getDescription(), house.getProperty_features(), house.getAvailability());
    }
    
    /**
     * Remove uma propriedade.
     * 
     * @param property_id Id da propriedade
     */
    @ApiOperation("Deletes a review")
    @DeleteMapping("api/delete_review")
    public void deleteProperty(@RequestParam(name = "houseId", required = true) long houseId) {
        query.removeProperty(houseId);
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
    @PostMapping("api/add_review/{property_id}/{user_id}{review}/{cotation}") 
    public boolean addReview(
            @PathVariable("user_id") final long user_id,
            @PathVariable("property_id") final long property_id,
            @PathVariable("review") final String review,
            @PathVariable("cotation") final String cotation
            ){
        return query.addReview(user_id, property_id, review, cotation);
    }
    
    /**
     * Edita uma dada review
     * 
     * @param review_id Id da review
     * @return True ou False consoante o resultado.
     */
    @ApiOperation("Edits a review")
    @PutMapping("api/edit_review/{review_id}")
    public boolean editReview(
            @PathVariable("review_id") final long review_id
            ){
        return query.editReview(review_id);
    }
    
    /**
     * Remove uma review.
     * 
     * @param review_id Id da review
     * @return True ou False consoante o resultado.
     */
    @ApiOperation("Deletes a review")
    @DeleteMapping("api/delete_review/{review_id}")
    public boolean deleteReview(
            @PathVariable("review_id") final long review_id){
        return query.deleteReview(review_id);
    }
}
