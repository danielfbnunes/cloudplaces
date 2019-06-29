 /**
  * Projeto Open source
  */

package cloudplaces.webapp.mappings;

import cloudplaces.webapp.database_queries.PropertyQueries;
import cloudplaces.webapp.database_queries.UserQueries;
import cloudplaces.webapp.entities.House;
import cloudplaces.webapp.entities.User;
import cloudplaces.webapp.pojo.HousePOJO;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Esta classe é responsável por disponibilizar as chamadas
 * que permitiram obter as páginas web.
 */

//Todo create logs when pages are accessed.
@ApiIgnore
@Controller
public class CloudPlacesController {
  static final Logger logger = Logger.getLogger(CloudPlacesController.class.getName());
  
  @Autowired
  PropertyQueries propertyQueries;
  
  @Autowired
  UserQueries userQueries;
  
  private final String username = "username";
  private final String redirect = "redirect:/login";
  
  /**
   * Este método disponibiliza a página inicial da aplicação web.
   *
   *
   * @return página de login
   */
  @GetMapping("/login")
  public String login(Model model, HttpServletRequest request){    
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
  public String login(@ModelAttribute User user, Model model, HttpServletRequest request){
    logger.info("The following is user is trying to login: " + user.getName());
    
    //check if user is logged in
    if (userLoggedIn(request)) {
      return "redirect:/";
    }
    
    User u = userQueries.authenticateUser(user.getEmail(), user.getPw());
    if(u == null){
      model.addAttribute("error", true);
      return "login.html";
    }
    else{
      //save login
      request.getSession().setAttribute(username, u.getEmail());
      return "redirect:/";
    }
  }
  
  /**
   * Este método disponibiliza a página inicial de um utlizador que tenha realizado o login.
   * @return Retorna a página de inicial de um utilizador que tenha realizado o login.
   */
  @GetMapping("/")
  public String propertiesPage(Model model,  HttpServletRequest request){ 
    //check if user is logged in
    if (!userLoggedIn(request)) {
      return redirect;
    }
    
    //get properties from database
    List<House> houseList = propertyQueries.getAllProperties();
    model.addAttribute("has_elements" , !houseList.isEmpty());
    model.addAttribute("houses", houseList);

    logger.info("User: " + request.getSession().getAttribute(username));
    return "index";
  }
  
  
  /**
   * Este método disponibiliza a página inicial de um utlizador que tenha realizado o login.
   * @return Retorna a página de inicial de um utilizador que tenha realizado o login.
   */
  @PostMapping(path="/",  consumes = "application/json")
  @ResponseBody
  public List<House> propertiesPagePost(Model model,  HttpServletRequest request, @RequestBody Map<String,String> postPayload){ 
    //check if user is logged in  
        
    String name = postPayload.get("name");
    if(name.equals("")){
      name=null;
    }
    
     String location = postPayload.get("location");
    if(location.equals("")){
      location=null;
    }
    List<House> houseList = propertyQueries.getProperties(
            name,
            location,
            Float.parseFloat(postPayload.get("min_price").replaceAll(" ", "")), 
            Float.parseFloat(postPayload.get("max_price").replaceAll(" ", "")), 
            Integer.parseInt(postPayload.get("min_rooms").replaceAll(" ", "")), 
            Integer.parseInt(postPayload.get("max_rooms").replaceAll(" ", "")), 
            null, 
            null, 
            null
    );
    model.addAttribute("has_elements" , !houseList.isEmpty());
    model.addAttribute("houses", houseList);
    model.addAttribute("user", new User());

    return houseList;
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
    
    User addedUser = userQueries.addUser(
            postPayload.get("name"),
            postPayload.get("email"),
            postPayload.get("pw"),
            postPayload.get("cellphone"),
            postPayload.get("photo")
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
          HttpServletRequest request,
          Model model
  ){
    //check if user is logged in
    if (!userLoggedIn(request)) {
      return redirect;
    }
    
    House tmp = propertyQueries.getProperty(id);
    
    // Link house attributes to html template
    model.addAttribute("house", tmp);
    model.addAttribute("houseFeatures", tmp.getPropertyFeatures().split("_"));
    //Link user attributes to html template
    model.addAttribute("user", tmp.getUser());
    
    //convert byte images do b64
    model.addAttribute("userImage" , tmp.getUser().getPhoto());
    
    String[] houseImages = new String[tmp.getPhotos().size()];
    for (int i=0; i<tmp.getPhotos().size(); i++)
      houseImages[i] = tmp.getPhotos().get(0).getPhoto();
    
    model.addAttribute("houseImages" , houseImages);
    
    
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
   * 
   * @param model
   * @return 
   */
  @GetMapping("/getMyProperties")
  public String loadMyProperties(HttpServletRequest request, Model model) {
    if (!userLoggedIn(request)) {
      return "redirect:/login";
    }
    
    List<House> houses = new ArrayList<>();
    
    for (House house : propertyQueries.getAllProperties()) {
      if (house.getUser().getEmail().equals(request.getSession().getAttribute("username"))) {
        houses.add(house);
      }
    }
    
    model.addAttribute("userEmail", request.getSession().getAttribute("username"));
    model.addAttribute("houses", houses);
    return "properties.html";
  }
  
  /**
   * 
   * @param email
   * @param model
   * @return 
   */
  @GetMapping("/listProperty")
  public String loadListProperty(HttpServletRequest request, Model model){
    //check if user is logged in
    if (!userLoggedIn(request)) {
      return "redirect:/login";
    }
    
    model.addAttribute("userEmail", request.getSession().getAttribute("username"));
    return "list-property.html";
  }
  
  /**
   * 
   * @param property
   * @param model
   * @return 
   */
  @PostMapping(path = "/addProperty", consumes = "application/json", produces = "application/json")
  public String addProperty(@RequestBody HousePOJO property, HttpServletRequest request, Model model) {
    //check if user is logged in
    if (!userLoggedIn(request)) {
      return "redirect:/login";
    }
    
    propertyQueries.addProperty(property.getName(), property.getAddress(), property.getPrice(), property.getNRooms(), property.getUser().getEmail(), property.getHabSpace(), property.getNBathrooms(), property.getGarage(), property.getDescription(), property.getPropertyFeatures(), property.getAvailability(), property.getPhotos());
    
    return "list-property.html";
  }
  
  /**
   * 
   * @param property
   * @param model
   * @return 
   */
  @PutMapping(path = "/editProperty", consumes = "application/json", produces = "application/json")
  public String editProperty(@RequestBody HousePOJO property, HttpServletRequest request, Model model) {
    //check if user is logged in
    if (!userLoggedIn(request)) {
      return "redirect:/login";
    }
    
    propertyQueries.editProperty(property.getName(), property.getAddress(), property.getPrice(), property.getNRooms(), property.getUser().getEmail(), property.getHabSpace(), property.getNBathrooms(), property.getGarage(), property.getDescription(), property.getPropertyFeatures(), property.getAvailability(), property.getPhotos());
    
    return "properties.html";
  }
  
  /**
   * 
   * @param id
   * @param model
   */
  @DeleteMapping("/removeProperty")
  public void removeProperty(@RequestParam(name="id", required=true) final long id, Model model) {
    propertyQueries.removeProperty(id);
  }
  
  
   /**
   * Este método apaga a user session
   *
   *
   * @return página de login
   */
  @GetMapping("/logout")
  public String logout(HttpServletRequest request){
    request.getSession().setAttribute(username,"");
    return redirect;
  }
  
  
  /**
   * Este método verifica se o user está logado
   *
   *
   * @return
   */
  public boolean userLoggedIn(HttpServletRequest request){
    boolean unlogged = request.getSession().getAttribute(username)==null || request.getSession().getAttribute(username).equals("");
    logger.info("Is user logged in?  "+ !unlogged);
    return !unlogged;
  }
}
