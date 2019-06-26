/**
 * Projeto Open source
 */


package cloudplaces.webapp.mappings;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Esta classe é responsável por disponibilizar as chamadas
 * que permitiram obter as páginas web.
 */

//Todo create logs when pages are accessed.
@Controller
public class CloudPlacesController 
{

    /**
     * Este método disponibiliza a página inicial da aplicação web.
     * 
     * 
     * @return Retorna a página de inicial da aplicação web.
     */
    @GetMapping("/getIndex")
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
     * @return Retorna a página de inscrição na aplicação web.
     */     
    @GetMapping("/getSignUp")
    public String loadSignUp(Model model){   
        return "signup.html";
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
    public String loadProperty(Model model){   
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