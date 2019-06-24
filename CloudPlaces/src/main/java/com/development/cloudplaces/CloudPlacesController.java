package com.development.cloudplaces;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CloudPlacesController 
{

    @GetMapping("/getIndex")
    public String loadIndex(
            Model model) 
    {        
        return "index.html";
    }
        
    
    @GetMapping("/getHomepage")
    public String loadHomepage(
            Model model) 
    {   
        return "homepage.html";
    }
    
    @GetMapping("/getAbout")
    public String loadAbout(
            Model model) 
    {   
        return "about.html";
    }
    
    @GetMapping("/getSignUp")
    public String loadSignUp(
            Model model) 
    {   
        return "signup.html";
    }
    
    @GetMapping("/getWishlist")
    public String loadWishList(
            Model model) 
    {   
        return "wishlist.html";
    }
    
    @GetMapping("/getProperty")
    public String loadProperty(
            Model model) 
    {   
        return "single-property.html";
    }
    
    @GetMapping("/getProfile")
    public String loadProfile(
            Model model) 
    {   
        return "profile.html";
    }
}