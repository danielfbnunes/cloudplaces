/**
 * Projeto Open source
 */

package cloudplaces.webapp.mappings;

import cloudplaces.webapp.databaseQueries.PropertyQueries;
import cloudplaces.webapp.entities.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Esta classe é responsável por disponibilizar as chamadas
 * que permitiram obter as páginas web.
 */

//Todo create logs when pages are accessed.
@Controller
public class CloudPlacesController {
  @Autowired
  private PropertyQueries propertyQueries;
  
  /**
   * Método responsável por enviar o menu html dinâmico.
   * 
   * @param model
   * @return -> Página html com o código do menu.
   */
  @GetMapping("/getHeader")
  public String loadHeader(Model model) {
    return "header.html";
  }
  
  /**
   * Este método disponibiliza a página inicial da aplicação web.
   * 
   * 
   * @return Retorna a página de inicial da aplicação web.
   */
  @GetMapping("/getIndex")
  public String loadIndex(Model model) {
    return "index.html";
  }

  /**
   * Este método disponibiliza a página inicial de um utlizador que tenha realizado o login.
   * 
   * 
   * @return Retorna a página de inicial de um utilizador que tenha realizado o login.
   */
  @GetMapping("/getHomepage")
  public String loadHomepage(Model model) {
    return "homepage.html";
  }

  /**
   * Este método disponibiliza a página com informações sobre a aplicação web.
   * 
   * 
   * @return Retorna a página com informações sobre a aplicação web.
   */    
  @GetMapping("/getAbout")
  public String loadAbout(Model model) {
    return "about.html";
  }

  /**
   * Este método disponibiliza a página de inscrição na aplicação web.
   * 
   * 
   * @return Retorna a página de inscrição na aplicação web.
   */     
  @GetMapping("/getSignUp")
  public String loadSignUp(Model model) {
    return "signup.html";
  }


  /**
   * Este método disponibiliza a página com a wishlist de um dado utilizador.
   * 
   * 
   * @return Retorna a página com a wishlist de um dado utilizador.
   */     
  @GetMapping("/getWishlist")
  public String loadWishList(Model model) {
    return "wishlist.html";
  }

  /**
   * Este método disponibiliza a página com informações sobre uma dada propriedade.
   * 
   * 
   * @return Retorna a página com informações sobre uma dada prpriedade.
   */     
  @GetMapping("/getProperty")
  public String loadProperty(Model model) {
    return "single-property.html";
  }

  /**
   * Este método disponibiliza a página com informações sobre o utilizador em questão.
   * 
   * 
   * @return Retorna a página com informações sobre o utilizador em questão.
   */     
  @GetMapping("/getProfile")
  public String loadProfile(Model model) {
    return "profile.html";
  }
  
  @GetMapping("/getMyProperties")
  public String loadMyProperties(Model model) {
    return "properties.html";
  }

  /**
   * Método responsável por adicionar uma nova casa na base de dados, de forma a criar um novo anúncio.
   * 
   * @param house -> Nova casa a ser criada do tipo House.
   */
  @PostMapping(path = "/createAd")
  public void createAd(@RequestBody House house) {
    propertyQueries.addProperty(house.getName(), house.getAddress(), house.getPrice(), house.getN_rooms(), house.getUser().getId(), house.getHab_space(), house.getN_bathrooms(), house.getGarage(), house.getDescription(), house.getProperty_features(), house.getAvailability());
  }

  /**
   * Método responsável por permitir editar uma propriedade que já exista.
   * 
   * @param house -> Nova casa a ser criada do tipo House.
   */
  @PutMapping(path = "/editAd")
  public void editAd(@RequestBody House house) {
    propertyQueries.editProperty(house.getName(), house.getAddress(), house.getPrice(), house.getN_rooms(), house.getUser().getId(), house.getHab_space(), house.getN_bathrooms(), house.getGarage(), house.getDescription(), house.getProperty_features(), house.getAvailability());
  }

  /**
   * Método responsável por remover um propriedade que já exista.
   * 
   * @param houseId -> Id da casa que pretendemos remover.
   */
  @DeleteMapping(path = "/removeAd")
  public void removeAd(@RequestParam(name = "houseId", required = true) long houseId) {
    propertyQueries.removeProperty(houseId);
  }
}