 /**
  * Projeto Open source
  */

package cloudplaces.webapp.mappings;



import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.database_queries.UserQueries;

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
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Esta classe é responsável por disponibilizar as chamadas
 * que permitiram obter as páginas web.
 */

//Todo create logs when pages are accessed.
@Controller
@SessionAttributes("currentUser")
public class CloudPlacesController {
  
  // Every time the controller starts, the user will be null
  @ModelAttribute("currentUser")
  public User currentUser() {
    return null;
  }
  
  Logger logger = Logger.getLogger(CloudPlacesController.class.getName());
  
  @Autowired
          PropertyQueries propertyQueries;
  
  @Autowired
          UserQueries userQueries;
  
  /**
   * Este método disponibiliza a página inicial da aplicação web.
   *
   *
   * @return página de login
   */
  @GetMapping("/login")
  public String login(Model model, @ModelAttribute("currentUser") User currentUser){
    
    //check if user is logged in
    if (userLoggedIn(currentUser)) {
      return "index";
    }
    
    model.addAttribute("user", new User());
    model.addAttribute("error", false);
    return "login.html";
  }
  
  
  /**
   * Este método disponibiliza a página inicial da aplicação web.
   *
   *
   * @return Retorna a página de inicial da aplicação web.
   */
  @PostMapping("/login")
  public String login(@ModelAttribute User user, Model model, @ModelAttribute("currentUser") User currentUser){
    logger.info("The following is user is trying to login: " + user.getName());
    
    //check if user is logged in
    if (userLoggedIn(currentUser)) {
      return "index";
    }

    User u = userQueries.authenticateUser(user.getEmail(), user.getPw());

    if(u == null){
      model.addAttribute("error", true);
      return "login.html";
    }
    else{
      currentUser = u;
      return "redirect:/";
    }
  }
  
  /**
   * Este método disponibiliza a página inicial de um utlizador que tenha realizado o login.
   *
   *
   * @return Retorna a página de inicial de um utilizador que tenha realizado o login.
   */
  @GetMapping("/")
  public String propertiesPage(Model model, @ModelAttribute("currentUser") User currentUser){
    //check if user is logged in
    if (userLoggedIn(currentUser)) {
      return "redirect:/";
    }
    return "redirect:/login";
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
    User addedUser = userQueries.addUser(
            postPayload.get("name"),
            postPayload.get("email"),
            postPayload.get("pw"),
            postPayload.get("cellphone"),
            postPayload.get("photo").getBytes()
    );
    
    if(addedUser != null)
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
          @ModelAttribute("currentUser") User currentUser,
          Model model
  ){
    //check if user is logged in
    if (!userLoggedIn(currentUser)) {
      return "redirect:/login";
    }
    
    House tmp = propertyQueries.getProperty(id);
    
    // Link house attributes to html template
    model.addAttribute("house", tmp);
    model.addAttribute("houseFeatures", tmp.getPropertyFeatures().split("_"));
    //Link user attributes to html template
    model.addAttribute("user", tmp.getUser());
    
    //convert byte images do b64
    model.addAttribute("userImage" ,new String(tmp.getUser().getPhoto()));
    
    String[] houseImages = new String[tmp.getPhotos().size()];
    for (int i=0; i<tmp.getPhotos().size(); i++)
      houseImages[i] = new String(tmp.getPhotos().get(0).getPhoto());
    
    model.addAttribute("houseImages" ,houseImages);
    
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
  
  
   /**
   * Este método apaga a user session
   *
   *
   * @return página de login
   */
  @GetMapping("/logout")
  public String logout(@ModelAttribute("currentUser") User currentUser){
    currentUser = null;
    return "redirect:/login";
  }
  
  
  /**
   * Este método verifica se o user está logado
   *
   *
   * @return
   */
  public boolean userLoggedIn(User user){
    return user != null;
  }
}
