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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe responável pelo mapeamento da API para queries sobre as
 * propriedades.
 */
//Todo create logs when pages are accessed.
@RestController
@Api(value="Property Resources", description = "Shows property resources")
public class PropertyResources 
{
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
    @GetMapping("get_properties/{name}/{location}/{radius}/{price}/{n_rooms}") 
    public ArrayList<Object> GetProperties(
            @PathVariable("name") final String name,
            @PathVariable("location") final String location,
            @PathVariable("radius") final float radius,
            @PathVariable("price") final float price,
            @PathVariable("n_rooms") final int n_rooms
            ){
        return new ArrayList<>();
    }
    
    /**
     * Retorna a propriedade correspondente ao id recebido
     * 
     * @param id Identificador da propriedade.
     * @return 
     */
    @ApiOperation("Returns a property")
    @GetMapping("get_property/{id}")
    public Object GetProperty(
            @PathVariable("id") final long id
            )
    {return new Object();}
    
    /**
     * Adiciona uma nova propriedade de acordo com os valores recebidos.
     * 
     * @param name Nome da casa
     * @param location Localização da Casa
     * @param price Preço de arrendamento
     * @param n_rooms Número de quartos
     * @return True ou False mediante o successo ou não da adição da propriedade
     */
    @ApiOperation("Adds a property") //TODO Adicionar o id do utilizador que a criou (Não seria post?)
    @PostMapping("add_property/{name}/{location}/{price}/{n_rooms}")
    public boolean AddProperty(
            @PathVariable("name") final String name,
            @PathVariable("location") final String location,
            @PathVariable("price") final float price,
            @PathVariable("n_rooms") final int n_rooms
            )
    {return true;}
    
    
    /**
     * Edita a propriedade com o id correspondente
     * 
     * @param id corresponde ao id da propriedade
     */
    @ApiOperation("Edits a property")
    @PutMapping("edit_property/{id}")
    public boolean EditProperty(
            @PathVariable("id") final long id
            )
    {return true;}
    
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
    @PostMapping("add_review/{property_id}/{user_id}{review}/{cotation}") 
    public boolean AddReview(
            @PathVariable("user_id") final long user_id,
            @PathVariable("property_id") final long property_id,
            @PathVariable("review") final String review,
            @PathVariable("cotation") final String cotation
            )
    {return true;}
    
    /**
     * Edita uma dada review
     * 
     * @param review_id Id da review
     * @return True ou False consoante o resultado.
     */
    @ApiOperation("Edits a review")
    @PutMapping("edit_review/{review_id}")
    public boolean EditReview(
            @PathVariable("review_id") final long review_id
            )
    {return true;}
    
    /**
     * Remove uma review.
     * 
     * @param review_id Id da review
     * @return True ou False consoante o resultado.
     */
    @ApiOperation("Deletes a review")
    @DeleteMapping("delete_review/{review_id}")
    public boolean DeleteReview(
            @PathVariable("review_id") final long review_id
            ){
        return true;
    }
    
    
    
    
    
    
}
