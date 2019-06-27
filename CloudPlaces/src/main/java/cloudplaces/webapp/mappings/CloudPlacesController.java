/**
 * Projeto Open source
 */

package cloudplaces.webapp.mappings;

import cloudplaces.webapp.databaseQueries.PropertyQueries;
import cloudplaces.webapp.databaseQueries.UserQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.User;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Esta classe é responsável por disponibilizar as chamadas
 * que permitiram obter as páginas web.
 */

//Todo create logs when pages are accessed.
@Controller
public class CloudPlacesController {
  
  Logger logger = Logger.getLogger(CloudPlacesController.class.getName());
  
  @Autowired
  PropertyQueries propertyQueries;
  
  @Autowired
  UserQueries userQueries;
  
  /**
   * Este método disponibiliza a página inicial da aplicação web.
   *
   *
   * @return Retorna a página de inicial da aplicação web.
   */
  @GetMapping("/login")
  public String loadIndex(Model model){
    return "index.html";
  }
  
  /**
   * Este método disponibiliza a página inicial de um utlizador que tenha realizado o login.
   *
   *
   * @return Retorna a página de inicial de um utilizador que tenha realizado o login.
   */
  @GetMapping("/getHomepage")
  public String loadHomepage(Model model){
    return "homepage.html";
  }
  
  /**
   * Este método disponibiliza a página com informações sobre a aplicação web.
   *
   *
   * @return Retorna a página com informações sobre a aplicação web.
   */
  @GetMapping("/getAbout")
  public String loadAbout(Model model){
    return "about.html";
  }
  
  /**
   * Este método disponibiliza a página de inscrição na aplicação web.
   *
   *
   * @return
   */
  @GetMapping("/signUp")
  public String getSignUp(){
    return "signup.html";
  }
  
  /**
   * Este método disponibiliza a página de inscrição na aplicação web.
   *
   *
   * @return Retorna a página de inscrição na aplicação web.
   */
  @PostMapping(path="/signUp",  consumes = "application/json")
  @ResponseBody
  public String postSignUp(@RequestBody Map<String,String> postPayload){
    
    logger.info("Received the following data: " + postPayload);
    boolean userAdded = userQueries.addUser(
            postPayload.get("name"),
            postPayload.get("email"),
            postPayload.get("pw"),
            postPayload.get("cellphone"),
            postPayload.get("photo").getBytes()
    );
    
    if(userAdded)
      return "login";
    else
      return "[Error] User was not added! A user with the same email already exists!";  
  }
  
  
  /**
   * Este método disponibiliza a página com a wishlist de um dado utilizador.
   *
   *
   * @return Retorna a página com a wishlist de um dado utilizador.
   */
  @GetMapping("/getWishlist")
  public String loadWishList(Model model){
    return "wishlist.html";
  }
  
  /**
   * Este método disponibiliza a página com informações sobre uma dada propriedade.
   *
   *
   * @return Retorna a página com informações sobre uma dada prpriedade.
   */
  @GetMapping("/getProperty")
  public String loadProperty(
          @RequestParam(name="id", required=true) final long id,
          Model model
  ){
    House tmp = (House) propertyQueries.getProperty(id);
    
    // Link house attributes to html template
    model.addAttribute("house", tmp);
    model.addAttribute("houseFeatures", tmp.getProperty_features().split("_"));
    //Link user attributes to html template
    model.addAttribute("user", tmp.getUser());
    
    return "single-property.html";
  }
  
  /**
   * Este método disponibiliza a página com informações sobre o utilizador em questão.
   *
   *
   * @return Retorna a página com informações sobre o utilizador em questão.
   */
  @GetMapping("/getProfile")
  public String loadProfile(Model model){
    return "profile.html";
  }
}