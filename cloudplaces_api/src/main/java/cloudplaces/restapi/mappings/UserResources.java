/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.restapi.mappings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rd
 */
@RestController
@Api(value="User Resources", description = "Shows user resources")
public class UserResources 
{
    @ApiOperation("Returns a list of properties")
    @GetMapping("get_wishlist/{user_id}")
    public ArrayList<Object> GetWishlist(
            @PathVariable("user_id") final long user_id
            )
    {return new ArrayList<>();}
    
    
    @ApiOperation("Inserts a property into a wishlist")
    @PostMapping("add_to_wishlist/{user_id}/{property_id}")
    public boolean AddToWishlist(
            @PathVariable("user_id") final long user_id,
            @PathVariable("property_id") final long property_id
            )
    {return true;}
    
    
    @ApiOperation("Deletes a property from a wishlist")
    @DeleteMapping("delete_from_wishlist/{user_id}/{property_id}")
    public boolean DeleteFromWishlist(
            @PathVariable("user_id") final long user_id,
            @PathVariable("property_id") final long property_id
            )
    {return true;}
    
}
